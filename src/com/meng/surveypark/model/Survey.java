package com.meng.surveypark.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 调查类
 */
public class Survey extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6642610867203722808L;

	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();
	
	//该调查的最小页序
	private Float minOrderno;
	//该调查的最大页序
	private Float maxOrderno;
	
	//logo路径
	private String logoPhotoPath;
	
	//是否关闭
	private boolean closed;

	private Set<Page> pages = new HashSet<>();
	
	//映射从 Survey 到 User 的多对一的关联关系
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}

	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}

	public Float getMinOrderno() {
		return minOrderno;
	}

	public void setMinOrderno(Float minOrderno) {
		this.minOrderno = minOrderno;
	}

	public Float getMaxOrderno() {
		return maxOrderno;
	}

	public void setMaxOrderno(Float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}

}
