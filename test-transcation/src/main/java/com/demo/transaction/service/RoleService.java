package com.demo.transaction.service;

import com.demo.transaction.pojo.Role;

import java.util.List;


public interface RoleService {
	
	public int insertRole(Role role);
	
	public int insertRoleList(List<Role> roleList);
	
	public int insertRoleList2(List<Role> roleList);
}
