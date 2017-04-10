package cn.ssha.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 申请表单
 * @author 黄中正
 *
 */
public class Application implements Serializable{
	private Integer id;
	private String title;       //申请标题
	private String docFilePath; //上传申请文档位置
	private User applicant;     //申请人
	private Date applyDate;    //申请时间
	private String status;      //申请状态
	private Set<ApproveInfo> approval;//审批实体
	private Template template;  //申请所使用的模板
	
	public final static String STATUS_APPROVING = "审批中";
	public final static String STATUS_UNAPPROVED = "未通过";
	public final static String STATUS_APPROVED = "已通过";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDocFilePath() {
		return docFilePath;
	}
	public void setDocFilePath(String docFilePath) {
		this.docFilePath = docFilePath;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applynDate) {
		this.applyDate = applynDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<ApproveInfo> getApproval() {
		return approval;
	}
	public void setApproval(Set<ApproveInfo> approval) {
		this.approval = approval;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
}
