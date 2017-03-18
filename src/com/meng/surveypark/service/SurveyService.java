package com.meng.surveypark.service;

import java.util.List;

import com.meng.surveypark.model.Answer;
import com.meng.surveypark.model.Page;
import com.meng.surveypark.model.Question;
import com.meng.surveypark.model.Survey;
import com.meng.surveypark.model.User;

public interface SurveyService{

	//新建调查
	Survey newSurvey(User user);

	//查找我的调查方法
	List<Survey> findMySurveys(User user);

	//根据sid取得相应的survey
	Survey getSurvey(Integer sid);

	//根据sid取得相应的survey,并且同时携带所有孩子
	Survey getSurveyWithChildren(Integer sid);

	//更新survey
	void updateSurvey(Survey model);

	//保存/更新页
	void saveOrUpdatePage(Page model);

	//根据pid得到页
	Page getPage(Integer pid);

	//保存/更新问题
	void saveOrUpdateQuestion(Question model);

	//根据qid得到问题
	Question getQuestion(Integer qid);

	//删除问题，同时删除对应的答案
	void deleteQuestion(Integer qid);

	//删除页，同时删除对应的问题和答案
	void deletePage(Integer pid);

	//删除调查，同时删除对应的页，问题和答案
	void deleteSurvey(Integer sid);

	//清除调查答案
	void clearAnswers(Integer sid);
	
	//切换调查状态
	void toggleStatus(Integer sid);

	//更新LogoPhoto的路径
	void updateLogoPhotoPath(Integer sid, String path);

	//查找我的调查方法，并同时携带page孩子
	List<Survey> getSurveyWithPages(User user);

	//移动/复制页
	void moveOrCopyPage(Integer srcPid, Integer targetPid, int pos);

	//得到所有没关闭的调查
	List<Survey> findAllAvailableSurveys();

	//获得指定调查的第一页
	Page getFirstPage(Integer sid);

	//得到指定页面的前一页
	Page getPrePage(Integer currentPid);

	//得到指定页面的后一页
	Page getNextPage(Integer currentPid);

	//批量保存答案
	void saveAnswers(List<Answer> answers);

	//得到指定调查的全部问题
	List<Question> getQuestions(Integer sid);

	//得到指定调查的全部答案
	List<Answer> getAnswers(Integer sid);

}
