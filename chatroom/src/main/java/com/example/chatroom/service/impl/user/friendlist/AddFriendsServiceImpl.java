package com.example.chatroom.service.impl.user.friendlist;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.FriendUserMapper;
import com.example.chatroom.mapper.StrangersMapper;
import com.example.chatroom.pojo.frienduser;
import com.example.chatroom.service.user.friendlist.AddFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddFriendsServiceImpl implements AddFriendsService {

    @Autowired
    FriendUserMapper friendUserMapper;


    @Override
    public Map<String, String> addFriend(Map<String, String> data) {
        String friendid=data.get("friendid");
        String userid=data.get("userid");
        String title=data.get("title");
        String photo=data.get("photo");
        String status="oneFriend";

        QueryWrapper<frienduser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",friendid);
        List<frienduser> check =friendUserMapper.selectList(queryWrapper);


        if(check.size()==0)
        {
            status="oneFriend";

        }
        else    {


            for(int i=0;i<check.size();i++)
            {
                if(check.get(i).getUserid().equals(friendid) && check.get(i).getFriendid().equals(userid) )
                {
                    status="allFriend";
                    frienduser new_change= new frienduser(check.get(i).getFriendid(),check.get(i).getUserid(),check.get(i).getTitle(),check.get(i).getPhoto(),"allFriend");
                    friendUserMapper.delete(queryWrapper);
                    friendUserMapper.insert(new_change);
                    break;
                }
            }
        }


        frienduser new_friendUser=new frienduser(friendid,userid,title,photo,status);

        friendUserMapper.insert(new_friendUser);

        Map<String,String> map=new HashMap<>();
        map.put("error_message","success");

        return  map;
    }
}
