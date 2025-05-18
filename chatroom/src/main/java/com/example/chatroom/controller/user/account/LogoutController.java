package com.example.chatroom.controller.user.account;

import com.example.chatroom.service.user.account.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogoutController {
    @Autowired
    LogoutService logoutService;

    @PostMapping("/user/account/logout/")
    private Map<String,String> logout(@RequestParam Map<String,String> data)
    {
        Integer id=Integer.parseInt(data.get("id"));
        return  logoutService.logout(id);
    }

}
