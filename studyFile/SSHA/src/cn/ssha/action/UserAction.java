package cn.ssha.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.ssha.bean.User;
import cn.ssha.service.UserService;
@Controller
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	@Autowired
	private UserService userService;
	
	/**
	 * ��ת��index.jsp����
	 */
	public String index(){
		user = userService.login(user);
		if(user != null){
			ActionContext.getContext().getSession().put("loginUser", user);
			return "index";
		}else{
			this.addActionError("�û������������");
			return "login";
		}
		
	}
	/**
	 * index.jsp��ҳ�沼��
	 */
	public String top(){
		return "top";
	}
	public String bottom(){
		return "bottom";
	}
	public String left(){
		return "left";
	}
	public String right(){
		return "right";
	}
	/**
	 * ��¼��index�׽���
	 */
	public String login(){
		return "login";
	}
	/**
	 * �˳�
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
}
