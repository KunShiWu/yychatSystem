package com.example.chatroom.service.user.account;

import java.util.Map;

public interface RegisterUserService {
    Map<String,String> RegisterUser(String username,String password,String confirmPassword);
}
