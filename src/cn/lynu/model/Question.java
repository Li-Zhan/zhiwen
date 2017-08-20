package cn.lynu.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Question {
	private Integer qid;
	
	private String title;
	
	private String content;
	
	private String userName;         //解决Struts2验证出现input(在问题发布功能的时候)
	
	//通过注解对日期转化为JSON进行格式化
	@JSONField (format="yyyy-MM-dd")  
	private Date date;
	
	@JSONField(serialize=false) 
	private User user;

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
