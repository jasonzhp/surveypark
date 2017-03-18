package com.meng.surveypark.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Answer;
import com.meng.surveypark.model.Question;
import com.meng.surveypark.model.Survey;
import com.meng.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey> {

	private static final long serialVersionUID = -8333436117302297431L;

	//接收sid
	private Integer sid;
	
	@Resource
	private SurveyService surveyService;

	public String execute()
	{
		return SUCCESS;
	}
	
	public InputStream getIs()
	{	
		try {
			//映射问题ID与列索引的关系
			Map<Integer, Integer> qidIndexMap = new HashMap<>();
			List<Question> questions = surveyService.getQuestions(sid);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("收集调查");
			
			HSSFCellStyle style = wb.createCellStyle();
			//设置单元格自动换行
			style.setWrapText(true);
			
			//输出第一行问题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			Question q = null;
			for(int i=0;i<questions.size();i++)
			{
				q = questions.get(i);
				cell = row.createCell(i);
				cell.setCellValue(q.getTitle());
				cell.setCellStyle(style);
				//设置列宽
				sheet.setColumnWidth(i, 6000);
				qidIndexMap.put(q.getId(), i);
			}
			//输出答案
			String oldUuid = "";
			String newUuid = "";
			//行索引
			int rowIndex = 0;
			List<Answer> answers = surveyService.getAnswers(sid);
			for(Answer a:answers)
			{
				newUuid = a.getUuid();
				if(!newUuid.equals(oldUuid))
				{
					rowIndex++;
					row = sheet.createRow(rowIndex);
					oldUuid = newUuid;
				}
				cell = row.createCell(qidIndexMap.get(a.getQuestionId()));
				cell.setCellValue(a.getAnswerId());
				cell.setCellStyle(style);
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			wb.close();
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
}
