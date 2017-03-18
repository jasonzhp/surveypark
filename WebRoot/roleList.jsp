<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>角色管理</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeaderP" align="right"><s:a action="RoleAction_toAddRolePage" namespace="/">添加角色</s:a>&nbsp;</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
			   	<s:if test="allRoles.isEmpty()"><p align="center">目前没有任何角色！</p></s:if>
			   	<s:else>
		   			<table class="simpleTable">
		   				<thead>
					 		<tr>
					   			<td class="tdWhiteLine" colspan="5"></td>
					   		</tr>
					   		<tr>
					   			<td class="tdHeader" colspan="5">&nbsp;角色管理：</td>
					   		</tr>
					   		<tr>
					   			<td class="tdWhiteLine" colspan="5"></td>
					   		</tr>
			   				<tr>
			   					<td class="tdList" align="center" width="10%">序号</td>
			   					<td class="tdList" align="center" width="10%">ID</td>
			   					<td class="tdList" align="center">角色名称</td>
			   					<td class="tdList" align="center">修改</td>
			   					<td class="tdList" align="center">删除</td>
			   				</tr>
			   			</thead>
			   			<tbody>
			   				<s:iterator value="%{allRoles}" status="st">
			   					<s:set value="id" var="roleId"></s:set>
			   					<tr>
			   						<td class="tdList" align="center"><s:property value="#st.index + 1" /></td>
			   						<td class="tdList" align="center"><s:property value="#roleId" /></td>
			   						<td class="tdList" align="center"><s:property value="roleName" /></td>
			   						<td class="tdList" align="center"><s:a action="RoleAction_editRole?roleId=%{#roleId}" namespace="/">修改</s:a></td>
			   						<td class="tdList" align="center"><s:a action="RoleAction_deleteRole?roleId=%{#roleId}" namespace="/">删除</s:a></td>
			   					</tr>
			   				</s:iterator>
			   			</tbody>
		   			</table>
			   	</s:else>
   			</td>
   		</tr>
   	</table>
  </body>
</html>
