<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑角色</title>

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
/*		function allSelect(){  
			var list = document.forms[0].ownRightIds;
			if(!list.length)
				return;
			for (var i = 0;i<list.length;i++){  
				if(list.options[i].selected == false)
			     	list.options[i].selected = true;  
			}  
		} 
*/
		function submitForm(){
			$('#left > option').attr('selected','selected');
			return true ;
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
   			<td class="tdHeader">&nbsp;编辑角色：</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
		<s:form action="RoleAction_saveOrUpdateRole" namespace="/" method="post">
			<s:hidden name="id"></s:hidden>
	   		<tr>
	   			<td>
	   				<table class="simpleTable">
	   					<tr>
	   						<td class="tdFormLabel" width="35%">角色名称：</td>
	   						<td class="tdFormControl"><s:textfield name="roleName" cssClass="text"></s:textfield></td>
	   					</tr>
	   					<tr>
	   						<td class="tdFormLabel">角色值：</td>
	   						<td class="tdFormControl"><s:textfield name="roleValue" cssClass="text"></s:textfield></td>
	   					</tr>
	   					<tr>
	   						<td class="tdFormLabel">角色描述：</td>
	   						<td class="tdFormControl"><s:textarea name="roleDesc" cols="40" rows="8"></s:textarea></td>
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
	   								name="ownRightIds"
	   								list="rights"
	   								listkey="id"
	   								listValue="rightName"
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
	   								list="noOwnRights"
	   								listKey="id"
	   								listValue="rightName"
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
	   			<td align="center"><s:submit value="提交" cssClass="btn" onclick="return submitForm()"></s:submit></td>
	   		</tr>
		</s:form>
	</table>
  </body>
</html>
