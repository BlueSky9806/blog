package com.zhanghao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhanghao
 * @data 2021/06/18
 */
@Controller
public class AboutController {

    @GetMapping("about")
    public String about() {
        return "about";
    }
}
