package com.example.chatroom.service.impl.user.friendlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.FriendUserMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.pojo.frienduser;
import com.example.chatroom.service.impl.util.UserDetailsImpl;
import com.example.chatroom.service.user.friendlist.FriendsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsInfoServiceImpl implements FriendsInfoService {

    @Autowired
    FriendUserMapper friendUserMapper;
    @Override
    public List<frienduser> FriendInfo() {

        UsernamePasswordAuthenticationToken authenticationToken=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();

        chat_user user=loginUser.getUser();

        QueryWrapper<frienduser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());

        List<frienduser> list =friendUserMapper.selectList(queryWrapper);
        return list;
    }
}
