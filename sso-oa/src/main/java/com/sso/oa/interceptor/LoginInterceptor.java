package com.sso.oa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sso.oa.utils.CommonResult;
import com.sso.oa.utils.CookieUtils;
import com.sso.oa.utils.HttpClientUtil;
import com.sso.oa.utils.JsonUtils;
import com.sso.oa.vo.SSOUserVo;

public class LoginInterceptor implements HandlerInterceptor {

	@Value("${sso.server.address}")
	private String ssoAddress;
	
	@Value("${sso.server.port}")
	private String ssoPort;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//cookie中查找用户凭证token
		String token = CookieUtils.getCookieValue(request, "TOKEN");
		if(StringUtils.isNotEmpty(token)){
			String resultJson=HttpClientUtil.doGet("Http://"+ssoAddress+":"+ssoPort+"/token/"+token);
			//把json对象转为java对象
			CommonResult commonResult=JSON.parseObject(resultJson, CommonResult.class); 
			if(commonResult!=null && commonResult.getCode()==2000){
				JSONObject jo=(JSONObject) commonResult.getData();
				SSOUserVo uservo = jo.toJavaObject(SSOUserVo.class);
				if(uservo!=null){
					return true;
				}
			}
		}
		//重定向到认证中心的登录首页
		response.sendRedirect("Http://"+ssoAddress+":"+ssoPort+"/page/login?redirectUrl="+request.getRequestURL().toString());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}