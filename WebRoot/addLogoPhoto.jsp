<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>增加Logo</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
	<s:include value="header.jsp"></s:include>
	<table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;选择Logo</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
				<s:form action="SurveyAction_doAddLogo" namespace="/" enctype="multipart/form-data" method="post">
					<s:hidden name="sid"></s:hidden>
					<table class="simpleTable">
						<tr>
							<td class="tdFormLabel">选择Logo：</td>
							<td class="tdFormControl">
								<s:file name="logoPhoto"></s:file>
								<font class="fontError"><s:fielderror></s:fielderror></font>
							</td>
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
