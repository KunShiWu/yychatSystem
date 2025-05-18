package com.example.chatroom.service.impl.user.message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.chatroom.mapper.MessageGitMapper;
import com.example.chatroom.pojo.messagegit;
import com.example.chatroom.service.user.message.GetMessageService;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GetMessageServiceImpl implements GetMessageService {

    @Autowired
    MessageGitMapper messageGitMapper;
    @Override
    public         LinkedList<messagegit> GetMessage(String userid, String friendid) {

        QueryWrapper<messagegit> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("userid",userid);

        List<messagegit> me=messageGitMapper.selectList(queryWrapper);

        QueryWrapper<messagegit> queryWrapper1=new QueryWrapper<>();

        queryWrapper1.eq("userid",friendid);
        List<messagegit> you=messageGitMapper.selectList(queryWrapper1);
        List<messagegit> me_list=new ArrayList<>();
        List<messagegit> you_list=new ArrayList<>();
        LinkedList<messagegit> list=new LinkedList<>();


        for(int i = 0;i<me.size();i++)
            if (me.get(i).getFriendid().equals(friendid)) {
                me_list.add(me.get(i));
            }
        for(int i=0;i<you.size();i++)
            if(you.get(i).getFriendid().equals(userid))
            {
                you_list.add(you.get(i));
            }
        int i=0,j=0;
        while (i != me_list.size() || j != you_list.size()) {

            if (j == you_list.size()) {
                list.add(me_list.get(i));
                i++;
                continue;
            }
            if (i == me_list.size()) {
                list.add(you_list.get(j));
                j++;
                continue;
            }

            if(me_list.get(i).getId()<you_list.get(j).getId())
            {
                list.add(me_list.get(i));
                i++;
            }else {
                list.add(you_list.get(j));
                j++;

            }



        }

        return list;
    }
}
