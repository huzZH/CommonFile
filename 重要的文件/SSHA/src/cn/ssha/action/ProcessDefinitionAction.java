package cn.ssha.action;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 流程管理
 */
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.ssha.service.IProcessDefinService;
@Controller
public class ProcessDefinitionAction extends ActionSupport{
	
	@Autowired
	private IProcessDefinService processDefinition;
	private File resource;
	private String key;
	private String pdId;
	
	
	public String getPdId() {
		return pdId;
	}
	public void setPdId(String pdId) {
		this.pdId = pdId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public File getResource() {
		return resource;
	}
	public void setResource(File resource) {
		this.resource = resource;
	}
	/**
	 * 查询最新版本的流程定义
	 */
	public String list(){
		List<ProcessDefinition> list = processDefinition.findLastList();
	//	ServletActionContext.getResponse().setContentType("test/html;charset=utf-8");
		ActionContext.getContext().put("list", list);
		return "list";
	}
	/**
	 * 跳转到部署流程界面
	 * @return
	 */
	public String deployUI(){
		return "deployUI";
	}
	/**
	 * 部署流程定义
	 */
	public String deploy(){
		processDefinition.deploy(resource);
		return "toList";
	}
	/**
	 * 删除流程部署
	 */
	public String delete(){
		processDefinition.deleteProcess(key);
		return "toList";
	}
	/**
	 * 查看流程图(文件下载)
	 */
	public String showPng(){
		InputStream in =  processDefinition.showPng(pdId);
		ActionContext.getContext().put("pngStream", in);
		return "showPng";
	}
	
	
	
}
