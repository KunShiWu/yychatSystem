package com.example.chatroom.service.user.friendlist;

import java.util.Map;

public interface IsFriendService {
    Map<String,String> IsFriend(Integer userid,Integer friendid);
}
