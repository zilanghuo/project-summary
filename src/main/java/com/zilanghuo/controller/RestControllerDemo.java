package com.zilanghuo.controller;

import com.zilanghuo.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.TreeMap;

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
        TreeMap<String, Object> resultMap = new TreeMap();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            resultMap.put(paraName, request.getParameter(paraName));
            log.info(paraName + ": " + request.getParameter(paraName));
        }
        log.info("----------------------------------------------------");
        log.info("compare:{}", SecurityUtils.verifyByTreeMap(resultMap, request.getParameter("signature")));

        return "0000";
    }
}
