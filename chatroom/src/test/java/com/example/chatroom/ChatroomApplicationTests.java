package com.example.chatroom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ChatroomApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("p1"));
    }

}
