<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>登陆页面</title>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
  </head>
  
  <body>
    <s:form action="LoginAction_doLogin" method="post" namespace="/">
  	<s:include value="header.jsp"></s:include>
    	<table class="headerTable">
    		<tr>
    			<td class="tdWhiteLine" colspan="2"></td>
    		</tr>
    		<tr>
    			<td class="tdHeader" colspan="2">用户登陆</td>
    		</tr>
    		<tr>
    			<td class="tdWhiteLine" colspan="2"></td>
    		</tr>
    		<tr>
    			<td class="tdFormLabel">E-mail：</td>
    			<td class="tdFormControl">
    				<s:textfield  name="email" cssClass="text" value="11@qq.com"></s:textfield>
    				<font class="fontError"><s:actionerror/></font>
    			</td>
    		</tr>
    		<tr>
    			<td class="tdFormLabel">密码：</td>
    			<td class="tdFormControl">
    				<s:password name="password" cssClass="text"></s:password>
    			</td>
    		</tr>
    		<tr>
    			<td class="tdFormLabel">&nbsp;</td>
    			<td class="tdFormControl"><s:submit cssClass="btn" value="登陆"></s:submit>
    		</tr>
    	</table>
    </s:form>
  </body>
</html>
