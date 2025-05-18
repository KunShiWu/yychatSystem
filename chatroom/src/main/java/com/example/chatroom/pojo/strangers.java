package com.example.chatroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class strangers {
    private Integer id;
    private String username;

    private String title;
    private String photo;
}
