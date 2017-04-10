package cn.ssha.util;

import org.apache.struts2.ServletActionContext;

import cn.ssha.bean.User;

public class ActContext {
	/**
	 * 获取当前登录用户名
	 * @return
	 */
	public static User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}
