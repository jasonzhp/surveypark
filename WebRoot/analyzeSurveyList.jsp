<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>分析调查</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
	<s:include value="header.jsp"></s:include>
	<table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine" colspan="2"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader" colspan="2">&nbsp;分析调查</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine" colspan="2"></td>
   		</tr>
   		<tr>
   			<td colspan="2" class="tdDesignSurvey">&nbsp;<s:property value="title" /></td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine" colspan="2"></td>
   		</tr>
   		<s:iterator value="pages" var="p">
   			<tr>
   				<td colspan="2" class="tdDesignPage">&nbsp;<s:property value="#p.title" /></td>
   			</tr>
   			<tr>
   				<td width="30px" class="tdList"></td>
	   			<td class="tdList">
		   			<s:iterator value="#p.questions" var="q" status="qst">
		   				<s:set value="#q.id" var="qid"></s:set>
		   				<s:set value="#q.questionType" var="qt"></s:set>
	   					<table class="simpleTable">
	   						<tr>
	   							<td class="tdDesignQuestion"><s:property value="#qst.index + 1" />.<s:property value="#q.title" /></td>
	   							<td class="tdDesignQuestion" width="500px" style="padding-top:20px;">
	   								<!-- 判断是否是矩阵式问题 -->
	   								<s:if test="#qt > 5">
	   									<s:form action="MatrixStatisticsAction" method="post" namespace="/" target="blank">
	   										<s:hidden name="qid" value="%{#qid}"></s:hidden>
	   											<!-- 提交给另一个action，改变form的提交地址 -->
	   											<s:submit value="查看矩阵式问题统计结果"></s:submit>
	   									</s:form>
	   								</s:if>
	   								<s:elseif test="#qt < 5">
		   								<s:form action="ChartOutputAction" namespace="/" method="post" target="blank">
		   									<s:hidden name="qid" value="%{#qid}"></s:hidden>
		   										<s:set var="chartList" value="#{0:'平面饼图',
		   																		1:'立体饼图',
		   																		2:'横向平面柱状图',
		   																		3:'纵向平面柱状图',
		   																		4:'横向立体柱状图',
		   																		5:'纵向立体柱状图',
		   																		6:'平面折线图',
		   																		7:'立体折线图'}"></s:set>
		   										<s:select name="chartType" list="#chartList" listKey="key" listValue="value"></s:select>
		   										<s:submit value="查看" cssClass="btn"></s:submit>
		   								</s:form>
	   								</s:elseif>
	   							</td>
	   						</tr>
	   					</table>
	   				</s:iterator>
	   			</td>
   			</tr>
   		</s:iterator>
   	</table>
  </body>
</html>
