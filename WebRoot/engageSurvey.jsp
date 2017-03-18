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
		<s:form action="EngageSurveyAction_doEngageSurvey" namespace="/">
			<s:hidden name="currentPid" value="%{currentPage.id}"></s:hidden>
			<table class="headerTable">
		 		<tr>
		   			<td class="tdWhiteLine" colspan="2"></td>
		   		</tr>
		   		<tr>
		   			<!-- 输出调查标题 -->
		   			<td class="tdHeader" colspan="2"><s:property value="#session.currentSurvey.title"/></td>
		   		</tr>
		   		<tr>
		   			<td class="tdWhiteLine" colspan="2"></td>
	   			</tr>
	   			<tr>
	   				<!-- 输出页面标题 -->
					<td colspan="2" class="tdDesignPage">&nbsp;&nbsp;<s:property value="%{currentPage.title}" /></td>
				</tr>
				<tr>
					<td width="30px" class="tdList">&nbsp;</td>
					<td width="*" class="tdList">
						<!-- 输出问题 -->
						<table class="simpleTable">
							<s:iterator value="%{currentPage.questions}" var="q">
								<s:set value="#q.id" var="qid"></s:set>
								<!-- 定义题型 -->
								<s:set value="#q.questionType" var="qt"></s:set>
								<tr>
									<td class="tdDesignQuestion"><s:property value="#q.title" /></td>
								</tr>
								<tr>
									<td class="tdList">
										<!-- 当题型为0,1,2,3时 
											 0.非矩阵式横向单选按钮
											 1.非矩阵式纵向单选按钮
											 2.非矩阵式横向复选按钮
											 3.非矩阵式纵向复选按钮
										-->
										<s:if test="#qt < 4">
											<s:iterator value="#q.optionArr" status="st">
												<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'
														name='q<s:property value="#qid" />' 
														value='<s:property value="#st.index" />'
														<s:property value="setTag('q'+#qid, #st.index, 'checked')" /> />
												<s:property/>&nbsp;
												<s:if test="#qt == 1 || qt == 3">
													<br>
												</s:if>
											</s:iterator>
											<!-- 如果存在其他项
													//其他项样式：0-无；1-文本框；2-下拉列表
											 -->
											<s:if test="#q.other">
												<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'
														name='q<s:property value="#qid" />'
														value="other"
														<s:property value="setTag('q'+#qid, 'other', 'checked')" /> />其他			
												<s:if test="#q.otherType == 1">
													<input type="text"
															name='q<s:property value="#qid" />other'
															<s:property value="setText('q'+#qid+'other')" /> />
												</s:if>
												<s:if test="#q.otherType == 2">
													<select name='q<s:property value="#qid" />other'>
														<s:iterator value="#q.otherSelectOptionArr" status="optst"> 
															<option value='<s:property value="#optst.index" />'
																	<s:property value="setTag('q'+#qid+'other',#optst.index,'selected')" /> >
																<s:property/>
															</option>
														</s:iterator>
													</select>
												</s:if>
											</s:if>
										</s:if>
										<!-- 当题型为4时 
											 4.非矩阵式下拉列表
										-->	
										<s:elseif test="#qt == 4">
											<select name='q<s:property value="#qid" />' > 
												<s:iterator value="#q.optionArr" status="optst">
													<option value='<s:property value="#optst.index" />'
															<s:property value="setTag('q'+#qid,#optst.index,'selected')" /> > 
														<s:property/>
													</option>
												</s:iterator>
											</select>
										</s:elseif>
										<!-- 当题型为5时 
											 5.非矩阵式文本框
										-->
										<s:elseif test="#qt == 5">
											<input type="text"
													name='q<s:property value="#qid" />'
													<s:property value="setText('q'+#qid)" /> />
										</s:elseif>
										<!-- 当题型为6,7,8时
											 6.矩阵式单选按钮
											 7.矩阵式复选按钮
											 8.矩阵式下拉列表
										 -->
										<s:elseif test="#qt > 5">
											<table class="simpleTable">
												<tr>
													<td class="tdList"></td>
													<!-- 输出矩阵行标题 -->
													<s:iterator value="#q.matrixRowTitleArr">
														<td class="tdList"><s:property/></td>
													</s:iterator>
												</tr>
												<s:iterator value="#q.matrixColumnTitleArr" status="columnst">
													<tr>
														<td class="tdList">
															<s:property/>
														</td>
														<s:iterator value="#q.matrixRowTitleArr" status="rowst">
															<td class="tdList">
																<s:if test="#qt == 6">
																	<input type="radio"
																			name='q<s:property value="#qid + '_' + #columnst.index" />' 
																			value='<s:property value="#rowst.index + '_' + #columnst.index" />'
																			<s:property value="setTag('q'+#qid+'_'+#columnst.index,#rowst.index+'_'+#columnst.index,'checked')" /> />
																</s:if>
																<s:if test="#qt == 7">
																	<input type="checkbox"
																			name='q<s:property value="#qid" />'
																			value='<s:property value="#rowst.index + '_' + #columnst.index" />'
																			<s:property value="setTag('q'+#qid,#rowst.index+'_'+#columnst.index,'checked')" /> />
																</s:if>
																<s:if test="#qt == 8">
																	<select name='q<s:property value="#qid" />'>
																		<s:iterator value="#q.matrixSelectOptionArr" status="optst">
																			<option value='<s:property value="#rowst.index + '_' + #columnst.index + '_' + #optst.index" />'
																					<s:property value="setTag('q'+#qid,#rowst.index+'_'+#columnst.index+'_'+#optst.index,'selected')" /> >
																				<s:property/>
																			</option>
																		</s:iterator>
																	</select>
																</s:if>
															</td>
														</s:iterator>
													</tr>
												</s:iterator>
											</table>
										</s:elseif>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2" class="tdList">
						<!-- 上一步 -->
						<s:if test="currentPage.orderno != #session['currentSurvey'].minOrderno">
							<input type="submit" value='<s:property value="#session['currentSurvey'].preText" />' name="submit_pre" class="btn" />
						</s:if>
						<!-- 下一步 -->
						<s:if test="currentPage.orderno != #session['currentSurvey'].maxOrderno">
							<input type="submit" value='<s:property value="#session['currentSurvey'].nextText" />' name="submit_next" class="btn" />
						</s:if>
						<!-- 完成 -->
						<s:if test="currentPage.orderno == #session['currentSurvey'].maxOrderno">
							<input type="submit" value='<s:property value="#session['currentSurvey'].doneText" />' name="submit_done" class="btn" />
						</s:if>
						<!-- 退出 -->
						<input type="submit" value='<s:property value="#session['currentSurvey'].exitText" />' name="submit_exit" class="btn"/>
					</td>
				</tr>
	   		</table>
	   	</s:form>
  </body>
</html>
