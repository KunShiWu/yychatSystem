package com.example.chatroom.service.impl.user.friendlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.IsFriendMapper;
import com.example.chatroom.mapper.StrangersMapper;
import com.example.chatroom.pojo.chat_user;
import com.example.chatroom.pojo.isfriend;
import com.example.chatroom.pojo.strangers;
import com.example.chatroom.service.impl.util.UserDetailsImpl;
import com.example.chatroom.service.user.friendlist.StrangerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrangerInfoServiceImpl implements StrangerInfoService {

    @Autowired
    StrangersMapper strangersMapper;
    @Autowired
    IsFriendMapper isFriendMapper;
    @Override
    public List<strangers> GetStrangerInfo() {
        UsernamePasswordAuthenticationToken authenticationToken=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();

        chat_user user=loginUser.getUser();

        QueryWrapper<isfriend> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("userid",user.getId());
        isfriend IsFriend=isFriendMapper.selectOne(queryWrapper);
        List<strangers> list=strangersMapper.selectList(null);

        String flag=IsFriend.getFlag();

        for(int i=0;i<flag.length();i++)
        {
            if(flag.charAt(i)=='1')
            {
                for(int j=0;j<list.size();j++)
                {
                  if(list.get(j).getId()==i+1)
                  {
                      list.remove(j);
                  }
                }

            }
        }


        return list;
    }
}
