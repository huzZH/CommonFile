package cn.ssha.service.imp;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssha.bean.Application;
import cn.ssha.dao.ApplicationDao;
import cn.ssha.service.ApplicationService;
@Service
@Transactional
public class ApplicationServiceImp implements ApplicationService{
	
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private ApplicationDao applicationDao;
	@Override
	public void add(Application app) {
		
		// 2、保存一个申请实体
				applicationDao.add(app);// 持久对象
				// 3、启动一个流程实例
				String processDefinitionKey = app.getTemplate().getPdKey();
				Map<String, Object> variables = new HashMap<String, Object>();
				variables.put("application", app);
				variables.put("applicationId", app.getId()); 
				ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, variables);
				// 4、办理第一个提交任务
				TaskQuery query = processEngine.getTaskService().createTaskQuery();
				query.taskAssignee(app.getApplicant().getLoginName());
				query.processInstanceId(pi.getId());
				Task task = query.singleResult();
				String taskId = task.getId();
				processEngine.getTaskService().complete(taskId);
		
	}
	/**
	 * 查找我的申请列表
	 */
	@Override
	public List<Application> findApplyList(DetachedCriteria dc) {
		return applicationDao.findApplyList(dc);
	}
	/**
	 * 根据申请ID查找申请信息
	 */
	@Override
	public Application findApplyById(Integer applicationId) {
		// TODO Auto-generated method stub
		return applicationDao.findApplyById(applicationId);
	}
	/**
	 * 根据绑定的流程变量查询流程任务
	 */
	@Override
	public Task findTaskByApplicationId(Integer applicationId) {
		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		query.processVariableValueEquals("applicationId", applicationId);
		return query.singleResult();
	}
	/**
	 * 查询流程定义对象
	 */
	@Override
	public ProcessDefinition findPDByTask(Task task) {
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		query.processDefinitionId(processDefinitionId);
		return query.singleResult();
	}
	//获取当前任务的坐标
	@Override
	public Map<String, Object> getCoordingByTask(Task task) {
		/**
		 * 总结：
		 * 1、根据任务查询流程实例ID,查询流程定义ID
		 * 2、根据流程定义ID获得流程定义的实体（封装了详细的流程信息，比数据库中的要多）
		 * 3、根据流程实例ID获得流程实例
		 * 4、根据流程实例获得当前的任务活动节点ID
		 * 5、根据流程定义的实体获得获得活动节点实体
		 * 6、根据活动节点实体获得具体的坐标信息
		 */
		
		
		String processDefinitionId = task.getProcessDefinitionId();
		String processInstanceId = task.getProcessInstanceId();
		// 返回的流程定义对象中包含坐标信息
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(processDefinitionId);
		ProcessInstanceQuery query = processEngine.getRuntimeService().createProcessInstanceQuery();
		query.processInstanceId(processInstanceId);						
		ProcessInstance ps = query.singleResult();
		//获取当前任务的执行节点
		String activityId = ps.getActivityId();
		ActivityImpl activiti = pd.findActivity(activityId);
		int x = activiti.getX();
		int y = activiti.getY();
		int height = activiti.getHeight();
		int width = activiti.getWidth();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("height", height);
		map.put("width", width);
		return map;
	}
	/**
	 * 根据流程部署ID和图片名来获取图片流
	 */
	@Override
	public InputStream getPngStream(String deploymentId, String imageName) {
		return processEngine.getRepositoryService().getResourceAsStream(deploymentId, imageName);
	}

}
