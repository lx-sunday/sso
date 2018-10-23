package com.sso.oa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sso.oa.utils.CookieUtils;
import com.sso.oa.utils.HttpClientUtil;

@Controller
public class IndexController {
	
	@Value("${sso.server.address}")
	private String ssoAddress;
	
	@Value("${sso.server.port}")
	private String ssoPort;

	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		//删除认证中心的cookie与redis缓存
		String token = CookieUtils.getCookieValue(request, "TOKEN");
		HttpClientUtil.doGet("Http://"+ssoAddress+":"+ssoPort+"/logout/"+token);
		return "redirect:/index";
	}
}
