package cn.ssha.service;

import java.io.File;
import java.util.List;

import cn.ssha.bean.Template;

public interface TemplateService {

	public List<Template> findTemplateList();

	public String getFilePath(File resource, String resourceFileName);

	public void addTemplate(Template template);

	public void delete(Template template);

	public Template findTemplateById(Integer id);

	public void update(Template t);

}
