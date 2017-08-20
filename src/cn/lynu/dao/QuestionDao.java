package cn.lynu.dao;

import java.util.List;

import cn.lynu.model.Question;
import cn.lynu.model.User;

public interface QuestionDao extends BaseDao<Question> {

	Question findByTitle(String title);

	List<Question> findAllByUserName(User user);
	
	List<Question> findByUser(User user);

	int findCount();

	List<Question> findPage(int begin, int pageSize);

}
