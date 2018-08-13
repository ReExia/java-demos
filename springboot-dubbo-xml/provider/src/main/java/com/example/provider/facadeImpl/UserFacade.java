package com.example.provider.facadeImpl;

import com.example.api.facade.IUserFacade;
import com.example.provider.service.AccountService;
import com.example.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userFacade")
public class UserFacade implements IUserFacade {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Override
    public Object accountInfo() {
        return accountService.accountInfo();
    }

    @Override
    public Object userInfo() {
        return userService.userInfo();
    }
}
