package cn.ssha.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.ssha.bean.Template;
import cn.ssha.service.IProcessDefinService;
import cn.ssha.service.TemplateService;
import cn.ssha.util.DownloadUtils;

/**
 * 流程模板Action
 * @author 黄中正
 *
 */
@Controller
public class TemplateAction extends ActionSupport implements ModelDriven<Template>{
	
	@Autowired
	private TemplateService templateService;
	@Autowired
	private IProcessDefinService processDefinition;
	private Template template = new Template();
	
	private File resource;
	private String resourceFileName;
	private String myFileName;
	

	public String getMyFileName() {
		return myFileName;
	}

	public void setMyFileName(String myFileName) {
		this.myFileName = myFileName;
	}

	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}
	@Override
	public Template getModel() {
		// TODO Auto-generated method stub
		return template;
	}
	

	

	/**
	 * 查询所有流程模板
	 * @return
	 */
	public String list(){
		List<Template> list = templateService.findTemplateList();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	/**
	 * 跳转流程模板添加界面
	 */
	public String saveUI(){
		List<ProcessDefinition> list = processDefinition.findLastList();
		ActionContext.getContext().put("list", list);
		return "saveUI";
	}
	/**
	 * 添加流程模板
	 * @return
	 */
	public String addTemplate(){
		String docFilePath = templateService.getFilePath(resource,resourceFileName);
		template.setDocFilePath(docFilePath);
		templateService.addTemplate(template);
		return "toList";
	}
	/**
	 * 删除流程模板
	 */
	public String delete(){
		templateService.delete(template);
		return "toList";
	}
	/**
	 * 跳转到修改模板界面
	 */
	public String editUI(){
		//根据ID查询要修改的数据，用于数据回显
		Template t = templateService.findTemplateById(template.getId());
		ActionContext.getContext().getValueStack().push(t);
		//准备流程定义数据列表，用于填充下拉框
		List<ProcessDefinition> list = processDefinition.findLastList();
		ActionContext.getContext().put("list", list);
		return "editUI";
	}
	/**
	 * 修改流程模板
	 */
	public String updateTemplate(){
		Template t = templateService.findTemplateById(template.getId());
		t.setName(template.getName());
		t.setPdKey(template.getPdKey());
		//如果上传的文件不为空，则删除原文件
		if(resource != null){
			String docFilePath = t.getDocFilePath();
			File file = new File(docFilePath);
			if(file.exists()){
				file.delete();
			}
			String filePath = templateService.getFilePath(resource, resourceFileName);
			t.setDocFilePath(filePath);
		}
		templateService.update(t);
		return "toList";
	}
	/**
	 * 下载DOC文件
	 */
	public String downloadFile(){
		Template t = templateService.findTemplateById(template.getId());
		String docFilePath = t.getDocFilePath();
		InputStream inputStream = DownloadUtils.downloadFile(docFilePath);
		ActionContext.getContext().put("fileStream", inputStream);
		String fileName = t.getName() + ".doc";
//		myFileName = t.getName() + ".doc";     //可以通过提供get,set方法在xml文件中动态获取，或者放置在ActionContext中
		try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ActionContext.getContext().put("fileName", fileName);
		
		return "downloadFile";
	}
	
}
