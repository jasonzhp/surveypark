<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑调查</title>
    
	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
	<s:include value="header.jsp"></s:include>
	<table class="headerTable" width="100%">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;编辑调查</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
   				<s:form action="SurveyAction_updateSurvey" namespace="/" method="post">
   					<s:hidden name="id"></s:hidden>
   					<table class="simpleTable">
   						<tr>
   							<td class="tdFormLabel">调查标题：</td>
   							<td class="tdFormControl"><s:textfield name="title" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">“上一步”提示文本：</td>
   							<td class="tdFormControl"><s:textfield name="preText" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">“下一步”提示文本：</td>
   							<td class="tdFormControl"><s:textfield name="nextText" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">“完成”提示文本：</td>
   							<td class="tdFormControl"><s:textfield name="doneText" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">“退出”提示文本：</td>
   							<td class="tdFormControl"><s:textfield name="exitText" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel"></td>
   							<td class="tdFormControl"><s:submit value="确定" cssClass="btn"></s:submit></td>
   						</tr>
   					</table>
   				</s:form>
   			</td>
   		</tr>
   	</table>
  </body>
</html>
