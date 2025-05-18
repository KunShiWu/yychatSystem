package com.example.chatroom.service.impl.user;

import com.example.chatroom.service.user.TestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public Map<String, String> Test() {
        Map<String,String> map=new HashMap<>();
        map.put("success","我是你baba");
        return map;
    }
}
