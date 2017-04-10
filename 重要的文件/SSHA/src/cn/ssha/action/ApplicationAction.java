package cn.ssha.action;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
/**
 * 申请Action
 */
import com.opensymphony.xwork2.ActionSupport;

import cn.ssha.bean.Application;
import cn.ssha.bean.ApproveInfo;
import cn.ssha.bean.TaskView;
import cn.ssha.bean.Template;
import cn.ssha.service.ApplicationService;
import cn.ssha.service.ApproveService;
import cn.ssha.service.TemplateService;
import cn.ssha.util.ActContext;
import cn.ssha.util.DownloadUtils;
@Controller
public class ApplicationAction extends ActionSupport{
	
	@Autowired
	private TemplateService templateService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private ApproveService approveService;
	private Integer templateId;
	private File resource;
	private String resourceFileName;
	private Integer applicationId;
	private String status;
	private String imageName;
	private String deploymentId;
	private String taskId;
	private Boolean approval;
	private String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getApproval() {
		return approval;
	}
	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public File getResource() {
		return resource;
	}
	public void setResource(File resource) {
		this.resource = resource;
	}
	
	
	

	public String getResourceFileName() {
		return resourceFileName;
	}
	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	/**
	 * 起草申请
	 */
	public String draftApply(){
		List<Template> list = templateService.findTemplateList();
		ActionContext.getContext().put("list", list);
		return "draftApply";
	}
	/**
	 * 跳转到submitUI界面 
	 */
	public String submitUI(){
		return "submitUI";
	}
	/**
	 * 添加申请
	 */
	public String submit(){
		System.out.println(templateId);
		System.out.println(resourceFileName);
		System.out.println(resource.getName());
		Template template = templateService.findTemplateById(templateId);
		
		Application app = new Application();
		String filePath = templateService.getFilePath(resource, resourceFileName);
		app.setDocFilePath(filePath);
		app.setApplicant(ActContext.getLoginUser());
		app.setApplyDate(new Date());
		app.setStatus(Application.STATUS_APPROVING);
		app.setTemplate(template);
		String title = template.getName() + "_" + ActContext.getLoginUser().getName() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(app.getApplyDate());
		app.setTitle(title);
		applicationService.add(app);
		return "toDraftApply";
	}
	/**
	 * 跳转到我的申请列表
	 */
	public String mySubmittedList(){
		//离线查询
		DetachedCriteria dc = DetachedCriteria.forClass(Application.class);
		dc.addOrder(Order.desc("applyDate"));
		dc.add(Restrictions.eq("applicant", ActContext.getLoginUser())); //添加过滤条件
		if(status != null && status.trim().length() > 0){
			dc.add(Restrictions.eq("status", status));
		}
		List<Application> list = applicationService.findApplyList(dc);
		ActionContext.getContext().put("list", list);
		return "mySubmittedList";
	}
	/**
	 * 查看申请信息
	 */
	public String showForm(){
		
		return "showForm";
	}
	/**
	 * 下载申请文件信息
	 */
	public String downloadFile(){
		Application app = applicationService.findApplyById(applicationId);
		String docFilePath = app.getDocFilePath();
		InputStream inputStream = DownloadUtils.downloadFile(docFilePath);
		ActionContext.getContext().put("fileStream", inputStream);
		String fileName = app.getTitle() + ".docx";
		try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActionContext.getContext().put("fileName", fileName);
		return "downloadFile";
	}
	/**
	 * 显示申请流程信息
	 */
	public String showPng(){
		//根据流程变量查询当前执行的任务
		Task task = applicationService.findTaskByApplicationId(applicationId);
		//查询流程定义对象
		ProcessDefinition pd = applicationService.findPDByTask(task);
		//将信息值传递给image.jsp
		ActionContext.getContext().put("deploymentId", pd.getDeploymentId());
		ActionContext.getContext().put("imageName", pd.getDiagramResourceName());
		//获取当前流程执行的坐标
		Map<String,Object> map = applicationService.getCoordingByTask(task);
		ActionContext.getContext().put("acs", map);
		return "showPng";
	}
	/**
	 * 显示流程执行图片信息
	 */
	public String viewImage(){
		InputStream in = applicationService.getPngStream(deploymentId,imageName);
		ActionContext.getContext().put("pngStream", in);
		return "viewImage";
	}
	
	/**
	 * 我的审批任务列表
	 */
	public String myTaskList(){
		List<TaskView> list = approveService.findTaskList(ActContext.getLoginUser());
		ActionContext.getContext().put("list", list);
		return "myTaskList";
	}
	/**
	 * 申请信息流转
	 */
	public String approveFlow(){
		List<ApproveInfo> list = approveService.findApplFlow(applicationId);
		ActionContext.getContext().put("list", list);
		return "approveFlow";
	}
	/**
	 * 跳转到审批处理界面
	 */
	public String approveUI(){
		return "approveUI";
	}
	/**
	 * 提交审批意见
	 */
	public String SubmitApprove(){
		/**
		 * 1、首先包装一个审批处理然后保存
		 * 2、办理当前的任务
		 * 3、如果当前任务通过
		 * 
		 *        如果当前任务为最后一个人审批，则状态为已通过
		 * 
		 *    如果当前任务未通过
		 *    
		 *        将申请的状态修改为未通过
		 *        手动结束流程
		 */
		System.out.println(applicationId);
		Application application = applicationService.findApplyById(applicationId);
		ApproveInfo ai = new ApproveInfo();
		ai.setApplication(application);
		ai.setApproveDate(new Date());
		ai.setComment(comment);
		ai.setApproval(approval);
		ai.setApprover(ActContext.getLoginUser());
		approveService.save(ai,taskId);
		
		return "toMyTaskList";
	}
}
