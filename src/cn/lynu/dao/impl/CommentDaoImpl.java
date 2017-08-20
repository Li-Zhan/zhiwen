package cn.lynu.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.lynu.dao.CommentDao;
import cn.lynu.model.Comment;
import cn.lynu.model.PageBean;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{

    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	public boolean addComment(Comment comment){
		this.getHibernateTemplate().save(comment);
		return true;
	}

//	public PageBean<Comment> showComment(Comment comment) {
////		String hql="from Comment where titleid=?";
////		return this.getHibernateTemplate().find(hql,comment.getTitleid());
//	}

	
//	public PageBean<Comment> showComment(Comment comment) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public int findCount(Comment comment) {
		List<Long> list = (List<Long>) this.getHibernateTemplate().find("select count(*) from Comment where titleid=?",comment.getTitleid());
		if(list.size()>0){
			return list.get(0).intValue(); 
		}
		return 0;
	}


	public List<Comment> findPage(int begin, int pageSize,Comment comment) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Comment.class);
		criteria.add(Restrictions.eq("titleid", comment.getTitleid()));   //指定根据titleid进行分页
		List<Comment> list = (List<Comment>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		return list;
	}
}
