package com.zilanghuo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwf
 * @date 2018/7/12
 * use:
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
