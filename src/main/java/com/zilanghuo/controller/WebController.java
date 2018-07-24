package com.zilanghuo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lwf
 * @date 2018/7/24
 * use: 类加上前缀，需要对应的文件夹也要有对应的文件目录
 */
@Controller
@RequestMapping(value = "/web")
@Slf4j
public class WebController {

    @RequestMapping(value = "index")
    public String index(){
        log.info("add web index");
        return "index";
    }
}
