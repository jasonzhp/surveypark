<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户授权管理</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;用户权限管理：</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
	   			<table class="simpleTable">
	   				<thead>
		   				<tr>
		   					<td class="tdList" align="center" width="10%">序号</td>
		   					<td class="tdList" align="center" width="10%">ID</td>
		   					<td class="tdList" align="center">E-mail</td>
		   					<td class="tdList" align="center">用户昵称</td>
		   					<td class="tdList" align="center">修改授权</td>
		   					<td class="tdList" align="center">删除授权</td>
		   				</tr>
		   			</thead>
		   			<tbody>
		   				<s:iterator value="%{allUsers}" status="st">
		   					<s:set value="id" var="userId"></s:set>
		   					<tr>
		   						<td class="tdList" align="center"><s:property value="#st.index + 1" /></td>
		   						<td class="tdList" align="center"><s:property value="#userId" /></td>
		   						<td class="tdList"><s:property value="email" /></td>
		   						<td class="tdList"><s:property value="nickName" /></td>
		   						<td class="tdList" align="center"><s:a action="UserAuthorizeAction_editUserAuthorize?userId=%{#userId}" namespace="/">修改授权</s:a></td>
		   						<td class="tdList" align="center"><s:a action="UserAuthorizeAction_clearUserAuthorize?userId=%{#userId}" namespace="/">删除授权</s:a></td>
		   					</tr>
		   				</s:iterator>
		   			</tbody>
	   			</table>
   			</td>
   		</tr>
   	</table>
  </body>
</html>
