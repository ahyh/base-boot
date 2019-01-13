package com.yh.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.RestController;

/**
 * 容器初始化Listener
 *
 * @author yanhuan
 */
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartListener.class);

    /**
     * 容器初始化后就触发的方法
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        String applicationName = applicationContext.getApplicationName();
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(RestController.class);
        logger.info("applicationName:{}", applicationName);
        for (String s : beanNamesForAnnotation) {
            logger.info("Controller:{}", s);
        }
    }
}
