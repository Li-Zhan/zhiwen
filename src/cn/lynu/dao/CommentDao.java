package cn.lynu.dao;

import java.util.List;

import cn.lynu.model.Comment;
import cn.lynu.model.PageBean;

public interface CommentDao extends BaseDao<Comment> {

	boolean addComment(Comment comment);

	//PageBean<Comment> showComment(Comment comment);

	int findCount(Comment comment);

	List<Comment> findPage(int begin, int pageSize,Comment comment);

}
