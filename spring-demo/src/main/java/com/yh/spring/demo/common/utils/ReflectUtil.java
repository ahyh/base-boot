package com.yh.spring.demo.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * 反射相关的工具类
 *
 * @author yanhuan
 */
public class ReflectUtil {

    /**
     * 获取同一个包下接口的实现类
     *
     * @param interfaceClass 接口类型
     */
    public static List<Class<?>> findAllClassByInterface(Class<?> interfaceClass) throws IOException {
        if (!interfaceClass.isInterface()) {
            return null;
        }
        String packageName = interfaceClass.getPackage().getName();
        List<Class<?>> classList = getClassesByPackageName(packageName);
        if (CollectionUtils.isEmpty(classList)) {
            return null;
        }
        List<Class<?>> returnClassList = new ArrayList<>();
        for (Class<?> clazz : classList) {
            if (interfaceClass.isAssignableFrom(clazz) && !interfaceClass.equals(clazz)) {
                returnClassList.add(clazz);
            }
        }
        return returnClassList;
    }

    public static List<Class<?>> getClassesByPackageWithAnnotation(String packageName, Class<? extends Annotation> annotationClass) throws IOException {
        List<Class<?>> classList = getClassesByPackageName(packageName);
        if (CollectionUtils.isEmpty(classList)) {
            return new ArrayList<>();
        }
        return classList.stream().filter(x -> x.getAnnotation(annotationClass) != null).collect(Collectors.toList());
    }

    public static List<Method> getMethodByClassWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        Method[] methods = clazz.getMethods();
        if (ArrayUtils.isNotEmpty(methods)) {
            return Arrays.stream(methods).filter(x -> x.getAnnotation(annotationClass) != null).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 获取返回值类型中的泛型类型，如果没有泛型类型返回null
     *
     * @param method 方法
     * @return 泛型类型
     */
    public static List<Class<?>> getReturnGenericTypeByMethod(Method method) {
        Type type = method.getGenericReturnType();
        // 判断获取的类型是否是参数类型
        if (type instanceof ParameterizedType) {
            List<Class<?>> genericClasses = new ArrayList<>();
            // 强制转型为带参数的泛型类型，
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type t : types) {
                genericClasses.add((Class) t);
            }
            return genericClasses;
        }
        return null;
    }

    /**
     * 获取package下所有Class
     *
     * @param packageName 包名
     * @return package下Class集合
     */
    public static List<Class<?>> getClassesByPackageName(String packageName) throws IOException {
        List<Class<?>> classes = new ArrayList<>();
        boolean recursive = true;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
            } else if ("jar".equals(protocol)) {
                JarFile jar;
                jar = ((JarURLConnection) url.openConnection()).getJarFile();
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.charAt(0) == '/') {
                        name = name.substring(1);
                    }
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        if (idx != -1) {
                            packageName = name.substring(0, idx).replace('/', '.');
                        }
                        if ((idx != -1) || recursive) {
                            if (name.endsWith(".class") && !entry.isDirectory()) {
                                String className = name.substring(packageName.length() + 1, name.length() - 6);
                                try {
                                    classes.add(Class.forName(packageName + '.' + className));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return classes;
    }

    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirFiles = dir.listFiles(file -> (recursive && file.isDirectory()) || (file.getName().endsWith(".class")));
        if (dirFiles != null && dirFiles.length > 0) {
            for (File file : dirFiles) {
                if (file.isDirectory()) {
                    findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
                } else {
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    try {
                        classes.add(Class.forName(packageName + '.' + className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
