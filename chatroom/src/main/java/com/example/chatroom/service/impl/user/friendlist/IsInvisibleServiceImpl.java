package com.example.chatroom.service.impl.user.friendlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.service.user.friendlist.IsInvisibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IsInvisibleServiceImpl implements IsInvisibleService   {

    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String,String>IsInvisible() {
        QueryWrapper<chat_user> queryWrapper=new QueryWrapper<>();
        List<chat_user> lists=userMapper.selectList(queryWrapper);

        Map<String,String > map=new HashMap<>();
        for (int i=0;i<lists.size();i++)
        {
            map.put(lists.get(i).getId().toString(),lists.get(i).getIsInvisible());
        }
        return map;

    }
}
