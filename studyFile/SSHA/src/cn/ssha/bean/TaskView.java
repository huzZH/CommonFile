package cn.ssha.bean;

import org.activiti.engine.task.Task;

public class TaskView {
	private Task task;
	private Application application;
	public TaskView(){}
	public TaskView(Task task,Application application){
		this.task = task;
		this.application = application;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
}
