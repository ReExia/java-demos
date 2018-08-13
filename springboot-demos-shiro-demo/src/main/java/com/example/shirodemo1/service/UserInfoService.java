package com.example.shirodemo1.service;


import com.example.shirodemo1.bean.UserInfo;

public interface UserInfoService {
	
	public UserInfo findByUsername(String name);
	
}
