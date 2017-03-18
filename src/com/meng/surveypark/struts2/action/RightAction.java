package com.meng.surveypark.struts2.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meng.surveypark.model.security.Right;
import com.meng.surveypark.service.RightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = -5246003514182854246L;
	
	private List<Right> allRights = new ArrayList<>();
	
	private Integer rightId; 
	
	@Resource
	private RightService rightService;

	//查找所有的权限
	public String findAllRights()
	{
		allRights = rightService.findAllEntities(); 
		return "rightListPage";
	}
	
	//到达添加权限页面
	public String toAddRightPage()
	{
		return "addRightPage";
	}
	
	//保存/更新权限
	public String saveOrUpdateRight()
	{
		this.rightService.saveOrUpdateRight(model);
		return "rightListAction";
	}
	
	//修改权限
	public String editRight()
	{
		this.model = this.rightService.getEntity(rightId);
		return "toEditRightPage";
	}
	
	//删除权限
	public String deleteRight()
	{
		Right r = new Right();
		r.setId(rightId);
		this.rightService.deleteEntity(r);
		return "rightListAction";
	}
	
	//批量更新权限
	public String batchUpdateRights()
	{
		rightService.batchUpdateRights(allRights);
		return "rightListAction";
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	
}
