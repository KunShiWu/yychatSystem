package com.example.chatroom.controller.user.account;

import com.example.chatroom.service.user.account.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterUserController {
    @Autowired
    RegisterUserService registerUserService;

    @PostMapping("/user/account/register/")
    private Map<String,String> registerUser(@RequestParam Map<String,String> data)
    {
        String username = data.get("username");
        String password = data.get("password");
        String confirmPassword = data.get("confirmPassword");

        return  registerUserService.RegisterUser(username,password,confirmPassword);
    }


}
