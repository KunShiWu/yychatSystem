package com.example.chatroom.controller.user.account;

import com.example.chatroom.service.user.account.GetIsLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetIsLoginController {
    @Autowired
    GetIsLoginService getIsLoginService;

    @GetMapping("/user/account/getLogin/")
    private Map<String,String> getlogin()
    {

        return    getIsLoginService.GetIsLogin();
    }

}
