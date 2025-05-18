package com.example.chatroom.controller.user.account;

import com.example.chatroom.service.user.account.GetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetTokenController {
    @Autowired
    GetTokenService getTokenService;

    @PostMapping("/user/account/token/")
    public Map<String,String > getToken(@RequestParam Map<String ,String> map)
    {
        String username=map.get("username");
        String password=map.get("password");

        return  getTokenService.getToken(username,password);
    }

}
