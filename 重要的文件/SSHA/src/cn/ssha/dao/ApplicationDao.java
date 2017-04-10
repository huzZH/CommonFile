package cn.ssha.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.ssha.bean.Application;
import cn.ssha.bean.User;

public interface ApplicationDao {

	public void add(Application app);

	public List<Application> findApplyList(DetachedCriteria dc);

	public Application findApplyById(Integer applicationId);

}
