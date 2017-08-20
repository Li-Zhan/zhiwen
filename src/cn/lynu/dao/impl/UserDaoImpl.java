package cn.lynu.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.lynu.dao.UserDao;
import cn.lynu.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	public User findByName(String userName) {
		String hql="from User where userName=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, userName);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public User findByNameAndPass(User user) {
		String hql="from User where userName=? and password=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getUserName(),user.getPassword());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public int findCount() {
		String hql="select count(*) from User";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}


	public List<User> findPage(int begin, int pageSize) {
		DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	
	
}
