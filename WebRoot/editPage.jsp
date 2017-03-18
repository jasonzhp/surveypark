<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>编辑页面</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <s:set value="id" var="sId"></s:set>
   	<table class="headerTable" width="100%">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;编辑页面</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
   				<s:form action="PageAction_saveOrUpdatePage" namespace="/" method="post">
   					<s:hidden name="sid"></s:hidden>
   					<s:hidden name="id"></s:hidden>
   					<table class="simpleTable">
   						<tr>
   							<td class="tdFormLabel">页面标题：</td>
   							<td class="tdFormControl"><s:textfield name="title" cssClass="text"></s:textfield></td>
   						</tr>
   						<tr>
   							<td class="tdFormLabel">页面描述：</td>
   							<td class="tdFormControl"><s:textarea name="description" cols="30" rows="8"></s:textarea></td>
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
