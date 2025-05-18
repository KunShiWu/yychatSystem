package com.example.chatroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class frienduser {

    private String friendid;
    private String userid;
    private String title;
    private String photo;
    private String status;
}
