package com.skynet2.demo.controller;

import com.skynet2.demo.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping("/")
    public String hello(){
        return demoService.go();
    }
}
