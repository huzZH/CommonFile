package cn.ssha.dao;

import java.util.List;

import cn.ssha.bean.ApproveInfo;

public interface ApproveDao {

	public List<ApproveInfo> findApplFlow(Integer applicationId);

	public void save(ApproveInfo ai);

}
