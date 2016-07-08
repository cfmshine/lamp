<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>设备管理人</title>
<script type="text/javascript">
	$(function(){
		
		if("${requestScope.maintenance.id}" != ""){
			$("form:eq(1)").attr("action","${ctp}/maintenance/update");
		}
		
	})
</script>
</head>

<body class="main">

	<form:form action="${ctp }/maintenance/save" method="post" modelAttribute="maintenance">
  		
		<c:if test="${maintenance.id != null }">
	  		<span class="page_title">编辑设备管理人</span>
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		<c:if test="${maintenance.id == null }">
	  		<span class="page_title">新建设备管理人</span>
		</c:if>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" onclick="document.forms[1].submit()">保存</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					管理人姓名
				</th>
				<td colspan="3">
					<form:input path="name"/>
				</td>
			</tr>
			<tr>
				<th>
					管理人电话1
				</th>
				<td>
					<form:input path="telephone"/>
				</td>
				<th>
					管理人电话2
				</th>
				<td>
					<form:input path="phone"/>
				</td>
			</tr>
		</table>
		
	</form:form>
</body>
</html>