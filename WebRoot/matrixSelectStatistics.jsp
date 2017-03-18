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
   			<td class="tdList">
   				<!-- 创建左上角颜色快，用不同颜色代表不同下拉列表选项 -->
   				<s:iterator value="%{qsm.question.matrixSelectOptionArr}" status="st">
   					<!-- 对文本框进行修饰，产生颜色快的特效 -->
   					<input type="text" readonly="readonly" 
   							style="border:1px solid blue;background-color:<s:property value='colors[#st.index]' />;width:12px;height:12px" />
   					<s:property />&nbsp;
   				</s:iterator>
   			</td>
   		</tr>
   		<tr>
   			<td class="tdList">
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
	   							<td class="tdList">
	   								<s:iterator value="%{qsm.question.matrixSelectOptionArr}" status="optst">
	   									<input type="text" readonly="readonly" 
	   										style="border:1px solid <s:property value='colors[#optst.index]' />;
	   												background-color:<s:property value='colors[#optst.index]' />;
	   												width:<s:property value='getPercent(#rowst.index, #columnst.index, #optst.index)' />px;
	   												height:12px" />
	   									<s:property value="getScale(#rowst.index, #columnst.index, #optst.index)" />
	   									<br>
	   								</s:iterator>
	   							</td>
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
