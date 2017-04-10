package cn.ssha.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssha.bean.User;
import cn.ssha.dao.UserDao;
import cn.ssha.service.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService{
	
	@Resource
	private UserDao userDao;
	@Override
	public User login(User user) {
		return userDao.login(user.getLoginName(),user.getPassword());
	}

}
