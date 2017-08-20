package cn.lynu.dao;

import java.util.List;

import cn.lynu.model.User;

public interface UserDao extends BaseDao<User>{

	User findByName(String userName);

	User findByNameAndPass(User user);

	int findCount();

	List<User> findPage(int begin, int pageSize);

}
