<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看设备</title>
<link rel="stylesheet" type="text/css" href="${ctp}/static/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctp}/static/jquery/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctp}/static/jquery/demo.css">
<script type="text/javascript" src="${ctp}/static/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#dd').datebox({    
		    required:true   
		}); 
	})
</script>
  </head>

  <body class="main">
	<span class="page_title">未完成的预约</span>
	<c:if test="${!empty bespeaks}">
	<table class="query_form_table" border="0" cellPadding="3"
		cellSpacing="0">
		<tr>
			<th>预约人</th>
			<th>预约时间</th>
			<th>预约节次</th>
		</tr>
		<c:forEach items="${bespeaks }" var="bespeak">
			<tr>
				<td>${bespeak.user.name }</td>
				<td><fmt:formatDate value="${bespeak.bespeakDate }" pattern="yyyy-MM-dd"/></td>
				<td>${bespeak.stage }</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty bespeaks }">
		没有任何数据
	</c:if>
	<br>
	<span class="page_title">查看设备</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.back(-1);">返回</button>			
	</div>
  	<form action="${ctp}/bespeak/save" method="post">
		<input type="hidden" name="deviceId" value="${device.id}"/>
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>设备名称</th>
				<td>${device.name }&nbsp;</td>
				
				<th>生产厂家</th>
				<td>${device.vender }&nbsp;</td>
			</tr>
			<tr>
				<th>所属专业</th>
				<td>${device.speciality }&nbsp;</td>
				
				<th>所在教室</th>
				<td>${device.classroom }&nbsp;</td>
			</tr>
			<tr>
				<th>状态</th>
				<td>${device.status == 1 ? "正常" : "维护中" }</td>
				<th>管理人</th>
				<td>${device.maintenance.name }</td>
			</tr>
			<tr>
				<th>电话1</th>
				<td>${device.maintenance.telephone }</td>
				<th>电话2</th>
				<td>${device.maintenance.phone }</td>
			</tr>
			
			<tr>
				<th>图片浏览</th>
				<td colspan="3"><img alt="" src="${ctp }/${device.imgPath }">&nbsp;</td>
			</tr>
			
			<tr>
				<th>简介</th>
				<td colspan="3"><textarea rows="6" cols="50" readonly="readonly">${device.synopsis }</textarea>&nbsp;</td>
			</tr>
		</table>
		
	<br />
	<c:if test="${device.status == 1 }">
	<span class="page_title">预约</span>
	<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
	<tr>
		<th>预约日期</th>
		<th>预约节次</th>
	</tr>
	<tr>
		<td>
			<input id="dd" type="text" class="easyui-datebox" required="required" name="bespeakDate"></input>
		</td>
		<td>
			<select name="stage">
				<option value="1">第一节</option>
				<option value="2">第二节</option>
				<option value="3">第三节</option>
				<option value="4">第四节</option>		
			</select>
		</td>
	</tr>
	
	</table>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[1].submit();">预约</button>
	</div>
	</c:if>
  </form>
  </body>
</html>