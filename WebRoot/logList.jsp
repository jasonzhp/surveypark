<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>最近日志</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable"> 
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeaderP" style="color:white">
   				<s:form action="LogAction_findNearestLogs" namespace="/">
		   			查询最近几个月的日志：<s:textfield name="months" size="1"></s:textfield>
		   			<s:submit value="go" cssStyle="width:40px"></s:submit>
	   			</s:form>
	   		</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
			   	<s:if test="nearestLogs.isEmpty()"><p align="center">目前没有任何日志！</p></s:if>
			   	<s:else>
		   			<table class="simpleTable">
		   				<thead>
					 		<tr>
					   			<td class="tdWhiteLine" colspan="6"></td>
					   		</tr>
					   		<tr>
					   			<td class="tdHeader" colspan="6">&nbsp;最近日志：</td>
					   		</tr>
					   		<tr>
					   			<td class="tdWhiteLine" colspan="6"></td>
					   		</tr>
			   				<tr>
			   					<td class="tdList" align="center">操作人</td>
			   					<td class="tdList" align="center">操作名称</td>
			   					<td class="tdList" align="center">参数</td>
			   					<td class="tdList" align="center">操作结果</td>
			   					<td class="tdList" align="center">消息</td>
			   					<td class="tdList" align="center">操作时间</td>
			   				</tr>
			   			</thead>
			   			<tbody>
			   				<s:iterator value="%{nearestLogs}" status="st">
			   					<tr>
			   						<td class="tdList" align="center"><s:property value="operator" /></td>
			   						<td class="tdList" align="center"><s:property value="operateName" /></td>
			   						<td class="tdList" align="center">
			   							<span title="<s:property value='operateParams' />">
			   								<s:property value="@com.meng.surveypark.util.StringUtil@getDescString(operateParams)" />
			   							</span>
			   						</td>
			   						<td class="tdList" align="center">
			   							<s:property value="operateResult" />
			   						</td>
			   						<td class="tdList" align="center">
			   							<span title="<s:property value='resultMsg' />">
			   								<s:property value="@com.meng.surveypark.util.StringUtil@getDescString(resultMsg)" />
			   							</span>
			   						</td>
			   						<td class="tdList" align="center"><s:date name="operateTime" format="MM/dd/yyyy HH:mm"/></td>
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
