package cn.ssha.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.ssha.bean.Application;
import cn.ssha.dao.ApplicationDao;
@Repository
public class ApplicationDaoImp extends HibernateDaoSupport implements ApplicationDao{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 添加申请
	 */
	@Override
	public void add(Application app) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(app);
	}
	/**
	 * 查找我的申请记录
	 */
	@Override
	public List<Application> findApplyList(DetachedCriteria dc) {
		List<Application> list = (List<Application>) this.getHibernateTemplate().findByCriteria(dc);
		return list;
	}
	/**
	 * 根据申请ID查找申请信息
	 */
	@Override
	public Application findApplyById(Integer applicationId) {
		return this.getHibernateTemplate().get(Application.class, applicationId);
	}

}
