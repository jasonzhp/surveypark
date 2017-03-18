<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>问题分析</title>
    
	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;矩阵式问题结果分析</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdDesignQuestion"><s:property value="%{qsm.question.title}" /></td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
   				<table class="simpleTable">
   					<tr>
   						<td class="tdList"></td>
   						<s:iterator value="%{qsm.question.matrixColumnTitleArr}">
   							<td class="tdList"><s:property /></td>
   						</s:iterator>
   					</tr>
   					<s:iterator value="%{qsm.question.matrixRowTitleArr}" var="row" status="rowst">
	   					<tr>
	   						<td class="tdList"><s:property /></td>
	   						<s:iterator value="%{qsm.question.matrixColumnTitleArr}" var="column" status="columnst">
	   							<td class="tdList"><s:property value="getScale(#rowst.index, #columnst.index)" /></td>
	   						</s:iterator>
	   					</tr>
   					</s:iterator>
   				</table>
   			</td>
   		</tr>
		<tr>
			<td class="tdList">共有&nbsp;<s:property value="%{qsm.count}" />&nbsp;人参与问卷。</td>
		</tr>
   	</table>
  </body>
</html>
