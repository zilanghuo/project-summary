package com.zilanghuo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author lwf
 * @date 2018/7/24
 * use: @RestController 返回的是数据体
 */
@RestController
@RequestMapping(value = "/core")
@Slf4j
public class RestControllerDemo {

    @RequestMapping(value = "/index")
    public String index() {
        log.info("add core index");
        return "index";
    }

    @RequestMapping(value = "registerCallback")
    public String registerCallback(HttpServletRequest request) {
        log.info("接收到回调地址");
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            log.info(paraName + ": " + request.getParameter(paraName));
        }
        return "0000";
    }
}
