package cn.lynu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.lynu.model.PageBean;
import cn.lynu.model.Question;
import cn.lynu.model.User;
import cn.lynu.service.QuestionService;
import cn.lynu.service.UserService;

@Controller("questionAction")
@Scope("prototype")
public class QuestionAction extends ActionSupport implements ModelDriven<Question> {

	private Question question = new Question();

	public Question getModel() {
		return question;
	}

	private int page;

	private int rows; // page和rows都是EasyUI分页请求的数据

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

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

	@Resource(name = "questionService")
	private QuestionService questionService;

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Resource(name = "userService")
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 添加问题
	 */
	public String addQuestion() throws Exception {

		String userName = ServletActionContext.getRequest().getParameter("userName");

		User user = userService.findByName(userName);
		if (user != null) {
			question.setUser(user);
			question.setDate(new Date());
			if (questionService.addQuestion(question)) {
				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
				ServletActionContext.getResponse().getWriter().write("true");
				return NONE;
			}
		}

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write("false");
		return NONE;
	}

	/**
	 * 根据用户查询其提出的问题
	 */
	public String findAllByUserName() throws Exception {

		String userName = ServletActionContext.getRequest().getParameter("userName");

		User user = userService.findByName(userName);
		if (user != null) {
			question.setUser(user);
			List<Question> list = questionService.findAllByUserName(user);
			String json = JSON.toJSONString(list);
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			return NONE;
		}

		// 所以返回一个值为false的JSON(不太优雅的操作)
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString("false")); // 为了在没有登录查询的时候不报错
		return NONE;
	}

	/**
	 * 根据用户查询其提出的问题(不返回content)
	 */
	public String findAllByUserName2() throws Exception {

		String userName = ServletActionContext.getRequest().getParameter("userName");

		User user = userService.findByName(userName);
		if (user != null) {
			question.setUser(user);
			List<Question> list = questionService.findAllByUserName(user);
			PropertyFilter filter=new PropertyFilter() {
				
				@Override
				public boolean apply(Object arg0, String arg1, Object arg2) {
					if(arg1.equalsIgnoreCase("content")){
						return false;
					}
					return true;
				}
			};
			String json = JSON.toJSONString(list,filter);
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			return NONE;
		}

		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString("false")); 
		return NONE;
	}

	/**
	 * 查询所有问题
	 */
	public String listPage() throws Exception {
		PageBean<Question> pageBean = questionService.listPage(page, rows);
		Map map = new HashMap();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());

		PropertyFilter filter = new PropertyFilter() { // 使用fastjson动态过滤指定字段

			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equalsIgnoreCase("content")) {
					return false;
				}
				return true;
			}
		};
		String json = JSON.toJSONString(map, filter);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}

	/**
	 * 删除问题
	 */
	public String delete() throws Exception {
		int data = questionService.delete(ids);
		String json = JSON.toJSONString(data);// 响应删除的记录数提示添加成功
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}

}
