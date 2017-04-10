package cn.ssha.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssha.bean.Application;
import cn.ssha.bean.ApproveInfo;
import cn.ssha.bean.TaskView;
import cn.ssha.bean.User;
import cn.ssha.dao.ApproveDao;
import cn.ssha.service.ApproveService;
@Service
@Transactional
public class ApproveServiceImp implements ApproveService{
	/**
	 * 查找登录者的审批任务
	 */
	@Resource
	private ApproveDao approveDao;
	@Resource
	private ProcessEngine processEngine;
	@Override
	public List<TaskView> findTaskList(User loginUser) {
		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		query.taskAssignee(loginUser.getLoginName()); //添加过滤条件，查找当前的用户的任务
		query.orderByTaskCreateTime().asc();//添加排序
		List<Task> TaskList = query.list();
		List<TaskView> list = new ArrayList<TaskView>();
		for(Task task : TaskList){
			Application application = (Application) processEngine.getTaskService().getVariable(task.getId(), "application");
			TaskView taskView = new TaskView(task,application);
			list.add(taskView);
		}
		
		return list;
	}
	/**
	 * 查看申请信息的流转记录
	 */
	@Override
	public List<ApproveInfo> findApplFlow(Integer applicationId) {
		List<ApproveInfo> list = approveDao.findApplFlow(applicationId);
		return list;
	}
	/**
	 * 审批处理意见
	 */
	@Override
	public void save(ApproveInfo ai,String taskId) {
		Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		processEngine.getTaskService().complete(taskId);
		ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		/* 2、办理当前的任务
		 * 3、如果当前任务通过
		 * 
		 *        如果当前任务为最后一个人审批，则状态为已通过
		 * 
		 *    如果当前任务未通过
		 *    
		 *        将申请的状态修改为未通过
		 *        如果当前办理人不是最后一个人，手动结束流程*/
		approveDao.save(ai);
		Application application = ai.getApplication();
		if(ai.getApproval()){  //如果当前任务通过
			if(processInstance == null){ //如果当前任务为最后一个人审批，则状态为已通过
				application.setStatus(Application.STATUS_APPROVED);
			}
		}else{ //如果当前任务未通过
			application.setStatus(Application.STATUS_UNAPPROVED);
			if(processInstance != null){
				processEngine.getRuntimeService().deleteProcessInstance(processInstanceId, Application.STATUS_UNAPPROVED);
			}
			
			
		}
		
		
		
	}

}
