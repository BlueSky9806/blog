package com.zhanghao.controller.admin;

import com.zhanghao.service.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghao
 * @data 2021/06/17
 */
@RestController
public class TestController {

    @Autowired
    AsyncTask asyncTask;

    @GetMapping("sendEmail")
    public String test1() throws InterruptedException {
//        int i = 1 / 0;
        asyncTask.taskA();
        return "测试发送邮件";
    }
}
