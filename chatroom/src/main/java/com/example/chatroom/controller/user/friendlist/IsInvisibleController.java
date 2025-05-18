package com.example.chatroom.controller.user.friendlist;

import com.example.chatroom.service.user.friendlist.IsInvisibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class IsInvisibleController {
    @Autowired
    IsInvisibleService isInvisibleService;

    @PostMapping("/user/friendlist/isinvisible/")
    private Map<String,String> Is_Invisible ()
    {
        return isInvisibleService.IsInvisible();
    }


}
