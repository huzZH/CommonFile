package cn.ssha.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssha.service.IProcessDefinService;
@Service
@Transactional
public class IProcessDefinServiceImp implements IProcessDefinService{
	@Resource
	private ProcessEngine processEngine;
	/**
	 * 查询最新版本的流程定义列表
	 */
	@Override
	public List<ProcessDefinition> findLastList() {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		//按照版本升序排序
		query.orderByProcessDefinitionVersion().asc();
		List<ProcessDefinition> list = query.list();
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition pd : list) {
			map.put(pd.getKey(), pd);
		}
		return new ArrayList<ProcessDefinition>(map.values());
	}
	/**
	 * 部署流程定义
	 */
	@Override
	public void deploy(File resource) {
		DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
		ZipInputStream zipInputStream = null;
		
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(resource),Charset.defaultCharset());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		deployment.addZipInputStream(zipInputStream);
		deployment.deploy();
	}
	/**
	 * 删除流程
	 */
	@Override
	public void deleteProcess(String key) {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		//根据key查询流程定义列表
		query.processDefinitionKey(key);
		List<ProcessDefinition> list = query.list();
		for(ProcessDefinition pd : list){
			processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);
		}
	}
	/**
	 * 查看流程图
	 */
	@Override
	public InputStream showPng(String pdId) {
		return processEngine.getRepositoryService().getProcessDiagram(pdId);
	}
	
}
