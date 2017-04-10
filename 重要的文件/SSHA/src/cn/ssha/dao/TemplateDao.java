package cn.ssha.dao;

import java.util.List;

import cn.ssha.bean.Template;

public interface TemplateDao {

	public List<Template> findTemplateList();

	public void addTemplate(Template template);

	public void delete(Template template);

	public Template findTemplateById(Integer id);

	public void update(Template t);

}
