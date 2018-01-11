package cn.ssha.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;

public interface IProcessDefinService {

	public List<ProcessDefinition> findLastList();

	public void deploy(File resource);

	public void deleteProcess(String key);

	public InputStream showPng(String pdId);

	

}
