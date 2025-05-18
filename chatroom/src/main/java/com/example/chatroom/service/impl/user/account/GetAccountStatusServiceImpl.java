package com.example.chatroom.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.service.user.account.GetAccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class GetAccountStatusServiceImpl implements GetAccountStatusService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> GetAccountStatus(Integer userid, String status) {
        QueryWrapper<chat_user> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("id",userid);
        chat_user user=userMapper.selectOne(queryWrapper);

        user.setIsInvisible(status);
        userMapper.delete(queryWrapper);
        userMapper.insert(user);
        Map<String,String> map=new HashMap<>();
        map.put("error_message",status);
        return  map;
    }
}
