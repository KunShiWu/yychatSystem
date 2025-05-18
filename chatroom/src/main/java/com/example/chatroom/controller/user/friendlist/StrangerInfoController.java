package com.example.chatroom.controller.user.friendlist;

import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.pojo.strangers;
import com.example.chatroom.service.user.friendlist.StrangerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StrangerInfoController {

    @Autowired
    StrangerInfoService strangerInfoService;


    @GetMapping("/user/friendlist/stranger/info/")
    private List<strangers>  getList()
    {
        return strangerInfoService.GetStrangerInfo();
    }


}
