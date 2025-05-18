package com.example.chatroom.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class chat_user {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private String title;
    private String isInvisible;
    private String isLogin;
}
