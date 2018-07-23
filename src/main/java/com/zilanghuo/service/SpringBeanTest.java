package com.zilanghuo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author lwf
 * @date 2018/7/23
 * use:
 */
@Component
@Slf4j
public class SpringBeanTest implements InitializingBean,DisposableBean {


    @Override
    public void destroy() throws Exception {
        log.info("---------------------------------before destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("---------------------------------afterProperties set");
    }
}
