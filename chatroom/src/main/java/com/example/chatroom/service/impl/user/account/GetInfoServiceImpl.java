package com.example.chatroom.service.impl.user.account;

import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.service.impl.util.UserDetailsImpl;
import com.example.chatroom.service.user.account.GetInfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetInfoServiceImpl implements GetInfoService {

    @Override
    public Map<String, String> getInfo() {

        UsernamePasswordAuthenticationToken authentication =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();

        chat_user user=loginUser.getUser();

        Map<String,String> map=new HashMap<>();

        map.put("error_message","success");
        map.put("id",user.getId().toString());
        map.put("username", user.getUsername());
        map.put("photo",user.getPhoto());
        map.put("title",user.getTitle());

        return  map;





    }
}
