package com.yishuifengxiao.common.security.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义授权接口
 * 
 * @author yishui
 * @date 2019年1月9日
 * @version 0.0.1
 */
public class CustomToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5927640198972638101L;

	/**
	 * 登录用户的用户名
	 */
	private String username;

	/**
	 * 登录用户的 clientId
	 */
	private String clientId;

	/**
	 * 登录用户的角色
	 */
	private List<String> roles;

	/**
	 * 当前token的授权模式
	 */
	private String grantType;

	/**
	 * 获取登录用户的用户名
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置登录用户的用户名
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取 登录用户的 clientId
	 * 
	 * @return
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * 设置 登录用户的 clientId
	 * 
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * 获取登录用户的角色
	 * 
	 * @return
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * 设置登录用户的角色
	 * 
	 * @param roles
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * 获取当前token的授权模式
	 * 
	 * @return
	 */
	public String getGrantType() {
		return grantType;
	}

	/**
	 * 设置当前token的授权模式
	 * 
	 * @param grantType
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public CustomToken(String username, String clientId, List<String> roles, String grantType) {
		this.username = username;
		this.clientId = clientId;
		this.roles = roles;
		this.grantType = grantType;
	}

	public CustomToken() {

	}

	@Override
	public String toString() {
		return new StringBuffer("{\"username\":\"").append(username).append("\",\"clientId\":\"").append(clientId)
				.append("\",\"grantType\":\"").append(grantType).append("\"}").toString();
	}

}