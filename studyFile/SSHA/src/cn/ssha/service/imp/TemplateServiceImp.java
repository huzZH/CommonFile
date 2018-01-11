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
 * ����ģ��ҵ���
 * @author ������
 *
 */
@Service
@Transactional
public class TemplateServiceImp implements TemplateService{
	@Resource
	private TemplateDao templateDao;
	/**
	 * ��������ģ���б�
	 */
	@Override
	public List<Template> findTemplateList() {
		
		return templateDao.findTemplateList();
	}
	/**
	 * ��ȡ�ļ�����·��
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
		//����Ŀ���ļ�
		File target = new File(file.getPath() + File.separator + UUID.randomUUID().toString() + suffix);
		try {
			FileUtils.copyFile(resource, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ϴ��ļ�ʧ�ܣ�");
		}
		return target.getPath() ;
	}
	/**
	 * �������ģ��
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
	 * ɾ������ģ��,��ͬʱɾ���ļ�
	 */
	@Override
	public void delete(Template template) {
		// TODO Auto-generated method stub
		Template t = templateDao.findTemplateById(template.getId());
		String docFilePath = t.getDocFilePath();
		File file = new File(docFilePath);
		//�ж��ļ��Ƿ����
		if(file.exists()){
			file.delete();
		}
		templateDao.delete(t);
	}
	/**
	 * ����ģ��ID����ģ��
	 */
	@Override
	public Template findTemplateById(Integer id) {
		// TODO Auto-generated method stub
		return templateDao.findTemplateById(id);
	}
	/**
	 * ��������ģ������
	 */
	@Override
	public void update(Template t) {
		// TODO Auto-generated method stub
		templateDao.update(t);
	}
	
}
