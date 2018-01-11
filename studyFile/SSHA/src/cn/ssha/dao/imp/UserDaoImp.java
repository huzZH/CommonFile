package cn.ssha.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.ssha.bean.User;
import cn.ssha.dao.UserDao;
@Repository
public class UserDaoImp extends HibernateDaoSupport implements UserDao  {

    @Resource
    public void setHiber(SessionFactory sessionFactory){
    	super.setSessionFactory(sessionFactory);
    }

	@Override
	public User login(String loginName, String password) {
		String hql = "from User where loginName = ? and password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, loginName,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
