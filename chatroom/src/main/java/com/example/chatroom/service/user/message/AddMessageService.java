package com.example.chatroom.service.user.message;

import java.util.Map;

public interface AddMessageService {
    Map<String,String> AddMessage(String userid,String friendid,String message);
}
