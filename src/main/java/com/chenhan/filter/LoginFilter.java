package com.chenhan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chenhan.config.CacheServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@WebFilter
@Slf4j
public class LoginFilter implements Filter{

	@Autowired
	private CacheServiceImpl cacheService;
	
//	private static Logger log = (Logger) LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		log.trace("LoginFilter======trace");  
        log.debug("LoginFilter======debug");  
        log.info("LoginFilter======info");  
        log.warn("LoginFilter======warn");  
        log.error("LoginFilter======error");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String uri = request.getRequestURI();//请求接口地址
		if(uri.indexOf("/login") != -1) {//不拦截登录，其他都拦截下来验证token，从缓存中验证
			//登录生成token
			
			
			chain.doFilter(servletRequest, servletResponse);
			return ;
		}
		
		String token = request.getHeader("token");
		try {
//			cacheService.validateToken(token);
			//注销登录不能刷新token,token有效期验证通过后，从jwt中获取用户信息
			if(!uri.equals("/logout")) {
//				String subject = JwtUtils.getSubject(headerToken);
//				UserDto user = JsonUtils.json2Object(subject, UserDto.class);
//				cacheService.refreshToken(token);
			}
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} 
		chain.doFilter(request, response);
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化自定义过滤器....");
		Filter.super.init(filterConfig);
	}
	
}
