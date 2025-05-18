package com.example.chatroom.controller.user;

import com.example.chatroom.service.user.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @GetMapping("/test/getInfo/")
    private Map<String,String> test()
    {
        return testService.Test();
    }

}
