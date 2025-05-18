package com.example.chatroom.service.user.account;

import java.util.Map;

public interface GetTokenService {
    Map<String,String> getToken(String username,String password);
}
