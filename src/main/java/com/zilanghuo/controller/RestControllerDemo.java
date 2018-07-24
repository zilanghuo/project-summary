package com.zilanghuo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String index(){
        log.info("add core index");
        return "index";
    }

}
