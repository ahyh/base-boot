package com.yh.spring.demo.listener.app;

import com.yh.spring.demo.common.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 初始化SpringUtil
 *
 * @author yanhuan
 */
@Component
public class SpringUtilInitializeListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(SpringUtilInitializeListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                if (!SpringUtil.isApplicationContextLoaded()) {
                    SpringUtil.setApplicationContext(contextRefreshedEvent.getApplicationContext());
                }
            } catch (Exception e) {
                logger.error("SpringUtilInitializeListener onApplicationEvent failed.", e);
                throw new RuntimeException("SpringUtilInitializeListener onApplicationEvent failed.");
            }
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
