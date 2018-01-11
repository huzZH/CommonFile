package cn.ssha.dao.imp;
import java.util.List;

/**
 * ����ģ��־ò�
 */
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.ssha.bean.Template;
import cn.ssha.dao.TemplateDao;
@Repository
public class TemplateDaoImp extends HibernateDaoSupport implements TemplateDao{
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * ��������ģ���б�
	 */
	@Override
	public List<Template> findTemplateList() {
		String hql = "From Template";
		List<Template> list = (List<Template>) this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	/**
	 * �������ģ��
	 */
	@Override
	public void addTemplate(Template template) {
		this.getHibernateTemplate().save(template);
	}
	/**
	 * ɾ������ģ��
	 */
	@Override
	public void delete(Template template) {
		this.getHibernateTemplate().delete(template);
	}
	/**
	 * ����ģ��ID����ģ��
	 */
	@Override
	public Template findTemplateById(Integer id) {
		return this.getHibernateTemplate().get(Template.class, id);
	}
	/**
	 * ��������ģ������
	 */
	@Override
	public void update(Template t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}
}
