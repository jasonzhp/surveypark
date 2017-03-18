<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>参与调查</title>
    
	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
	<s:include value="/header.jsp"></s:include>
	<s:if test="surveys.idEmpty()"><p align="center">目前没有可用的调查！</p></s:if>
	<s:else>
		<s:set value="5" var="cells"></s:set>
		<table class="headerTable">
	 		<tr>
	   			<td class="tdWhiteLine" colspan='<s:property value="#cells"/>'></td>
	   		</tr>
	   		<tr>
	   			<td class="tdHeader" colspan='<s:property value="#cells"/>'>&nbsp;参与调查：请选择要参与的调查</td>
	   		</tr>
	   		<tr>
	   			<td class="tdWhiteLine" colspan='<s:property value="#cells"/>'></td>
   			</tr>
   			<s:iterator begin="0" end="%{surveys.size - 1}" step="#cells" var="i">
   				<tr>
   					<s:iterator begin="0" end="%{#cells - 1}" step="1" var="j">
   						<s:set value="#i + #j" var="idx"></s:set>
   						<td width='<s:property value="100 / #cells" />%' class="tdList" align="center">
   							<s:if test="#idx < surveys.size">
   								<s:a action="EngageSurveyAction_entry?sid=%{surveys[#idx].id}" namespace="/">
   									<img alt='<s:property value="surveys[#idx].title" />' 
   										src='<s:property value="getImageUrl(surveys[#idx].logoPhotoPath)" />'
   										width="180px" height="120px" >
   									<br>
   									<s:property value="#idx + 1"/>.<s:property value="surveys[#idx].title"/>
   								</s:a>
   							</s:if>
   						</td>
   					</s:iterator>
   				</tr>
   			</s:iterator>
   		</table>
	</s:else>
  </body>
</html>
