package com.demo.transaction.mapper;

import com.demo.transaction.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
	public int insertRole(Role role);
}