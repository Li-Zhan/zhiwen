package cn.lynu.dao;

import cn.lynu.model.Admin;

public interface AdminDao extends BaseDao<Admin>{

	Admin login(Admin admin);

}
