package com.zilanghuo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwf
 * @date 2018/7/12
 * use:
 */
@Controller
@RequestMapping(value = "/")
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("add index jsp");
        return "index";
    }
}
