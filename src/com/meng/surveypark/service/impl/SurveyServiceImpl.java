package com.meng.surveypark.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.model.Answer;
import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.Question;
import com.meng.surveypark.model.Survey;
import com.meng.surveypark.model.User;
import com.meng.surveypark.service.SurveyService;
import com.meng.surveypark.util.DataUtil;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
	
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;

	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;

	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;

	//新建调查
	public Survey newSurvey(User user) {
		Survey survey = new Survey();
		Page page = new Page();
		
		survey.setUser(user);
		survey.getPages().add(page);
		page.setSurvey(survey);
		
		surveyDao.saveEntity(survey);
		pageDao.saveEntity(page);
		return survey;
	}

	//查找我的调查集合
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}

	//根据sid取得相应的survey
	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}

	//根据sid取得相应的survey,并且同时携带所有孩子
	public Survey getSurveyWithChildren(Integer sid) {
//		Survey s = surveyDao.getEntity(sid);
		//高内聚低耦合原则，所以下行比上行好！
		Survey s = this.getSurvey(sid);
		//强行初始化pages和questions
		for(Page p:s.getPages())
			p.getQuestions().size();
		return s;
	}

	//更新survey
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}
	
	//保存/更新页
	public void saveOrUpdatePage(Page model)
	{
		pageDao.saveOrUpdateEntity(model);
	}

	//获得指定页
	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}

	//保存/更新问题
	public void saveOrUpdateQuestion(Question model)
	{
		questionDao.saveOrUpdateEntity(model);
	}
			
	//根据qid得到问题
	public Question getQuestion(Integer qid)
	{
		return questionDao.getEntity(qid);
	}

	//删除问题，同时删除对应的答应
	public void deleteQuestion(Integer qid) {
		//删除答案
		String hql = "delete from Answer a where a.questionId = ?";
		answerDao.BatchEntityByHQL(hql, qid);
		//删除问题
		hql = "delete from Question q where q.id = ?";
		questionDao.BatchEntityByHQL(hql, qid);
	}
	
	//删除页，同时删除对应的问题和答案
	public void deletePage(Integer pid)
	{
		//删除答案
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
		answerDao.BatchEntityByHQL(hql, pid);
		//删除问题
		hql = "delete from Question q where q.page.id = ?";
		questionDao.BatchEntityByHQL(hql, pid);
		//删除页
		hql = "delete from Page p where p.id = ?";
		pageDao.BatchEntityByHQL(hql, pid);
	}
	
	//删除调查，同时删除对应的页，问题和答案
	public void deleteSurvey(Integer sid)
	{
		//删除答案
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.BatchEntityByHQL(hql, sid);
		//删除问题
		//hibernate在写操作当中，不允许两级或两级以上的链接
//		hql = "delete from Question q where q.page.survey.id = ?";
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		questionDao.BatchEntityByHQL(hql, sid);
		//删除页
		hql = "delete from Page p where p.survey.id = ?";
		pageDao.BatchEntityByHQL(hql, sid);
		//删除调查
		hql = "delete from Survey s where s.id = ?";
		surveyDao.BatchEntityByHQL(hql, sid);
	}
	
	//清除调查答案
	public void clearAnswers(Integer sid)
	{
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.BatchEntityByHQL(hql, sid);
	}
	
	//切换调查状态
	public void toggleStatus(Integer sid)
	{
		Survey s = this.surveyDao.getEntity(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		surveyDao.BatchEntityByHQL(hql, !s.isClosed(), sid);
	}
	
	//更新LogoPhoto的路径
	public void updateLogoPhotoPath(Integer sid, String path)
	{
		String hql = "update Survey s set s.logoPhotoPath = ? where s.id = ?";
		surveyDao.BatchEntityByHQL(hql, path, sid);
	}
	
	//查找我的调查方法，并同时携带page孩子
	public List<Survey> getSurveyWithPages(User user)
	{
		String hql = "select s from Survey s where s.user.id = ?";
		List<Survey> list = surveyDao.findEntityByHQL(hql, user.getId());
		for(Survey s:list)
		{
			s.getPages().size();
		}
		return list;
	}
	
	//移动/复制页
	public void moveOrCopyPage(Integer srcPid, Integer targetPid, int pos)
	{
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page targetPage = this.getPage(targetPid);
		Survey targetSurvey = targetPage.getSurvey();
		//移动
		if(srcSurvey.getId()==targetSurvey.getId())
		{
			this.setOrderno(srcPage, targetPage, pos);
		}
		//复制
		else
		{
			//强行初始化问题集合，否则深度复制的页面对象没有问题集合
			srcPage.getQuestions().size();
			//深度复制
			Page copy = (Page) DataUtil.deeplyCopy(srcPage);
			//维持页面和目标调查的关联关系
			copy.setSurvey(targetSurvey);
			//保存页面
			pageDao.saveEntity(copy);
			//保存问题
			for(Question q:copy.getQuestions())
			{
				questionDao.saveEntity(q);
			}
			this.setOrderno(copy, targetPage, pos);
		}
	}
	
	//得到所有没关闭的调查
	public List<Survey> findAllAvailableSurveys()
	{
		String hql = "select s from Survey s where s.closed = ?";
		return surveyDao.findEntityByHQL(hql, false);
	}
	
	//获得指定调查的第一页
	public Page getFirstPage(Integer sid)
	{
		String hql = "select p from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, sid);
		Page p = list.get(0);
		//强行初始化页面问题和调查
		p.getQuestions().size();
		p.getSurvey().getTitle();
		return p;
	}
	
	//得到指定页面的前一页
	public Page getPrePage(Integer currentPid)
	{
		Page p = this.getPage(currentPid);
		p = this.getPrePage(p);
		//初始化问题
		p.getQuestions().size();
		return p;
	}

	//得到指定页面的后一页
	public Page getNextPage(Integer currentPid)
	{
		Page p = this.getPage(currentPid);
		p = this.getNextPage(p);
		//初始化问题
		p.getQuestions().size();
		return p;
	}
	
	//批量保存答案
	public void saveAnswers(List<Answer> answers)
	{
		Date date = new Date();
		String uuid = UUID.randomUUID().toString();
		for(Answer a : answers)
		{
			a.setAnswerTime(date);
			a.setUuid(uuid);
			answerDao.saveEntity(a);
		}
	}
	
	//得到指定调查的全部问题
	public List<Question> getQuestions(Integer sid)
	{
		String hql = "from Question q where q.page.survey.id = ?";
		return questionDao.findEntityByHQL(hql, sid);
	}
	
	//得到指定调查的全部答案
	public List<Answer> getAnswers(Integer sid)
	{
		String hql = "from Answer a where a.surveyId = ? order by a.uuid asc";
		return answerDao.findEntityByHQL(hql, sid);
	}

	//设置页序
	private void setOrderno(Page srcPage, Page targetPage, int pos) {
		//向前
		if(pos==0)
		{
			//如果目标页是该调查的第一页
			if(this.isFirstPage(targetPage))
			{
				srcPage.setOrderno(targetPage.getOrderno() - 0.01f);
			}
			else
			{
				//得到目标页的前一页
				Page prePage = this.getPrePage(targetPage);
				srcPage.setOrderno((prePage.getOrderno() + targetPage.getOrderno())/2);
			}
		}
		//向后
		else
		{
			//如果目标页是该调查的最后一页
			if(this.isLastPage(targetPage))
			{
				srcPage.setOrderno(targetPage.getOrderno() + 0.01f);
			}
			else
			{
				//得到目标页的后一页
				Page nextPage = this.getNextPage(targetPage);
				srcPage.setOrderno((nextPage.getOrderno() + targetPage.getOrderno())/2);
			}			
		}
	}

	//得到该调查目标页的后一页
	private Page getNextPage(Page targetPage) {
		String hql = "select p from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, targetPage.getSurvey().getId(), targetPage.getOrderno());
		return list.get(0);
	}

	//判断目标也是否为该调查的最后一页
	private boolean isLastPage(Page targetPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ?";
		Long count = (Long) pageDao.uniqueResult(hql, targetPage.getSurvey().getId(), targetPage.getOrderno());
		return count==0;
	}

	//得到该调查目标页的前一页
	private Page getPrePage(Page targetPage) {
		String hql = "select p from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc";
		List<Page> list = pageDao.findEntityByHQL(hql, targetPage.getSurvey().getId(), targetPage.getOrderno());
		return list.get(0);
	}

	//判断目标页是否为该调查的第一页
	private boolean isFirstPage(Page targetPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ?";
		Long count = (Long) pageDao.uniqueResult(hql, targetPage.getSurvey().getId(), targetPage.getOrderno());
		return count==0;
	}

}
