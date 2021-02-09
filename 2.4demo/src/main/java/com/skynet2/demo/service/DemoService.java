package com.skynet2.demo.service;

import com.skynet2.demo.configuration.WaitProperties;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public DemoService(WaitProperties properties) throws InterruptedException {
        System.err.println(properties.getFor().toMillis());
        Thread.sleep(properties.getFor().toMillis());
    }

    public String go() {
        return "Hello World";
    }
}
