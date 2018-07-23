package com.zilanghuo.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lwf
 * @date 2018/7/23
 * use: spring 生命周期（注解模式）
 */
@Component
@Slf4j
public class SpringBeanTestForAnnotation implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PreDestroy
    public void destroy() throws Exception {
        log.info("---------------------------------SpringBeanTestForAnnotation before destroy");
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        log.info("---------------------------------SpringBeanTestForAnnotation afterProperties set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("-----------------------applicationContext,applicationName:{},appName:{}"
                ,applicationContext.getApplicationName(),applicationContext.getDisplayName());

    }
}
