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

@Service("questionService")
@Transactional
public class QuestionService {
	
	@Resource(name="questionDao")
	private QuestionDao questionDao;
	

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}


	public boolean addQuestion(Question question) {
		if(questionDao.findByTitle(question.getTitle())==null){
			questionDao.add(question);
			return true;
		}
		return false;
	}


	public List findAllByUserName(User user) {
		List list=questionDao.findAllByUserName(user);
		if(list!=null){
			return list;
		}
		return null;
	}


	public PageBean<Question> listPage(int currPage, int ps) {
		PageBean<Question> pageBean=new PageBean<>();
		//封装
		pageBean.setCurrPage(currPage);
		int pageSize=ps;
		pageBean.setPageSize(pageSize);
		int totalCount=questionDao.findCount();
		pageBean.setTotalCount(totalCount);
		Double totalPage=Math.ceil((double)totalCount/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//每页开始
		int begin=(currPage-1)*pageSize;
		List<Question> list=questionDao.findPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}


	public int delete(String ids) {
		int count=0;
		String[] id=ids.split(",");
		for (int i = 0; i < id.length; i++) {
			Question question = questionDao.findOne(Integer.valueOf(id[i]));
			if(question!=null){
				questionDao.delete(question);
				count++;
			}
		}
		return count;
	}

}
