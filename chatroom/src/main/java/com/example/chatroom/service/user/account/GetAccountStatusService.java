package com.example.chatroom.service.user.account;

import java.util.Map;

public interface GetAccountStatusService {
    Map<String,String > GetAccountStatus(Integer userid,String  status);
}
