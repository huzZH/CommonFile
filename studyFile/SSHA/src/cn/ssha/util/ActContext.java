package cn.ssha.util;

import org.apache.struts2.ServletActionContext;

import cn.ssha.bean.User;

public class ActContext {
	/**
	 * ��ȡ��ǰ��¼�û���
	 * @return
	 */
	public static User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}
