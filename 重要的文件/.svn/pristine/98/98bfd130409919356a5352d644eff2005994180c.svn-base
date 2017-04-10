package cn.ssha.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.ssha.bean.ApproveInfo;
import cn.ssha.dao.ApproveDao;
@Repository
public class ApproveDaoImp extends HibernateDaoSupport implements ApproveDao{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 查看申请信息的流转记录
	 */
	@Override
	public List<ApproveInfo> findApplFlow(Integer applicationId) {
		String hql = "from ApproveInfo ai where ai.application.id = ? order by ai.approveDate";
		return (List<ApproveInfo>) this.getHibernateTemplate().find(hql, applicationId);
	}
	/**
	 * 保存审批处理
	 */
	@Override
	public void save(ApproveInfo ai) {
		this.getHibernateTemplate().save(ai);
		
	}

}
