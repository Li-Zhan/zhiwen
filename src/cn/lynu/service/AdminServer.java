package cn.lynu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.dao.AdminDao;
import cn.lynu.model.Admin;

@Service("adminServer")
@Transactional
public class AdminServer {
	
	@Resource(name="adminDao")
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public Object login(Admin admin) {
		admin=adminDao.login(admin);
		if(admin!=null){
			return admin;
		}
		return null;
	}
	
	

}
