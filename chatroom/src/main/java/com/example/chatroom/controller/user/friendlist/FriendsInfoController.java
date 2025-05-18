package com.example.chatroom.controller.user.friendlist;

import com.example.chatroom.pojo.frienduser;
import com.example.chatroom.service.user.friendlist.FriendsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendsInfoController {

    @Autowired
    FriendsInfoService friendsInfoService;
    @GetMapping("/user/friendlist/friends/info/")
    private List<frienduser> getFriendInfo()
    {
        return  friendsInfoService.FriendInfo();
    }

}
