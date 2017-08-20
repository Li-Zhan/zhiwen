package cn.lynu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.dao.QuestionDao;
import cn.lynu.dao.UserDao;
import cn.lynu.model.PageBean;
import cn.lynu.model.Question;
import cn.lynu.model.User;

@Service("userService")
@Transactional
public class UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Resource(name="questionDao")
	private QuestionDao questionDao;
	
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}


	public boolean addUser(User user) {
		if(userDao.findByName(user.getUserName())==null){
			userDao.add(user);
			return true;
		}else{
			return false;
		}
	}


	public boolean isUser(User user) {
		if(userDao.findByName(user.getUserName())!=null){
			return true;
		}
		return false;
	}


	public User loginUser(User user) {
		user=userDao.findByNameAndPass(user);
		if(user!=null){
			return user;
		}
		return null;
	}


	public User findByName(String userName) {
		return userDao.findByName(userName);
	}


	public PageBean<User> listPage(int currPage,int ps) {
		PageBean<User> pageBean=new PageBean<>();
		//封装
		pageBean.setCurrPage(currPage);
		int pageSize=ps;
		pageBean.setPageSize(pageSize);
		int totalCount=userDao.findCount();
		pageBean.setTotalCount(totalCount);
		Double totalPage=Math.ceil((double)totalCount/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//每页开始
		int begin=(currPage-1)*pageSize;
		List<User> list=userDao.findPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	public int delete(String ids) {
		int count=0;                //记录删除的记录
		String[] id=ids.split(",");
		for(int i=0;i<id.length;i++){
			User user = userDao.findOne(Integer.valueOf(id[i]));
			if(user!=null){
				List<Question> questions = questionDao.findByUser(user);
				if(questions.size()>0){
					for (Question question : questions) {
						questionDao.delete(question);
					}
				}
				userDao.delete(user);
				count++;
			}
		}
		return count;
	}


	public User findOne(Integer uid) {
		User user = userDao.findOne(uid);
		if(user!=null){
			return user;
		}
		return null;
	}


	public boolean update(User user) {
		userDao.update(user);
		return true;
	}

}
