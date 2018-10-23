package com.springboot;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.dao.GroupRepository;
import com.springboot.dao.UserRepository;
import com.springboot.entity.Groups;
import com.springboot.entity.Users;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={App.class})
public class GroupTest {

	@Autowired
	private GroupRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void addGroup(){
		Groups groups=new Groups();
		groups.setName("工单组");
		this.repository.save(groups);
	}
	
	@Test
	public void addUser(){
		Groups groups = this.repository.findOne(4L);
		Users user=new Users(4L);
		List<Users> userList = groups.getUserList();
		userList.add(user);
		this.repository.saveAndFlush(groups);
	}
	
	@Test
	public void getUserList(){
		Groups groups = this.repository.findOne(4L);
		List<Users> userList = groups.getUserList();
		for(Users user :userList){
			System.out.println(user.getId()+"...."+user.getName());
		}
	}
	
	@Test
	public void removeUser(){
		Groups groups = this.repository.findOne(4L);
		List<Users> userList = groups.getUserList();
		Iterator<Users> ite = userList.iterator();
		while(ite.hasNext()){
			Users users = ite.next();
			if(users.getId()==4L)
				ite.remove();
		}
		this.repository.saveAndFlush(groups);
	}
}
