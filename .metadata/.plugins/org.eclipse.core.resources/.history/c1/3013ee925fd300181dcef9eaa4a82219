package com.springboot.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 当前实体中ManyToOne与OneToMany对应的实体都是本身
 * @author lx
 *
 */
@Entity
@Table(name="category")
public class Category implements Serializable{

	private static final long serialVersionUID = 3056901912384376245L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	/**
	 * 关系的拥有方(即Many的一方)负责关系的维护，在拥有方建立外键，会用到JoinColumn
	 */
	@ManyToOne
	@JoinColumn(name="pid")
	private Category parent;
	
	/**
	 * 用于双向关联，由one的一方指向many的一方，同时这个属性为one的一方在many一方的属性值
	 */
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	private List<Category> childList;

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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChildList() {
		return childList;
	}

	public void setChildList(List<Category> childList) {
		this.childList = childList;
	}
	
}
