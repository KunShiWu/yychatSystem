package com.example.chatroom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chatroom.pojo.chat_user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<chat_user> {
}
