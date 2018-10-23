package com.sso.pro.vo;

import java.io.Serializable;

public class SSOUserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5287331678737403385L;

	private Long id;
	
	private String appkey;
	
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}