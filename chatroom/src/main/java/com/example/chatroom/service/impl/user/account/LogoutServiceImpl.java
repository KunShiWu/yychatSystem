package com.example.chatroom.service.impl.user.account;

import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.chat_user;

import com.example.chatroom.service.user.account.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class LogoutServiceImpl implements LogoutService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> logout(Integer id) {

        chat_user user= userMapper.selectById(id);
        chat_user new_user=new chat_user(user.getId(), user.getUsername(),user.getPassword(),user.getPhoto(),user.getTitle(),"No","No");
        userMapper.deleteById(user.getId());
        userMapper.insert(new_user);
        Map<String,String> map=new HashMap<>();

        map.put("error_message","success");
        return  map;
    }
}
