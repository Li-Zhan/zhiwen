package cn.lynu.action;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.lynu.model.PageBean;
import cn.lynu.model.User;
import cn.lynu.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();

	public User getModel() {
		return user;
	}

	@Resource(name = "userService")
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private int page;
	
	private int rows;     //page和rows都是EasyUI分页请求的数据
	
	private String ids;    //可以删除多条数据,数据之间以,分割

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * 注册用户
	 * 
	 */
	public String addUser() throws Exception {
		String password = user.getPassword();
		user.setPassword(DigestUtils.md5Hex(password.getBytes()));
		if (userService.addUser(user)) {
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write("true");
			return NONE;
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write("false");
		return NONE;
	}

	/**
	 * 验证用户是否存在
	 */
	public String isUser() throws Exception {
		if (userService.isUser(user)) {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write("false"); // 响应false已通知验证插件存在相同用户（验证不通过）
			return NONE;
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write("true"); // true表示验证通过
		return NONE;
	}

	/**
	 * 用户登录
	 */
	public String loginUser() throws Exception {
		String password = user.getPassword();
		user.setPassword(DigestUtils.md5Hex(password.getBytes()));
		user = userService.loginUser(user);
		if (user != null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write("true");
			return NONE;
		}

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write("false");
		return NONE;
	}
	
	/**
	 * 显示用户列表
	 * 
	 */
	public String listPage()throws Exception{
		PageBean<User> pageBean=userService.listPage(page,rows);
		Map map=new HashMap();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());
		PropertyFilter filter=new PropertyFilter() {  //使用fastjson动态过滤指定字段 
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equalsIgnoreCase("question")){
					return false;
				}
				return true;
			}
		};
		String json = JSON.toJSONString(map,filter);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return NONE;
	}
	
	/**
	 * 删除用户
	 */
	public String delete()throws Exception{
		int data=userService.delete(ids);
		String json=JSON.toJSONString(data);//响应删除的记录数提示添加成功
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}
	
	/**
	 * 根据id查询用户 
	 */
	public String findOne()throws Exception{
		user=userService.findOne(user.getUid());
		if(user!=null){
			String json=JSON.toJSONString(user);
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		}
		return NONE;
	}
	
	/**
	 * 修改用户
	 */
	public String update()throws Exception{
		user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));
		if(userService.update(user)){
			String json=JSON.toJSONString("1");//响应1提示添加成功
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		}
		return NONE;
	}
	
	/**
	 * 根据名称查询
	 */
	public String findByName() throws Exception {
		user = userService.findByName(user.getUserName());
		if(user!=null){
			String json = JSON.toJSONString(user);
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		}
		String json = JSON.toJSONString("false");
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}
	

}
