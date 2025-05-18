package com.example.chatroom.service.impl.user.friendlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.FriendUserMapper;
import com.example.chatroom.mapper.IsFriendMapper;
import com.example.chatroom.pojo.frienduser;
import com.example.chatroom.pojo.isfriend;
import com.example.chatroom.service.user.friendlist.IsFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

import java.util.Map;
@Service
public class IsFriendServiceImpl implements IsFriendService {

    @Autowired
    IsFriendMapper isFriendMapper;

    @Autowired
    FriendUserMapper friendUserMapper;
    @Override
    public Map<String, String> IsFriend(Integer userid,Integer friendid) {



        QueryWrapper<isfriend> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("userid",userid);
        isfriend   IsFriend = isFriendMapper.selectOne(queryWrapper);


        StringBuffer sb =new StringBuffer(IsFriend.getFlag());

        sb.setCharAt(friendid-1,'1');

        String new_flag=sb.toString();

        isFriendMapper.delete(queryWrapper);


        isfriend new_isFriend =new isfriend(userid,new_flag);
        isFriendMapper.insert(new_isFriend);


        Map<String,String> map=new HashMap();


        map.put("error_message","success");
        return map;
    }
}
