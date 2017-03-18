package com.meng.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meng.surveypark.dao.BaseDao;
import com.meng.surveypark.model.Answer;
import com.meng.surveypark.model.Question;
import com.meng.surveypark.model.statistics.OptionStatisticsModel;
import com.meng.surveypark.model.statistics.QuestionStatisticsModel;
import com.meng.surveypark.service.StatisticsService;

//统计服务实现
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	
	//对指定问题进行统计
	public QuestionStatisticsModel statistics(Integer qid) {
		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		Question q = questionDao.getEntity(qid);
		qsm.setQuestion(q);
		
		//统计回答问题的人数
		String qhql = "select count(*) from Answer a where a.questionId = ?";
		Long count = (Long) questionDao.uniqueResult(qhql, qid);
		qsm.setCount(count.intValue());
		
		//统计各个选项的情况
		int qt = q.getQuestionType();
		String ohql = "select count(*) from Answer a where a.questionId = ? and concat(',', a.answerId, ',') like ?";
		Long ocount = null;
		switch(qt)
		{
			//非矩阵问题
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				String[] options = q.getOptionArr();
				OptionStatisticsModel osm = null;
				for(int i=0;i<options.length;i++)
				{
					osm = new OptionStatisticsModel();
					osm.setOptionIndex(i);
					osm.setOptionLabel(options[i]);
					ocount = (Long) answerDao.uniqueResult(ohql, qid, "%,"+ i +",%"); 
					osm.setCount(ocount.intValue());
					qsm.getOsms().add(osm);
				}
				if(q.isOther())
				{
					osm = new OptionStatisticsModel();
					osm.setOptionLabel("其他");
					ocount = (Long) answerDao.uniqueResult(ohql, qid, "%other%"); 
					osm.setCount(ocount.intValue());
					qsm.getOsms().add(osm);
				}
				break;
				
			//矩阵问题
			case 6:
			case 7:
			case 8:
				String[] rows = q.getMatrixRowTitleArr();
				String[] columns = q.getMatrixColumnTitleArr();
				String[] selects = q.getMatrixSelectOptionArr();
				for(int i=0;i<rows.length;i++)
				{
					for(int j=0;j<columns.length;j++)
					{
						if(qt!=8)
						{
							osm = new OptionStatisticsModel();
							osm.setMatrixRowIndex(i);
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixColumnIndex(j);
							osm.setMatrixColumnLabel(columns[j]);
							ocount = (Long) answerDao.uniqueResult(ohql, qid, "%,"+ i + "_"+ j +",%");
							osm.setCount(ocount.intValue());
							qsm.getOsms().add(osm);
						}
						else
						{
							for(int k=0;k<selects.length;k++)
							{
								osm = new OptionStatisticsModel();
								osm.setMatrixRowIndex(i);
								osm.setMatrixRowLabel(rows[i]);
								osm.setMatrixColumnIndex(j);
								osm.setMatrixColumnLabel(columns[j]);
								osm.setMatrixSelectIndex(k);
								osm.setMatrixSelectLabel(selects[k]);
								ocount = (Long) answerDao.uniqueResult(ohql, qid, "%,"+ i + "_"+ j + "_" + k + ",%");
								osm.setCount(ocount.intValue());
								qsm.getOsms().add(osm);
							}
						}
					}
				}
				break;
		}
		
		return qsm;
	}

}
