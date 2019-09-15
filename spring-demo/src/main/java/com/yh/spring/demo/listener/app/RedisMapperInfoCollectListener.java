package com.yh.spring.demo.listener.app;

import com.yh.spring.demo.common.utils.ReflectUtil;
import com.yh.spring.demo.common.utils.SpringUtil;
import com.yh.spring.demo.proxy.redis.annotation.RedisMapper;
import com.yh.spring.demo.proxy.redis.annotation.RedisMethod;
import com.yh.spring.demo.proxy.redis.components.RedisMapperInfoContainer;
import com.yh.spring.demo.proxy.redis.components.RedisMapperMethodInfo;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Mapper接口中走RedisMapper代理的方法信息收集Listener
 *
 * @author yanhuan
 */
@Component
public class RedisMapperInfoCollectListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(RedisMapperInfoCollectListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                collectRedisMapperMethodInfo();
            } catch (Exception e) {
                logger.error("RedisMapperInfoCollectListener onApplicationEvent failed.", e);
                throw new RuntimeException("RedisMapperInfoCollectListener onApplicationEvent failed.");
            }
        }
    }

    /**
     * 收集加上@RedisMapper、@RedisMethod注解的方法的信息
     */
    private void collectRedisMapperMethodInfo() throws Exception {
        try {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringUtil.getBean(SqlSessionFactory.class);
            Configuration configuration = sqlSessionFactory.getConfiguration();
            MapperRegistry mapperRegistry = configuration.getMapperRegistry();
            Collection<Class<?>> mapperClasses = mapperRegistry.getMappers();
            if (CollectionUtils.isEmpty(mapperClasses)) {
                throw new RuntimeException("No Mapper Classes");
            }
            for (Class mapperClazz : mapperClasses) {
                RedisMapper annotation = (RedisMapper)mapperClazz.getAnnotation(RedisMapper.class);
                if(annotation == null){
                    continue;
                }
                List<Method> methodList = ReflectUtil.getMethodByClassWithAnnotation(mapperClazz, RedisMethod.class);
                if (CollectionUtils.isEmpty(methodList)) {
                    continue;
                }
                RedisMapperMethodInfo info;
                for (Method method : methodList) {
                    info = new RedisMapperMethodInfo();
                    info.setMapperInterface(mapperClazz);
                    info.setMethod(method);
                    info.setReturnType(method.getReturnType());
                    info.setGenericType(ReflectUtil.getReturnGenericTypeByMethod(method));
                    info.setMappedStatement(configuration.getMappedStatement(method.getName()));
                    info.setParamTypes(Arrays.asList(method.getParameterTypes()));
                    RedisMapperInfoContainer.put(method.toString(), info);
                }
            }
        } catch (Exception e) {
            logger.error("Scanning methods witch are annotated by @RedisMapper failed.", e);
            throw new RuntimeException("Scanning methods witch are annotated by @RedisMapper failed.");
        }
    }


    @Override
    public int getOrder() {
        return 5;
    }
}
