package com.example.shirodemo1.repository;

import com.example.shirodemo1.bean.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
	
	//通过用户名查找用户信息.
	public UserInfo findByUsername(String name);
}
