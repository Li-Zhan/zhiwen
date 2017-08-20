package cn.lynu.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.lynu.dao.AdminDao;
import cn.lynu.model.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

    @Resource(name="sessionFactory")
    public void setSF(SessionFactory sf){
        super.setSessionFactory(sf);
    }

	public Admin login(Admin admin) {
		List<Admin> list = (List<Admin>) this.getHibernateTemplate().find("from Admin where adminName=? and password=?",admin.getAdminName(),admin.getPassword());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
