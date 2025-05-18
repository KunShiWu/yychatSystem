package com.example.chatroom.service.user.message;

import com.example.chatroom.pojo.messagegit;

import java.util.LinkedList;
import java.util.List;

public interface GetMessageService {
    LinkedList<messagegit> GetMessage(String userid, String friendid);
}
