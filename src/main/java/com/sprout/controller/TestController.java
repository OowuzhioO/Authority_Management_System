package com.sprout.controller;


import com.sprout.common.JsonData;
import com.sprout.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

//    @RequestMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        log.info("hello");
//        System.out.println("test successfully");
//        return "hello, permission";
//    }

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
//        return JsonData.success("hello, permission");
//        throw new PermissionException("test exception");
        throw new RuntimeException("test excetion");
    }
}
