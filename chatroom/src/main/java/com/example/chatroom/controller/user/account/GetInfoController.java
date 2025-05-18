package com.example.chatroom.controller.user.account;


import com.example.chatroom.service.user.account.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetInfoController {
    @Autowired
    GetInfoService getInfoService;

    @GetMapping("/user/account/info/")
    private Map<String,String> getInfo(){
        return  getInfoService.getInfo();
    }

}
