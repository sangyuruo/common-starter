package com.yishuifengxiao.common.autoconfigure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yishuifengxiao.common.properties.SecurityProperties;
import com.yishuifengxiao.common.security.endpoint.ExceptionAuthenticationEntryPoint;
import com.yishuifengxiao.common.security.handler.CustomAccessDeniedHandler;
import com.yishuifengxiao.common.security.handler.CustomAuthenticationFailureHandler;
import com.yishuifengxiao.common.security.handler.CustomAuthenticationSuccessHandler;
import com.yishuifengxiao.common.security.handler.CustomLogoutSuccessHandler;
import com.yishuifengxiao.common.security.processor.ProcessHandler;
import com.yishuifengxiao.common.security.processor.impl.DefaultProcessHandler;
/**
 * 配置spring security 处理器
 * @author yishui
 * @date 2019年10月18日
 * @version 1.0.0
 */
@Configuration
@ConditionalOnClass({ DefaultAuthenticationEventPublisher.class, EnableWebSecurity.class,
	WebSecurityConfigurerAdapter.class })
public class SecurityHandlerAutoConfiguration {

	/**
	 * 自定义属性配置
	 */
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 自定义处理
	 * 
	 * @param objectMapper
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ProcessHandler handlerProcessor(ObjectMapper objectMapper) {
		DefaultProcessHandler customHandle = new DefaultProcessHandler();
		customHandle.setObjectMapper(objectMapper);
		customHandle.setSecurityProperties(securityProperties);
		return customHandle;
	}

	/**
	 * 自定义登陆失败处理器
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public AuthenticationFailureHandler authenticationFailureHandler(ProcessHandler customHandle,
			ApplicationContext context) {
		CustomAuthenticationFailureHandler hanler = new CustomAuthenticationFailureHandler();
		hanler.setSecurityProperties(securityProperties);
		hanler.setCustomHandle(customHandle);
		hanler.setContext(context);
		return hanler;
	}

	/**
	 * 自定义登陆成功处理器
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public AuthenticationSuccessHandler authenticationSuccessHandler(ProcessHandler customHandle,
			ApplicationContext context) {
		CustomAuthenticationSuccessHandler hanler = new CustomAuthenticationSuccessHandler();
		hanler.setSecurityProperties(securityProperties);
		hanler.setCustomHandle(customHandle);
		hanler.setContext(context);
		return hanler;
	}

	/**
	 * 自定义退出成功处理器
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(ProcessHandler customHandle, ApplicationContext context) {
		CustomLogoutSuccessHandler hanler = new CustomLogoutSuccessHandler();
		hanler.setSecurityProperties(securityProperties);
		hanler.setCustomHandle(customHandle);
		hanler.setContext(context);
		return hanler;
	}

	/**
	 * 创建一个名字 exceptionAuthenticationEntryPoint 的token信息提示处理器
	 * 
	 * @return
	 */
	@Bean("exceptionAuthenticationEntryPoint")
	@ConditionalOnMissingBean(name = "exceptionAuthenticationEntryPoint")
	public AuthenticationEntryPoint exceptionAuthenticationEntryPoint(ProcessHandler customHandle,
			ApplicationContext context) {
		ExceptionAuthenticationEntryPoint point = new ExceptionAuthenticationEntryPoint();
		point.setCustomHandle(customHandle);
		point.setSecurityProperties(securityProperties);
		point.setContext(context);
		return point;
	}

	/**
	 * 权限拒绝处理器
	 * 
	 * @return
	 */
	@Bean("accessDeniedHandler")
	@ConditionalOnMissingBean(name = "accessDeniedHandler")
	public AccessDeniedHandler accessDeniedHandler(ProcessHandler customHandle, ApplicationContext context) {
		CustomAccessDeniedHandler handler = new CustomAccessDeniedHandler();
		handler.setSecurityProperties(securityProperties);
		handler.setCustomHandle(customHandle);
		handler.setContext(context);
		return handler;
	}

}
