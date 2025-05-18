package com.example.chatroom.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.service.user.account.GetIsLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetIsLoginServiceImpl implements GetIsLoginService {

    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> GetIsLogin() {

        QueryWrapper<chat_user> queryWrapper=new QueryWrapper<>();

        List<chat_user> lists=userMapper.selectList(queryWrapper);

        Map<String,String > map=new HashMap<>();
        for (int i=0;i<lists.size();i++)
        {
            map.put(lists.get(i).getId().toString(),lists.get(i).getIsLogin());
        }
        return map;

    }
}
