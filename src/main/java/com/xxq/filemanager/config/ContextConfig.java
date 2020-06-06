package com.xxq.filemanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;

@Configuration
public class ContextConfig {

    public static Logger logger = LoggerFactory.getLogger(ContextConfig.class);

    public static ConfigurableApplicationContext context;
    public static ConfigurableEnvironment env;


    public static Object create(Class tempClass, long time) {
        if (System.currentTimeMillis() - time > 5000L) {
            logger.error("容器里获取类超时。。。" + tempClass);
            System.exit(0);
        }
        Object bean = context.getBean(tempClass);
        if (bean == null) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return create(tempClass, time);
        }
        return bean;
    }

    @Bean
    public ApplicationContext test(ConfigurableApplicationContext c) {
        context = c;
        env = context.getEnvironment();
        return c;
    }

    @Bean
    public BorrowMap map(ConfigurableApplicationContext c) {
        logger.info("map注入成功");
        return new BorrowMap();
    }

}
