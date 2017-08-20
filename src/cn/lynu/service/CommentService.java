package cn.lynu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.dao.CommentDao;
import cn.lynu.model.Comment;
import cn.lynu.model.PageBean;

@Service("commentService")
@Transactional
public class CommentService {
	
	@Resource(name="commentDao")
	private CommentDao commentDao;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public boolean addComment(Comment comment) {
		if(commentDao.addComment(comment)){
			return true;
		}
		return false;
	}

	public PageBean<Comment> showComment(Comment comment, int currPage) {
		
		
        PageBean<Comment> pageBean = new PageBean<>();
        // 封装pageBean
        pageBean.setCurrPage(currPage);
        int pageSize = 2;         //每页两条数据
        pageBean.setPageSize(pageSize);
        int totalCount = commentDao.findCount(comment);
        pageBean.setTotalCount(totalCount);
        Double totalPage = Math.ceil((double) totalCount / pageSize);
        pageBean.setTotalPage(totalPage.intValue());
        int begin = (currPage - 1) * pageSize;
        List<Comment> list = commentDao.findPage(begin, pageSize,comment);
        pageBean.setList(list);
        return pageBean;
//		
//		
//		List list=commentDao.showComment(comment);
//		if(list!=null){
//			return list;
//		}
//		return null;
	}

}
