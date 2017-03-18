<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设计调查</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css"> 

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <s:set value="id" var="sId"></s:set>
   	<table class="headerTable">
 		<tr>
   			<td colspan="2" class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td colspan="2" class="tdHeader">&nbsp;设计调查</td>
   		</tr>
   		<tr>
   			<td colspan="2" class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdDesignSurvey">
   				<!-- Logo -->
   				<s:if test="logoExists()">
   					<img src="<s:url value="%{logoPhotoPath}" />" height="25px" width="50px">
   				</s:if>
   				<!-- 调查标题 -->
   				&nbsp;<s:property value="title"/>
   			</td>
   			<td align="right" class="tdDesignSurvey">
   				<s:a action="SurveyAction_toAddLogoPhoto?sid=%{#sId}">增加logo</s:a>&nbsp;
   				<s:a action="SurveyAction_editSurvey?sid=%{#sId}">编辑调查</s:a>&nbsp;
   				<s:a action="PageAction_toAddPage?sid=%{#sId}">增加页</s:a>&nbsp;
   			</td>
   		</tr>
   		<tr>
   			<td colspan="2">
   				<table class="simpleTable">
   					<tr>
   						<td width="30px" class="tdList">&nbsp;</td>
   						<td width="*" class="tdList">
   							<table class="simpleTable">
   								<!-- 迭代页面集合 -->
   								<s:iterator value="pages" var="p">
   									<s:set value="#p.id" var="pId"></s:set>
   									<tr>
   										<!-- 页面标题 -->
   										<td class="tdDesignPage"><s:property value="#p.title"/></td>
   										<td align="right" class="tdDesignPage">
   											<s:a action="PageAction_editPage?sid=%{#sId}&pid=%{#pId}" namespace="/">编辑页标题</s:a>&nbsp;
   											<s:a action="MoveOrCopyPageAction_toSelectTargetPage?srcPid=%{#pId}" namespace="/">移动/复制页</s:a>&nbsp;
   											<s:a action="QuestionAction_toSelectQuestionType?sid=%{#sId}&pid=%{#pId}" namespace="/">增加问题</s:a>&nbsp;
   											<s:a action="PageAction_deletePage?sid=%{#sId}&pid=%{#pId}" namespace="/">删除页</s:a>&nbsp;
   										</td>
   									</tr>
   									<tr>
   										<td colspan="2">
   											<table class="simpleTable">
   												<tr>
   													<td width="30px" class="tdList">&nbsp;</td>
   													<td width="*" class="tdList">
   														<table class="simpleTable">
   															<!-- 迭代问题集合 -->
   															<s:iterator value="#p.questions" var="q">
   															<s:set value="#q.id" var="qId"></s:set>
																<tr>
																	<!-- 题目 -->
																	<td class="tdDesignQuestion"><s:property value="#q.title"></s:property></td>
																	<td class="tdDesignQuestion" align="right">
   																		<s:a action="QuestionAction_editQuestion?sid=%{#sId}&pid=%{#pId}&qid=%{#qId}" namespace="/">编辑问题</s:a>&nbsp;
   																		<s:a action="QuestionAction_deleteQuestion?sid=%{#sId}&qid=%{#qId}" namespace="/">删除问题</s:a>&nbsp;
   																	</td>
   																</tr>
   																<!-- 选项 -->
   																<tr>
	   																<td colspan="2">
	   																	<!-- 定义题型变量 -->
	   																	<s:set value="#q.questionType" var="qt"></s:set>
	   																	
	   																	<!-- 当题型为0,1,2,3时 
	   																			 0.非矩阵式横向单选按钮
																				 1.非矩阵式纵向单选按钮
																				 2.非矩阵式横向复选按钮
																				 3.非矩阵式纵向复选按钮
	   																	-->
	   																	<s:if test="#qt < 4">
	   																		<s:iterator value="#q.optionArr">
	   																			<input type='<s:property value="#qt < 2?'radio':'checkbox'" />' /><s:property/>&nbsp;
	   																			<s:if test="#qt == 1||#qt ==3"><br /></s:if>
	   																		</s:iterator>
	   																		<!-- 判断是否有其他项 -->
	   																		<s:if test="#q.other">
	   																			<input type='<s:property value="#qt < 2?'radio':'checkbox'" />' />其他
	   																			<!-- 其他项为文本框 -->
	   																			<s:if test="#q.otherType == 1">
	   																				<input type="text" />
	   																			</s:if>
	   																			<!-- 其他项为下拉列表 -->
	   																			<s:elseif test="#q.otherType == 2">
	   																				<select>
	   																					<s:iterator value="#q.otherSelectOptionArr">
	   																						<option><s:property/></option>
	   																					</s:iterator>
	   																				</select>
	   																			</s:elseif>
	   																		</s:if>
	   																	</s:if>	
	   																	
	   																	<!-- 当题型为4时 
	   																			4.非矩阵式下拉列表
	   																	-->
	   																	<s:elseif test="#qt == 4">
	   																		<select>
	   																			<s:iterator value="#q.optionArr">
	   																				<option><s:property/></option>
	   																			</s:iterator>
	   																		</select>
	   																	</s:elseif>
	   																	
	   																	<!-- 当题型为5时 
	   																			5.非矩阵式文本框
	   																	-->
	   																	<s:elseif test="#qt == 5">
	   																		<input type="text" />
	   																	</s:elseif>
	   																	
	   																	<!-- 当题型为6,7,8时
	   																			 6.矩阵式单选按钮
																				 7.矩阵式复选按钮
																				 8.矩阵式下拉列表
	   																	 -->
	   																	<s:elseif test="#qt > 5">
	   																		<table class="simpleTable">
	   																			<tr>
	   																				<td class="tdList"></td>
	   																				<!-- 列头 -->
	   																				<s:iterator value="#q.matrixColumnTitleArr">
	   																					<td class="tdList"><s:property/></td>
	   																				</s:iterator>
	   																			</tr>
	   																				<s:iterator value="#q.matrixRowTitleArr">
		   																				<tr>
			   																				<!-- 行头 -->
		   																					<td class="tdList"><s:property/></td>
		   																					<s:iterator value="#q.matrixColumnTitleArr">
		   																						<td class="tdList">
		   																							<s:if test="#qt == 6"><input type="radio" /></s:if>
		   																							<s:elseif test="#qt == 7"><input type="checkbox" /></s:elseif>
		   																							<s:elseif test="#qt == 8">
		   																								<select>
		   																									<s:iterator value="#q.matrixSelectOptionArr">
		   																										<option><s:property/></option>
		   																									</s:iterator>
		   																								</select>
		   																							</s:elseif>
		   																						</td>
		   																					</s:iterator>
		   																				</tr>
	   																				</s:iterator>
	   																		</table>
	   																	</s:elseif>
	   																</td>
	   															</tr>
   															</s:iterator>
   														</table>
   													</td>
   												</tr>
   											</table>
   										</td>
   									</tr>
   								</s:iterator>
   							</table>
   						</td>
   					</tr>
   				</table>
   			</td>
   		</tr>
   	</table>
  </body>
</html>
