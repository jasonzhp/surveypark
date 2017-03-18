<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>权限管理</title>

	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript">
		function selectAll(form, selectAllCheckbox)
		{
			//取出全选复选框的状态
			var selected = selectAllCheckbox.checked;
			for(var i = 0;i<form.elements.length;i++)
			{
				//提取控件
				var checkbox = form.elements[i];
				//检查是否是指定控件
				if(checkbox.id == "groupCheckbox" && checkbox.type == "checkbox")
				{
					checkbox.checked = selected;
				}
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
   			<td class="tdHeaderP" align="right"><s:a action="RightAction_toAddRightPage" namespace="/">添加权限</s:a>&nbsp;</td>
   		</tr>
   		<tr>
   			<td class="tdWhiteLine"></td>
   		</tr>
   		<tr>
   			<td>
			   	<s:if test="allRights.isEmpty()"><p align="center">目前没有任何权限！</p></s:if>
			   	<s:else>
			   		<s:form action="RightAction_batchUpdateRights" namespace="/" method="post">
			   			<table class="simpleTable">
			   				<thead>
						 		<tr>
						   			<td class="tdWhiteLine" colspan="8"></td>
						   		</tr>
						   		<tr>
						   			<td class="tdHeader" colspan="8">&nbsp;权限管理：</td>
						   		</tr>
						   		<tr>
						   			<td class="tdWhiteLine" colspan="8"></td>
						   		</tr>
				   				<tr>
				   					<td class="tdList" width="50px" align="center">ID</td>
				   					<td class="tdList" width="150px" align="center">权限名称</td>
				   					<td class="tdList" width="120px" align="center">
				   						&nbsp;&nbsp;公共资源<br>
				   						<input type="checkbox" id="cbSelectAll" onclick="selectAll(this.form, this);"/>全选
				   					</td>
				   					<td class="tdList" align="center">权限URL</td>
				   					<td class="tdList" align="center">权限位</td>
				   					<td class="tdList" align="center">权限码</td>
				   					<td class="tdList" align="center">修改</td>
				   					<td class="tdList" align="center">删除</td>
				   				</tr>
				   			</thead>
				   			<tbody>
				   				<s:iterator value="%{allRights}" status="st">
				   					<s:set value="id" var="rightId"></s:set>
				   					<tr>
				   						<td class="tdList">
				   							<s:textfield name="allRights[%{#st.index}].id" readonly="true" cssStyle="width:50px"></s:textfield>
				   						</td>
				   						<td class="tdList">
				   							<s:textfield name="allRights[%{#st.index}].rightName" cssStyle="width:150px"></s:textfield>
				   						</td>
				   						<td class="tdList">&nbsp;<s:checkbox name="allRights[%{#st.index}].common" id="groupCheckbox"></s:checkbox>
				   						<td class="tdList"><s:property value="rightUrl" /></td>
				   						<td class="tdList"><s:property value="rightPos" /></td>
				   						<td class="tdList" width="200px"><s:property value="rightCode" /></td>
				   						<td class="tdList" align="center"><s:a action="RightAction_editRight?rightId=%{#rightId}" namespace="/">修改</s:a></td>
				   						<td class="tdList" align="center"><s:a action="RightAction_deleteRight?rightId=%{#rightId}" namespace="/">删除</s:a></td>
				   					</tr>
				   				</s:iterator>
				   				<tr>
				   					<td colspan="8" class="tdList" align="center"><s:submit value="提交" cssClass="btn"></s:submit></td>
				   				</tr>
				   			</tbody>
			   			</table>
			   		</s:form>
			   	</s:else>
   			</td>
   		</tr>
   	</table>
  </body>
</html>
