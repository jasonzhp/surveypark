<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑用户授权</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">
	
	<script type="text/javascript" src='<s:url value="/jquery-1.12.1.js" />'></script>
	<script type="text/javascript">
		$(function(){
			$('#one1').click(function(){
				var size = $('#left > option:selected').size();
				if(size != 0)
				{
					$('#left > option:selected').appendTo($('#right'));
				}
				else
				{
					$('#left > option:first-child').appendTo($('#right'));
				}
			});
			$('#one2').click(function(){
				var size = $('#right > option:selected').size();
				if(size != 0)
				{
					$('#right > option:selected').appendTo($('#left'));
				}
				else
				{
					$('#right > option:first-child').appendTo($('#left'));
				}
			});
			$('#all1').click(function(){
				$('#left > option').appendTo($('#right'));
			});
			$('#all2').click(function(){
				$('#right > option').appendTo($('#left'));			
			});
		});
		
		//当按下提交按钮时，对左边的select对象执行全选工作
		function allSelect(){  
			var list = document.forms[0].ownRoleIds;
			if(!list.length)
				return;
			for (var i = 0;i<list.length;i++){  
				if(list.options[i].selected == false)
			     	list.options[i].selected = true;  
			}  
		} 
	</script>

  </head>
  
  <body>
    <s:include value="header.jsp"></s:include>
    <table class="headerTable">
 		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td class="tdHeader">&nbsp;编辑用户授权：</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
		<s:form action="UserAuthorizeAction_updateUserAuthorize" namespace="/" method="post" onsubmit="allSelect()">
			<s:hidden name="id"></s:hidden>
	   		<tr>
	   			<td>
	   				<table class="simpleTable">
	   					<tr>
	   						<td class="tdFormLabel" width="35%">用户昵称：</td>
	   						<td class="tdFormControl"><s:textfield name="nickName" cssClass="text" readonly="true" cssStyle="color:gray"></s:textfield></td>
	   					</tr>
	   					<tr>
	   						<td class="tdFormLabel">E-mail：</td>
	   						<td class="tdFormControl"><s:textfield name="email" cssClass="text" readonly="true" cssStyle="color:gray"></s:textfield></td>
	   					</tr>
	   				</table>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>
	   				<table class="simpleTable">
	   					<tr>
	   						<td width="45%" align="right">
	   							<s:select id="left"
	   								name="ownRoleIds"
	   								list="roles"
	   								listkey="id"
	   								listValue="roleName"
	   								multiple="true"
	   								size="15"
	   								cssStyle="width:170px">
	   							</s:select>
	   						</td>
	   						<td width="10%" align="center" valign="middle">
	   							<input type="button" id="one1" value="&gt;" class="btn" /><br><br>
	   							<input type="button" id="one2" value="&lt" class="btn" /><br><br>
	   							<input type="button" id="all1" value="&gt;&gt;" class="btn" /><br><br>
	   							<input type="button" id="all2" value="&lt;&lt;" class="btn" />
	   						</td>
	   						<td width="45%" align="left">
	   							<s:select id="right"
	   								list="noOwnRoles"
	   								listKey="id"
	   								listValue="roleName"
	   								multiple="true"
	   								size="15"
	   								cssStyle="width:170px">
	   							</s:select>
	   						</td>
	   					</tr>
	   				</table>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td align="center"><s:submit value="提交" cssClass="btn"></s:submit></td>
	   		</tr>
		</s:form>
	</table>
  </body>
</html>
