package com.sso.server.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sso.server.dao.UserRepository;
import com.sso.server.entity.SSOUser;
import com.sso.server.util.CommonResult;
import com.sso.server.util.CookieUtils;
import com.sso.server.util.JsonUtils;
import com.sso.server.util.RedisUtils;
import com.sso.server.util.ResultStatus;

import redis.clients.jedis.Jedis;


@Controller
public class LoginController {
	
	@Autowired
	UserRepository repository;
	
	@RequestMapping(value="/page/login",method=RequestMethod.GET)
	public String goLoginPage(@RequestParam("redirectUrl")String redirectUrl,HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("redirectUrl", redirectUrl);
		return "login";
	}
 
	@PostMapping(value="/login")
	@ResponseBody
	public CommonResult login(@RequestBody SSOUser ssoUser,HttpServletRequest request,HttpServletResponse response){
		List<SSOUser> users = this.repository.findAll();
		for(SSOUser user :users){
			if(user.getAppkey().equals(ssoUser.getAppkey()) && 
					user.getPassword().equals(ssoUser.getPassword())){
				//生成用户凭证
				String token=UUID.randomUUID().toString();
				//当前登录用户存放在缓存中
				user.setPassword(null);
				Jedis jedis = RedisUtils.getJedis();
				jedis.set(token, JsonUtils.objectToJson(user));
				//设置session的过期时间
				jedis.expire(token, 1800);
				//写cookie
				CookieUtils.setCookie(request, response, "TOKEN", token);
				return new CommonResult(ResultStatus.SUCCESS, user);
			}
		}
		return new CommonResult(ResultStatus.WARN, "用户名或密码错误");
	}
	
	@GetMapping(value="/token/{token}")
	@ResponseBody
	public CommonResult getUserByToken(@PathVariable("token") String token,HttpServletRequest request,
			HttpServletResponse response){
		
		Jedis jedis = RedisUtils.getJedis();
		String json = jedis.get(token);
		if(StringUtils.isEmpty(json)){
			
			return new CommonResult(ResultStatus.ERROR, "当前登录用户信息已过期");
		}
		SSOUser ssoUser = JsonUtils.jsonToPojo(json, SSOUser.class);
		//设置session的过期时间
		jedis.expire(token, 1800);
		
		return new CommonResult(ResultStatus.SUCCESS, ssoUser);
	}
	
	@GetMapping("/logout/{token}")
	public void logout(@PathVariable String token,HttpServletRequest request,
			HttpServletResponse response){
		//删除当前cookie的用户凭证
		CookieUtils.deleteCookie(request, response, "TOKEN");
		//删除redis中缓存
		Jedis jedis = RedisUtils.getJedis();
		jedis.del(token);
	}
	
}
