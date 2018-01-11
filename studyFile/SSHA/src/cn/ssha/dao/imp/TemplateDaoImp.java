package cn.ssha.dao.imp;
import java.util.List;

/**
 * 流程模板持久层
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
	 * 查找流程模板列表
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
	 * 添加流程模板
	 */
	@Override
	public void addTemplate(Template template) {
		this.getHibernateTemplate().save(template);
	}
	/**
	 * 删除流程模板
	 */
	@Override
	public void delete(Template template) {
		this.getHibernateTemplate().delete(template);
	}
	/**
	 * 根据模板ID查找模板
	 */
	@Override
	public Template findTemplateById(Integer id) {
		return this.getHibernateTemplate().get(Template.class, id);
	}
	/**
	 * 更新流程模板数据
	 */
	@Override
	public void update(Template t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}
}
