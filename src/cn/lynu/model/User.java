package cn.lynu.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	private Integer uid;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private Byte sex;
	
	//通过注解对日期转化为JSON进行格式化
	@JSONField (format="yyyy-MM-dd")  
	private Date date;
	
	private Set question=new HashSet();

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set getQuestion() {
		return question;
	}

	public void setQuestion(Set question) {
		this.question = question;
	}
	

}
