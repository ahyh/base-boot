package com.yh.mybatis.interceptors;

import com.yh.annotations.EncrypAnnotation;
import com.yh.utils.EncrypUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

/**
 * 拦截器签名
 *
 * @author yanhuan1
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class EncrypInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(EncrypInterceptor.class);

    /**
     * 拦截器的拦截方法
     * 获取方法入参，根据反射拿到入参对象的所有Field，如果Field上加了
     * EncrypAnnoation注解，则将此入参的对应的值加密/解密
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object param = args[1];
        //写入时，加注解的字段加密
        MappedStatement statement = (MappedStatement) args[0];
        String name = statement.getSqlCommandType().name();
        if (!name.equalsIgnoreCase("select")) {
            setEncryptFieldValue(param, true);
        }
        Object returnValue = invocation.proceed();
        //解密
        if (returnValue instanceof List) {
            List returnValueList = (List) returnValue;
            for (Object obj : returnValueList) {
                setEncryptFieldValue(obj, false);
            }
        } else {
            setEncryptFieldValue(returnValue, false);
        }
        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 处理加密的字段值
     *
     * @param object     需要处理的入参/执行结果
     * @param encodeFlag true-入参加密，false-结果值解密
     * @throws Exception
     */
    private void setEncryptFieldValue(Object object, boolean encodeFlag) throws Exception {
        //只能拿到自己本类中的Field,不能拿到父类中的Field
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null && annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof EncrypAnnotation) {
                        field.setAccessible(true);
                        String sourceStr = field.get(object).toString();
                        if (encodeFlag) {
                            field.set(object, EncrypUtil.encodeJDKBase64(sourceStr));
                        } else {
                            field.set(object, EncrypUtil.decodeJDKBase64(sourceStr));
                        }
                    }
                }
            }
        }
    }
}
