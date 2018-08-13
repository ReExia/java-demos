package com.example.provider.facadeImpl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.api.facade.IUserFacade;
import com.example.provider.service.AccountService;
import com.example.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserFacadeImpl implements IUserFacade {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Override
    public Object accountInfo() {
        return accountService.accuntInfo();
    }

    @Override
    public Object userInfo() {
       return userService.userInfo();
    }
}
