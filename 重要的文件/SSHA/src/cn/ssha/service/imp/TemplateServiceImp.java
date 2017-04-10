package cn.ssha.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssha.bean.Template;
import cn.ssha.dao.TemplateDao;
import cn.ssha.service.TemplateService;

/**
 * 流程模板业务层
 * @author 黄中正
 *
 */
@Service
@Transactional
public class TemplateServiceImp implements TemplateService{
	@Resource
	private TemplateDao templateDao;
	/**
	 * 查找流程模板列表
	 */
	@Override
	public List<Template> findTemplateList() {
		
		return templateDao.findTemplateList();
	}
	/**
	 * 获取文件保存路径
	 */
	@Override
	public String getFilePath(File resource,String resourceFileName) {
		String suffix = resourceFileName.substring(resourceFileName.indexOf("."));
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd/");
		String path = "G:" + format.format(date);
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//构造目标文件
		File target = new File(file.getPath() + File.separator + UUID.randomUUID().toString() + suffix);
		try {
			FileUtils.copyFile(resource, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("上传文件失败！");
		}
		return target.getPath() ;
	}
	/**
	 * 添加流程模板
	 */
	@Override
	public void addTemplate(Template template) {
		templateDao.addTemplate(template);
	}
	
	@Test
	public void test() throws IOException{
		File file = new File("G:\\abc.docx");
		File outFile = new File("G:\\bc.docx");
		byte b[] = new byte[1024];
		int len;
		try {
			FileInputStream in = new FileInputStream(file);
			FileOutputStream out = new FileOutputStream(outFile);
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * 删除流程模板,并同时删除文件
	 */
	@Override
	public void delete(Template template) {
		// TODO Auto-generated method stub
		Template t = templateDao.findTemplateById(template.getId());
		String docFilePath = t.getDocFilePath();
		File file = new File(docFilePath);
		//判断文件是否存在
		if(file.exists()){
			file.delete();
		}
		templateDao.delete(t);
	}
	/**
	 * 根据模板ID查找模板
	 */
	@Override
	public Template findTemplateById(Integer id) {
		// TODO Auto-generated method stub
		return templateDao.findTemplateById(id);
	}
	/**
	 * 更新流程模板数据
	 */
	@Override
	public void update(Template t) {
		// TODO Auto-generated method stub
		templateDao.update(t);
	}
	
}
