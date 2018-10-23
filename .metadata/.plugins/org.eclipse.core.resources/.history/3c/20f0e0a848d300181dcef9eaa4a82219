package com.springboot;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.dao.CategoryRepository;
import com.springboot.entity.Category;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class CategoryTest {

	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void addFirst(){
		Category bean=new Category();
		bean.setName("快递问题");
		this.repository.save(bean);
	}
	
	@Test
	public void findchild(){
		Category parent = this.repository.findOne(1L);
		List<Category> childList = parent.getChildList();
		if(childList!=null){
			for(Category child :childList){
				System.out.println(child.getId()+"....."+child.getName());
			}
		}
		
	}
	
}
