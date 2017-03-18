<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>移动/复制页</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
	<s:include value="header.jsp"></s:include>
	<table class="headerTable">
 		<tr>
   			<td colspan="3" class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td colspan="3" class="tdHeader">&nbsp;移动/复制页[同一调查内是移动，不同调查间是复制]</td>
   		</tr>
   		<tr>
   			<td colspan="3" class="tdWhiteLine"></td>
   		</tr>
		<s:iterator value="mySurveys" var="survey">
			<s:set value="#survey.id" var="sid"></s:set>
			<!-- 调查名称 -->
			<tr>
				<td colspan="3" class="tdDesignSurvey"><s:property value="title"></s:property></td>
			</tr>
			<s:iterator value="#survey.pages" var="page">
				<s:set value="#page.id" var="pid"></s:set>
				<s:if test="#pid==srcPid">
					<s:set value='"rgb(200,125,200)"' var="bgcolor"></s:set> 
				</s:if>
				<s:else>
					<s:set value='"white"' var="bgcolor"></s:set>
				</s:else>
				<tr bgcolor='<s:property value="bgcolor" />'>
					<td width="30px" class="tdList"></td>
					<td class="tdList"><s:property value="title"></s:property></td>
					<td class="tdList">
						<s:if test="#pid!=srcPid">
							<s:form action="MoveOrCopyPageAction_doMoveOrCopyPage" namespace="/" method="post">
								<s:hidden value="%{#sid}" name="sid"></s:hidden>
								<s:hidden value="%{#pid}" name="targetPid"></s:hidden>
								<s:hidden name="srcPid"></s:hidden>
								<s:radio list="#{0:'之前',1:'之后'}" listKey="key" listValue="value" name="pos"></s:radio>
								<s:submit value="确定" cssClass="btn"></s:submit>
							</s:form>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</s:iterator>
   	</table>
  </body>
</html>
