package cn.lynu.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.lynu.dao.QuestionDao;
import cn.lynu.model.Question;
import cn.lynu.model.User;

@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {

    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	public Question findByTitle(String title) {
		String hql="from Question where title=?";
		List<Question> list = (List<Question>) this.getHibernateTemplate().find(hql, title);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public List<Question> findAllByUserName(User user) {
		
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery=session.createSQLQuery("select (select count(*) from comment where titleid=a.qid)as count, a.qid,a.title,a.content,a.date from question as a where user_id=? ORDER BY date DESC");
		sqlQuery.setInteger(0, user.getUid());
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));

		List<Question> list = sqlQuery.list();
		
		if(list.size()>0){
			return list;
		}
		return null;
	}
	
	public List<Question> findByUser(User user){
		String hql="from Question where user=?";
		List<Question> list = (List<Question>) this.getHibernateTemplate().find(hql, user);
		if(list.size()>0){
			return list;
		}
		return null;
	}


	public int findCount() {
		String hql="select count(*) from Question";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}


	public List<Question> findPage(int begin, int pageSize) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Question.class);
		List<Question> list = (List<Question>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		if(list.size()>0){
			for (Question question : list) {
				question.setUserName(question.getUser().getUserName());
			}
			return list;
		}
		return null;
	}

}
