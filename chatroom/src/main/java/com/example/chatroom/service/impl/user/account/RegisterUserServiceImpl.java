package com.example.chatroom.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.IsFriendMapper;
import com.example.chatroom.mapper.StrangersMapper;
import com.example.chatroom.mapper.UserMapper;
import com.example.chatroom.pojo.isfriend;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.pojo.strangers;
import com.example.chatroom.service.user.account.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    StrangersMapper strangersMapper;
    @Autowired
    IsFriendMapper isFriendMapper;
    @Override
    public Map<String, String> RegisterUser(String username, String password, String confirmPassword) {
        Map<String,String> map =new HashMap<>();



        if(username==null||username.length()==0)
        {
            map.put("error_message","用户名不能为空");
            return   map;
        }

        if(username.length()>100)
        {
            map.put("error_message","用户名不能设置过长");
            return  map;
        }

        if(password==null||password.length()==0)
        {
            map.put("error_message","密码不能为空");
            return map;
        }
        if(password.length()>100||confirmPassword.length()>100)
        {
            map.put("error_message","密码不能太长");

            return map;
        }

        if(!confirmPassword.equals(password))
        {
            map.put("error_message","两次密码不一致");
            return  map;
        }


        QueryWrapper<chat_user> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username);

        List<chat_user> users=userMapper.selectList(queryWrapper);

        if(!users.isEmpty())
        {
            map.put("error_message","用户名已存在");
            return map;
        }

        String encodePassword= passwordEncoder.encode(password);
        String photo="https://cdn.acwing.com/media/user/profile/photo/196648_lg_9abf0246d4.jpg";
        String title="未命名";

        chat_user user=new chat_user (null,username,encodePassword,photo,title,"No","No");

        userMapper.insert(user);
        strangers s=new strangers(user.getId(),user.getUsername(),user.getTitle(),user.getPhoto());

        strangersMapper.insert(s);


        isfriend isFriend=new isfriend(user.getId(),"000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        isFriendMapper.insert(isFriend);
        map.put("error_message","success");

        return map;
    }
}
