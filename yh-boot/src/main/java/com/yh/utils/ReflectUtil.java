package com.yh.utils;

import com.google.common.base.Preconditions;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 反射工具类
 *
 * @author yanhuan
 */
public final class ReflectUtil {

    public static <T> T assignField(Class clazz, boolean accessible, Object object, String fieldName, String value) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                if (field != null) {
                    field.setAccessible(accessible);
                    Class<?> classType = field.getType();
                    if (classType == Integer.class || classType == int.class) {
                        field.set(object, Integer.valueOf(value));
                    } else if (classType == Byte.class || classType == byte.class) {
                        field.set(object, Byte.valueOf(value));
                    } else if (classType == Long.class || classType == long.class) {
                        field.set(object, Long.valueOf(value));
                    } else {
                        field.set(object, value);
                    }
                }
                return (T) object;
            }
        }
        return (T) object;
    }

    public static Object copy(Object source, Class clazz) {
        try {
            Preconditions.checkNotNull(source);
            Object target = clazz.newInstance();
            //获取clazz的所有属性
            List<Field> targetFieldList = new ArrayList<>();
            List<Field> sourceFieldList = new ArrayList<>();
            Class sourceClass = source.getClass();
            while (sourceClass != null) {
                sourceFieldList.addAll(new ArrayList<>(Arrays.asList(sourceClass.getDeclaredFields())));
                sourceClass = sourceClass.getSuperclass();
            }
            Map<String, Object> name2ValueMap = new HashMap<>();
            for (Field f : sourceFieldList) {
                f.setAccessible(true);
                name2ValueMap.put(f.getName(), f.get(source));
            }
            while (clazz != null) {
                targetFieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
                clazz = clazz.getSuperclass();
            }
            for (Field field : targetFieldList) {
                if (name2ValueMap.containsKey(field.getName())) {
                    field.setAccessible(true);
                    field.set(target, name2ValueMap.get(field.getName()));
                }
            }
            return target;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
