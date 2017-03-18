<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的调查</title>
    
	<link rel="stylesheet" type="text/css" href="css/styles.css"> 
	<script type="text/javascript" src='<s:url value="/jquery-1.12.1.js" />'></script>
	<script type="text/javascript">
		$(function(){
			//点击删除时，弹出 是否确认删除 窗口
			$("#delete").click(function(){
			 	alert(123);
			 	var surveyTitle = $(this).next(":input").val();
				var flag = confirm("确定要删除调查："+ surveyTitle +"");
				if(flag)
				{
					//删除，使用ajax方式
					
				}
				//取消超链接的默认行为
				return false;
			});
		});
	</script>

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <s:if test="mySurveys.isEmpty()"><p align="center">目前没有任何调查项!</p></s:if>
    <s:else>
    	<table class="headerTable">
   	 		<tr>
    			<td colspan="10" class="tdWhiteLine"></td>
    		</tr>
    		<tr>
    			<td colspan="10" class="tdHeader">我的调查：</td>
    		</tr>
    		<tr>
    			<td class="tdListHeader">ID</td>
    			<td class="tdListHeader">调查标题</td>
    			<td class="tdListHeader">创建时间</td>
    			<td class="tdListHeader">状态</td>	
    			<td class="tdListHeader">设计</td>
    			<td class="tdListHeader">收集信息</td>
    			<td class="tdListHeader">分析</td>
    			<td class="tdListHeader">打开/关闭</td>
    			<td class="tdListHeader">清除调查</td>
    			<td class="tdListHeader">删除</td>
    		</tr>
    		<s:iterator value="mySurveys" status="st"> 
    		<s:set value="id" var="sId"></s:set>
    			<tr>
    				<td class="tdList"><s:property value="#st.index + 1" /></td>
    				<td class="tdList"><s:property value="title"/></td>
    				<!-- 时间格式里 hh是12小时制，HH是24小时制 -->
    				<td class="tdList"><s:date name="createTime" format="MM/dd/yyyy HH:mm"/></td>
    				<td class="tdList">
    					<s:if test="closed">关闭</s:if>
    					<s:else>开放</s:else>
    				</td>
    				<td class="tdList"><s:a action="SurveyAction_designSurvey?sid=%{#sId}" namespace="/" cssClass="aList">设计</s:a></td>
    				<td class="tdList"><s:a action="CollectionSurveyAction?sid=%{#sId}" namespace="/" cssClass="aList">收集信息</s:a></td>
    				<td class="tdList"><s:a action="SurveyAction_analyzeSurvey?sid=%{#sId}" namespace="/" cssClass="aList">分析</s:a></td>
    				<td class="tdList"><s:a action="SurveyAction_toggleStatus?sid=%{#sId}" namespace="/" cssClass="aList">打开/关闭</s:a></td>
    				<td class="tdList"><s:a action="SurveyAction_clearAnswers?sid=%{#sId}" namespace="/" cssClass="aList">清除调查</s:a></td>
    				<td class="tdList">
    					<a href="SurveyAction_deleteSurvey?sid=${sId}" class="aList" id="delete">删除</a>
    					<s:hidden name="lastName" value="%{title}"></s:hidden>
    				</td>
    			</tr>
    		</s:iterator>
    	</table>
    </s:else>
  </body>
</html>
