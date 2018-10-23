package com.springboot.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Groups implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6655151439368207897L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	/**
	 * 表关联，通过JoinTable创建关联表
	 */
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="group_user",
	           joinColumns={@JoinColumn(name="group_id",referencedColumnName="id")},
	   inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")}
	)
	private List<Users> userList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Users> getUserList() {
		if(userList==null){
			userList=new ArrayList<Users>();
		}
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}
	
	
}

