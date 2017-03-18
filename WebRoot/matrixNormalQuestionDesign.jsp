<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>设计问题</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;矩阵型问题设计</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
   				<s:form action="QuestionAction_saveOrUpdateQuestion" namespace="/" method="post">
   					<s:hidden name="id"></s:hidden>
   					<s:hidden name="pid"></s:hidden>
   					<s:hidden name="sid"></s:hidden>
   					<s:hidden name="questionType"></s:hidden>
   					<table class="simpleTable">
   						<tr>
   							<td class="tdFormLabel">问题题目：</td>
   							<td class="tdFormControl"><s:textfield name="title" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">行标题标签组：</td>
   							<td class="tdFormControl"><s:textarea name="matrixRowTitles" cols="40" rows="8"></s:textarea>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">列标题标签组：</td>
   							<td class="tdFormControl"><s:textarea name="matrixColumnTitles" cols="40" rows="8"></s:textarea>
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