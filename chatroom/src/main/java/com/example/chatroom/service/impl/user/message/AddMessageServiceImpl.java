package com.example.chatroom.service.impl.user.message;

import com.example.chatroom.mapper.MessageGitMapper;
import com.example.chatroom.pojo.messagegit;
import com.example.chatroom.service.user.message.AddMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddMessageServiceImpl  implements AddMessageService {
    @Autowired
    MessageGitMapper messageGitMapper;
    @Override
    public Map<String, String> AddMessage(String userid, String friendid,String message) {
        Map<String,String> map=new HashMap<>();
        messagegit MessageGit=new messagegit(null,userid,friendid,message);
        messageGitMapper.insert(MessageGit);


        map.put("error_message","success");

        return  map;
    }
}
