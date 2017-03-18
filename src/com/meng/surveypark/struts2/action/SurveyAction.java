package com.meng.surveypark.struts2.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.Survey;
import com.meng.surveypark.model.User;
import com.meng.surveypark.service.SurveyService;
import com.meng.surveypark.struts2.UserAware;
import com.meng.surveypark.util.ValidateUtil;

//调查Action
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware, ServletContextAware{

	private static final long serialVersionUID = -7663913725531471702L;

	@Resource
	private SurveyService surveyService;
	
	//我的调查集合
	private List<Survey> mySurveys;

	//接收User对象
	private User user;

	//接收调查ID
	private Integer sid;
	
	//上传的文件
	File logoPhoto;
	//上传的文件的文件名
	String logoPhotoFileName;

	//接收ServletContext对象
	private ServletContext sc;
	
	//动态指定错误页面
	private String errorPage;
	
	//查询我的调查集合
	public String mySurveys()
	{
		mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	//新建调查
	public String newSurvey()
	{
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	//设计调查
	public String designSurvey()
	{
//		this.model = surveyService.getSurvey(sid);
		//为了解决懒加载问题，强行初始化代理对象
		this.model = surveyService.getSurveyWithChildren(sid);		
		return "designSurveyPage";
	}
	
	//编辑调查
	public String editSurvey()
	{
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	//该方法只在updateSurvey方法前执行
	public void prepareUpdateSurvey()
	{
		this.errorPage = "/editSurvey.jsp";
	}
	
	//更新调查
	public String updateSurvey()
	{
		this.sid = model.getId();
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	//删除调查
	public String deleteSurvey()
	{
		surveyService.deleteSurvey(sid);
		return "findMySurveyAction";
	}
	
	//清除调查答案
	public String clearAnswers()
	{
		surveyService.clearAnswers(sid);
		return "findMySurveyAction";
	}
	
	//切换调查状态
	public String toggleStatus()
	{
		surveyService.toggleStatus(sid);
		return "findMySurveyAction";
	}
	
	//跳转到选择logo的页面
	public String toAddLogoPhoto()
	{
		return "addLogoPhotoPage";
	}
	
	//该方法只在doAddLogo方法前执行
	public void prepareDoAddLogo()
	{
		this.errorPage = "/addLogoPhoto.jsp";
	}
	
	//增加Logo
	public String doAddLogo()
	{
		if(ValidateUtil.isValidate(logoPhotoFileName))
		{
			//1.实现上传
			//upload文件夹的真实路径
			String dir = sc.getRealPath("/upload");
			//扩展名
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//纳秒时间作为扩展名
			long l = System.nanoTime();
			File newFile = new File(dir, l + ext);
			//文件另存为
			logoPhoto.renameTo(newFile);
			
			//2.更新路径
			surveyService.updateLogoPhotoPath(sid, "/upload/" + l + ext);
		}
		return "designSurveyAction";
	}
	
	//分析调查
	public String analyzeSurvey()
	{
		this.model = surveyService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage";
	}
	
	//判断Logo是否存在
	public boolean logoExists()
	{
		String path = this.model.getLogoPhotoPath();
		if(ValidateUtil.isValidate(path))
		{
			String realPath = sc.getRealPath(path);
			File file = new File(realPath);
			return file.exists();
		}
		return false;
	}
	
//	//该方法会在 designSurvey 方法前执行
//	public void prepareDesignSurvey()
//	{
////		this.model = surveyService.getSurvey(sid);
//		//为了解决懒加载问题，强行初始化代理对象
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}
		
	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	//注入User对象
	public void setUser(User user) {
		this.user = user;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	//注入ServletContext对象
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}


	
}
