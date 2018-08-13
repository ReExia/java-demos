package com.demo.transaction.service.impl;

import java.util.List;

import com.demo.transaction.mapper.RoleMapper;
import com.demo.transaction.pojo.Role;
import com.demo.transaction.service.RoleService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImpl implements RoleService, ApplicationContextAware {
	
	@Autowired
	private RoleMapper roleMapper = null;
	
	
	private ApplicationContext ctx = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public int insertRole(Role role) {
		return roleMapper.insertRole(role);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public int insertRoleList(List<Role> roleList) {
		int count = 0;
		for (Role role : roleList) {
			try {
				insertRole(role);
				count++;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED)
	public int insertRoleList2(List<Role> roleList) {
		int count = 0;
		RoleService service = ctx.getBean(RoleService.class);
		for (Role role : roleList) {
			try {
				service.insertRole(role);
				count++;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
	}
}