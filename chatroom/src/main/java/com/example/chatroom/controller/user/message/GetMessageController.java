package com.example.chatroom.controller.user.message;

import com.example.chatroom.pojo.messagegit;
import com.example.chatroom.service.user.message.GetMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class GetMessageController  {
    @Autowired
    GetMessageService getMessageService;

    @GetMapping("/user/message/getmessage/")
    private LinkedList<messagegit> GetMessage(@RequestParam Map<String,String> data)
    {
        String userid=data.get("userid");
        String friendid=data.get("friendid");

        return  getMessageService.GetMessage(userid,friendid);
    }

}
