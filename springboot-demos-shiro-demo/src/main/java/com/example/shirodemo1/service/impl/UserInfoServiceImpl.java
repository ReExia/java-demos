package com.example.shirodemo1.service.impl;

import com.example.shirodemo1.bean.UserInfo;
import com.example.shirodemo1.repository.UserInfoRepository;
import com.example.shirodemo1.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserInfo findByUsername(String name) {
		return userInfoRepository.findByUsername(name);
	}

}
