<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  
  <body>
    <table class="headerTable">
    	<tr>
    		<td class="headerTd1"><h1 class="headerH1">欢迎使用SurveyDoor调查系统!</h1></td>
    	</tr>
    	<tr>
    		<td class="headerTd2">
    		<s:a action="LoginAction_toLoginPage" namespace="/">&nbsp;[首页]&nbsp;</s:a>
    		<s:a action="SurveyAction_newSurvey" namespace="/">&nbsp;[新建调查]&nbsp;</s:a>
    		<s:a action="SurveyAction_mySurveys" namespace="/">&nbsp;[我的调查]&nbsp;</s:a>
    		<s:a action="EngageSurveyAction_findAllAvailableSurveys" namespace="/">&nbsp;[参与调查]&nbsp;</s:a>
    		<s:a action="RegAction_toRegPage" namespace="/">&nbsp;[用户注册]&nbsp;</s:a>
    		<s:a action="UserAuthorizeAction_findAllUsers" namespace="/">&nbsp;[用户授权管理]&nbsp;</s:a>
    		<s:a action="RoleAction_findAllRoles" namespace="/">&nbsp;[角色管理]&nbsp;</s:a>
    		<s:a action="RightAction_findAllRights" namespace="/">&nbsp;[权限管理]&nbsp;</s:a>
    		<s:a action="LogAction_findNearestLogs" namespace="/">&nbsp;[最近日志]&nbsp;</s:a>
    		</td>
    	</tr>
    </table>
    <s:if test="#session['user']!=null">
    	<table width="100%">
    		<tr>
    			<td class="welcome">
    				<b>欢迎你，<s:property value="#session['user'].nickName"/>.&nbsp;&nbsp;</b>
    			</td>
    		</tr> 
   		</table>			 
   	</s:if> 
  </body>
</html>
