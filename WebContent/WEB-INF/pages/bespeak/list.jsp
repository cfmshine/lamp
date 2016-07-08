<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>预约列表</title>
</head>
<body>

	<div class="page_title">未完成预约列表</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[1].submit();">查询</button>
	</div>
	
	<form action="${ctp}/bespeak/manager/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>设备名称</th>
				<td>
					<input type="text" name="search_LIKES_deviceName"/>
				</td>
				<th>申请人姓名</th>
				<td><input type="text" name="search_LIKES_userName" /></td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>设备名称</th>
					<th>申请人姓名</th>
					<th>提交时间</th>
					<th>申请时间</th>
					<th>申请节次</th>
				</tr>
				
				<c:forEach var="bespeak" items="${page.content }">
					<tr>
						<td class="list_data_text">${bespeak.device.name }&nbsp;</td>
						<td class="list_data_text">${bespeak.user.name }&nbsp;</td>
						<td class="list_data_text"><fmt:formatDate value="${bespeak.date }"/>&nbsp;</td>
						<td class="list_data_text"><fmt:formatDate value="${bespeak.bespeakDate }"/>&nbsp;</td>
						<td class="list_data_text">${bespeak.stage }&nbsp;</td>					
					</tr>
				</c:forEach>
			</table>
			<atguigu:page page="${page }"></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
	
</body>
</html>
