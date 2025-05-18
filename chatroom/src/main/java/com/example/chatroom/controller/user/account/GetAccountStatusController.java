package com.example.chatroom.controller.user.account;

import com.example.chatroom.service.user.account.GetAccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetAccountStatusController {
    @Autowired
    GetAccountStatusService getAccountStatusService;
    @PostMapping("/user/account/getaccountstatus/")
    private Map<String,String > GetAccountStatus(@RequestParam Map<String,String > data)
    {
        Integer userid=Integer.parseInt(data.get("userid"));
        String status=data.get("status");
        return getAccountStatusService.GetAccountStatus(userid,status);
    }

}
