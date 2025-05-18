package com.example.chatroom.controller.user.message;

import com.example.chatroom.service.user.message.AddMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddMessageController {
    @Autowired
    AddMessageService addMessageService;

    @PostMapping("/user/message/addmessage/")
    private Map<String,String> AdddMessage(@RequestParam Map<String,String> data)
    {
        String userid=data.get("userid");
        String friendid=data.get("friendid");
        String message=data.get("message");
        return  addMessageService.AddMessage(userid,friendid,message);
    }



}
