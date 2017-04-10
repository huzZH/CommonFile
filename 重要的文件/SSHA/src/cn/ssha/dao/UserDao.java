package cn.ssha.dao;

import cn.ssha.bean.User;

public interface UserDao {

	public User login(String loginName, String password);
	
}
