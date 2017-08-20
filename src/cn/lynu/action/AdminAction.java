package cn.lynu.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.lynu.model.Admin;
import cn.lynu.service.AdminServer;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{

	private Admin admin=new Admin();
	
	public Admin getModel() {
		return admin;
	}
	
	@Resource(name="adminServer")
	private AdminServer adminServer;

	public void setAdminServer(AdminServer adminServer) {
		this.adminServer = adminServer;
	}
	
	public String login()throws Exception{
		if(adminServer.login(admin)!=null){
			ServletActionContext.getRequest().getSession().setAttribute("adminName", admin.getAdminName());
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write("1");
			return NONE;
		}
		return NONE;
	}
	
	public String exitLogin(){
		if(ServletActionContext.getRequest().getSession().getAttribute("adminName")!=null){
			ServletActionContext.getRequest().getSession().removeAttribute("adminName");
		}
		return "exitlogin_success";
	}


}
