package com.example.chatroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.service.impl.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<chat_user> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username);//这里的“username”是和数据库对应的名字

        chat_user user=userMapper.selectOne(queryWrapper);

        if(user==null)
        {
            throw  new   RuntimeException("用户不存在");
        }

        return new UserDetailsImpl(user);

    }
}
