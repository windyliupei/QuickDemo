package com.hon.windy.quickapi.controller;


import cn.hutool.json.JSONConverter;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONStrFormater;
import cn.hutool.json.JSONUtil;
import com.hon.windy.quickapi.fek.Custom_content;
import com.hon.windy.quickapi.fek.JsonRootBean;
import com.hon.windy.quickapi.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Callable;

@RequestMapping(value = "/v1/api/hello")
@Controller
public class HelloController {


    @Autowired
    HelloService service;

    @GetMapping("hi")
    @ResponseBody
    public String hi(){

        return "";
    }

    @ResponseBody
    @PostMapping("/say/{message}")
    public String hello(@PathVariable("message") String message) throws InterruptedException {

        return "success";
    }

    @ResponseBody
    @PostMapping("/helloBody")
    public String helloBody(@RequestBody String body) throws InterruptedException {
        return body;
    }


}
