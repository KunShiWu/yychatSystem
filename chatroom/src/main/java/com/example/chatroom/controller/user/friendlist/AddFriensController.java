package com.example.chatroom.controller.user.friendlist;

import com.example.chatroom.service.user.friendlist.AddFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddFriensController {

    @Autowired
    AddFriendsService addFriendsService;

    @PostMapping("/user/friendlist/add/")
    private Map<String,String> addfriend (@RequestParam Map<String,String> data)
    {
        return  addFriendsService.addFriend(data);
    }

}
