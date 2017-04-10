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
	 * 跳转到index.jsp界面
	 */
	public String index(){
		user = userService.login(user);
		if(user != null){
			ActionContext.getContext().getSession().put("loginUser", user);
			return "index";
		}else{
			this.addActionError("用户名或密码错误！");
			return "login";
		}
		
	}
	/**
	 * index.jsp的页面布局
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
	 * 登录到index首界面
	 */
	public String login(){
		return "login";
	}
	/**
	 * 退出
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
}
