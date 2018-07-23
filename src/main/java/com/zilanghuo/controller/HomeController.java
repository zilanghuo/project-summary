package com.zilanghuo.controller;

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
@RequestMapping
public class HomeController {

    @RequestMapping("/index")
    public String home() {
        return "index";
    }
}
