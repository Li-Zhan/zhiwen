package cn.lynu.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.lynu.model.Comment;
import cn.lynu.model.PageBean;
import cn.lynu.service.CommentService;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ActionSupport implements ModelDriven<Comment> {

	private Comment comment = new Comment();

	public Comment getModel() {
		return comment;
	}

	@Resource(name = "commentService")
	private CommentService commentService;

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	// 分页(当前页)
	private int currPage;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	/**
	 * 添加评论
	 */
	public String addComment() throws Exception {
		comment.setDate(new Date());
		if (commentService.addComment(comment)) {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write("true");
			return NONE;
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write("false");
		return NONE;
	}

	/**
	 * 显示评论
	 */
	public String showComment() throws Exception {
		
			PageBean<Comment> pageBean = commentService.showComment(comment, currPage);
			boolean ststus = false; // 为了在没有评论的时候不报错
			if (pageBean != null) {
				String json = JSON.toJSONString(pageBean);
				ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
				ServletActionContext.getResponse().getWriter().write(json);
				return NONE;
			}

		// 所以返回一个值为false的JSON(不太优雅的操作)
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(ststus));
		return NONE;
	}


}
