<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>增加设备</title>
<script type="text/javascript">
	$(function(){
		
		if("${requestScope.device.id}" != ""){
			$("form:eq(1)").attr("action","${ctp}/device/update");
		}
		
	})
</script>
</head>

<body class="main">

	<form:form action="${ctp }/device/save" method="post" modelAttribute="device" enctype="multipart/form-data">
  		
		<c:if test="${device.id != null }">
	  		<span class="page_title">　修改设备</span>
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		<c:if test="${device.id == null }">
	  		<span class="page_title">　新建设备</span>
		</c:if>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" onclick="document.forms[1].submit()">保存</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					设备名称
				</th>
				<td>
					<form:input path="name"/>
					<span class="red_star">*</span>
				</td>
				<th>
					生产厂家
				</th>
				<td>
					<form:input path="vender"/>
				</td>
			</tr>
			<tr>
				<th>
					所属专业
				</th>
				<td>
					<% 
						Map<String, String> map = new HashMap<String, String>();
						map.put("包装", "包装");
						map.put("图文", "图文");
						map.put("数字", "数字");
						map.put("物流", "物流");
						map.put("木材", "木材");
						request.setAttribute("speciality", map);
					%>
					<form:select path="speciality" items="${speciality }"></form:select>
					<span class="red_star">*</span>
				</td>
				<th>
					所属教室
				</th>
				<td>
					<% 
						Map<String, String> map2 = new HashMap<String, String>();
						map2.put("16-202", "16-202");
						map2.put("17-310", "17-310");
						request.setAttribute("classroom", map2);
					%>
					<form:select path="classroom" items="${classroom }"></form:select>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					维护人员
				</th>
				<td>
					<form:select path="maintenance.id">
						<form:option value="">未指定</form:option>
						<form:options items="${maintenances }" itemLabel="name" itemValue="id"/>
					</form:select>
				</td>
				<th>
					上传图片
				</th>
				<td>
					<input type="file" name="img">
				</td>
			</tr>
			<c:if test="${!empty device.imgPath }">
				<tr>
					<th>
						图片浏览
					</th>
					<td>
							<img alt="" src="${ctp }/${device.imgPath }">
					</td>
				</tr>	
			</c:if>
		</table>
		<br>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					简介
				</th>
				<td colspan="3"><form:textarea path="synopsis" cols="50" rows="6"/>&nbsp;</td>
			</tr>
		</table>
		
	</form:form>
</body>
</html>