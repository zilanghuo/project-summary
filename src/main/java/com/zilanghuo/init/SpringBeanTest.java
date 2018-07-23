package com.zilanghuo.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author lwf
 * @date 2018/7/23
 * use:spring 生命周期（继承类）
 */
@Component
@Slf4j
public class SpringBeanTest implements InitializingBean,DisposableBean {


    @Override
    public void destroy() throws Exception {
        log.info("---------------------------------SpringBeanTest before destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("---------------------------------SpringBeanTest afterProperties set");
    }
}
