package com.example.chatroom.service.impl.user.account;

import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.service.impl.util.UserDetailsImpl;
import com.example.chatroom.service.user.account.GetTokenService;
import com.example.chatroom.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class GetTokenServiceImpl implements GetTokenService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override

    public Map<String, String> getToken(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(username,password);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();


        chat_user user=loginUser.getUser();

        String jwt= JwtUtil.createJWT(user.getId().toString());

        chat_user new_user=new chat_user(user.getId(), user.getUsername(),user.getPassword(),user.getPhoto(),user.getTitle(),user.getIsInvisible(),"Yes");
        userMapper.deleteById(user.getId());
        userMapper.insert(new_user);


        Map<String,String> map=new HashMap<>();

        map.put("error_message","success");
        map.put("access_token",jwt);

        return  map;


    }
}
