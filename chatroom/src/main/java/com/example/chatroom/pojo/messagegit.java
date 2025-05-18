package com.example.chatroom.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class messagegit {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userid;
    private String friendid;
    private String message;

}
