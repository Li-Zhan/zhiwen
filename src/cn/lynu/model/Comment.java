package cn.lynu.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Comment {
	private Integer cid;
	
	private Integer titleid;
	
	private String user;
	
	private String comment;
	
	@JSONField(format="yyyy-MM-dd")
	private Date date;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getTitleid() {
		return titleid;
	}

	public void setTitleid(Integer titleid) {
		this.titleid = titleid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
