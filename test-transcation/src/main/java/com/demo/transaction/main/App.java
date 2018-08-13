package com.demo.transaction.main;
import java.util.ArrayList;
import java.util.List;

import com.demo.transaction.pojo.Role;
import com.demo.transaction.service.RoleListService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String [] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext ("spring-cfg.xml");
		RoleListService roleListService = ctx.getBean(RoleListService. class);
		List<Role> roleList = new ArrayList<Role>();
        for (int i=1; i<=2; i++) {
            Role role = new Role();
            role.setRoleName("role_name_" + i);
            role.setNote("note_" + i);
            roleList.add(role);
        }
        int count = roleListService.insertRoleList(roleList);
	   System.out.println(count);
	}
}