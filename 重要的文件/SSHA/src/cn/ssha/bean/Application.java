package cn.ssha.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * �����
 * @author ������
 *
 */
public class Application implements Serializable{
	private Integer id;
	private String title;       //�������
	private String docFilePath; //�ϴ������ĵ�λ��
	private User applicant;     //������
	private Date applyDate;    //����ʱ��
	private String status;      //����״̬
	private Set<ApproveInfo> approval;//����ʵ��
	private Template template;  //������ʹ�õ�ģ��
	
	public final static String STATUS_APPROVING = "������";
	public final static String STATUS_UNAPPROVED = "δͨ��";
	public final static String STATUS_APPROVED = "��ͨ��";
	
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
