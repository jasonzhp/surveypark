<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑权限</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine" colspan="2"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader" colspan="2">编辑权限</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine" colspan="2"></td>
   		</tr>	
	   	<s:form action="RightAction_saveOrUpdateRight" namespace="/" method="post">
	   		<s:hidden name="id"></s:hidden>
	   		<s:hidden name="rightPos"></s:hidden>
	   		<s:hidden name="rightCode"></s:hidden>
   			<tr>
   				<td class="tdFormLabel">权限名称：</td>
   				<td class="tdFormControl">
   					<s:textfield name="rightName" cssClass="text"></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td class="tdFormLabel">权限URL：</td>
   				<td class="tdFormControl">
   					<s:textfield name="rightUrl" cssClass="text"></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td class="tdFormLabel">公共资源：</td>
   				<td class="tdFormControl"><s:checkbox name="common"></s:checkbox></td>
   			</tr>
   			<tr>
   				<td class="tdFormLabel">权限描述：</td>
   				<td class="tdFormControl">
					<s:textarea name="rightDesc" rows="10" cols="40"></s:textarea>
   				</td>
   			</tr>
   			<tr>
   				<td class="tdFormLabel"></td>
   				<td class="tdFormControl">
					<s:submit value="提交" cssClass="btn"></s:submit>	
   				</td>
   			</tr>	
	   	</s:form>
   	</table>
  </body>
</html>
