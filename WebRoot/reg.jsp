<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
  </head>
  
  <body> 
	<s:form action="RegAction_doReg" namespace="/" method="post">
		<s:include value="header.jsp"></s:include>
		<table class="headerTable">
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="2" class="tdHeader">注册新用户</td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdFormLabel">E-mail：</td>
				<td class="tdFormControl">
					<s:textfield name="email" cssClass="text"></s:textfield>
					<font class="fontError"><s:fielderror><s:param>email</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">密码：</td>
				<td class="tdFormControl">
					<s:password name="password" cssClass="text"></s:password>
					<font class="fontError"><s:fielderror><s:param>password</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">确认密码：</td>
				<td class="tdFormControl">
					<s:password name="confirmPassword" cssClass="text"></s:password>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">昵称：</td>
				<td class="tdFormControl">
					<s:textfield name="nickName" cssClass="text"></s:textfield>
					<font class="fontError"><s:fielderror><s:param>nickName</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">&nbsp;</td>
				<td class="tdFormControl"><s:submit cssClass="btn" value="确定"></s:submit></td>
			</tr>
		</table>
	</s:form>

  </body>
</html>
