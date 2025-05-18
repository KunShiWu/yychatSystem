package com.example.chatroom.controller.user.friendlist;

import com.example.chatroom.service.user.friendlist.IsFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IsFriendController {

    @Autowired
    IsFriendService   isFriendService;

    @PostMapping("/user/friendlist/isFriend/")
    private Map<String,String > isFriend (@RequestParam Map<String,String > data)
    {
        Integer userid= Integer.parseInt( data.get("userid"));
        Integer friendid=Integer.parseInt(data.get("friendid")) ;

        return isFriendService.IsFriend(userid,friendid);
    }

}
