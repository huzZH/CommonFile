package cn.ssha.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.hibernate.criterion.DetachedCriteria;

import cn.ssha.bean.Application;
import cn.ssha.bean.User;

public interface ApplicationService {

	public void add(Application app);

	public List<Application> findApplyList(DetachedCriteria dc);

	public Application findApplyById(Integer applicationId);

	public Task findTaskByApplicationId(Integer applicationId);

	public ProcessDefinition findPDByTask(Task task);

	public Map<String, Object> getCoordingByTask(Task task);

	public InputStream getPngStream(String deploymentId, String imageName);

}
