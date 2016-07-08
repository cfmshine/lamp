<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备列表</title>
<script type="text/javascript">
	$(function() {
		
		$("#new").click(function(){
			window.location.href="${ctp}" + "/device/toAddUI";
			return false;
		});
		
		$("img[id^='delete-']").click(function() {del(this);return false;});
		
		function del(button){
			var $this = $(button);
			var deviceId = button.id.split("-")[1];
			var url = "${ctp}/device/delete/"+deviceId;
			$.ajax({
				type: "PUT",
				url: url,
				success: function(message) {
					if(message == "1") {
						$img = $("<img id='give-"+deviceId+"' title='解决故障' src='${ctp }/static/images/bt_acti.gif' class='op_button' />");
						$this.after($img);
						$img.click(function() {give(this);return false;});
						$this.remove();
						$("#state-" + deviceId).text("维护中");
						alert("设置成功");
					} else {
						alert("设置失败");
					}
				}
			});
		}
		
		$("img[id^='give-']").click(function() {give(this);return false;});
		
		function give(button){
			var $this = $(button);
			var deviceId = button.id.split("-")[1];
			var url = "${ctp}/device/give/"+deviceId;
			$.ajax({
				type: "PUT",
				url: url,
				success: function(message) {
					if(message == "1") {
						$img = $("<img id='delete-"+deviceId+"' title='故障' src='${ctp }/static/images/bt_del.gif' class='op_button' />");
						$this.after($img);
						$img.click(function() {del(this);return false;});
						$this.remove();
						$("#state-" + deviceId).text("正常");
						alert("设置成功");
					} else {
						alert("设置失败");
					}
				}
			});
		}
	});
</script>
</head>
<body>

	<div class="page_title">设备列表</div>
	<div class="button_bar">
		<button class="common_button" id="new">新建</button>
		<button class="common_button" onclick="document.forms[1].submit();">查询</button>
	</div>
	
	<form action="${ctp}/device/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>设备名称</th>
				<td>
					<input type="text" name="search_LIKES_name" value="${param.search_LIKES_name }"/>
				</td>
				<th>所在教室</th>
				<td>
					<select name="search_EQS_classroom">
						<option value="">全部</option>
						<option value="16-202" <c:if test="${param.search_EQS_classroom == '16-202' }">selected="selected"</c:if> >16-202</option>
						<option value="17-310" <c:if test="${param.search_EQS_classroom == '17-310' }">selected="selected"</c:if> >17-310</option>				
					</select>
				</td>
			</tr>
			<tr>
				<th>生产厂家</th>
				<td>
					<input type="text" name="search_LIKES_vender" value="${param.search_LIKES_vender }" />
				</td>
				
				<th>所属专业</th>
				<td>
					<select name="search_EQS_speciality">
						<option value="">全部</option>
						<option value="包装" <c:if test="${param.search_EQS_speciality == '包装' }">selected="selected"</c:if> >包装</option>
						<option value="图文" <c:if test="${param.search_EQS_speciality == '图文' }">selected="selected"</c:if> >图文</option>
						<option value="数字" <c:if test="${param.search_EQS_speciality == '数字' }">selected="selected"</c:if> >数字</option>
						<option value="木材" <c:if test="${param.search_EQS_speciality == '木材' }">selected="selected"</c:if> >木材</option>	
						<option value="物流" <c:if test="${param.search_EQS_speciality == '物流' }">selected="selected"</c:if> >物流</option>					
					</select>
				</td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<select name="search_EQI_status">
						<option value="">全部</option>
						<option value="1" <c:if test="${param.search_EQI_status == 1 }">selected="selected"</c:if> >正常</option>
						<option value="2" <c:if test="${param.search_EQI_status == 2 }">selected="selected"</c:if> >维护中</option>
					</select>
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>设备名称</th>
					<th>所属专业</th>
					<th>生产厂家</th>
					<th>所在教室</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				
				<c:forEach var="device" items="${page.content }">
					<tr>
						<td class="list_data_text">${device.name }&nbsp;</td>
						<td class="list_data_text">${device.speciality }&nbsp;</td>
						<td class="list_data_text">${device.vender }&nbsp;</td>
						<td class="list_data_text">${device.classroom }&nbsp;</td>
						<td class="list_data_text" id="state-${device.id }">${device.status == 1 ? "正常" : "维护中" }&nbsp;</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/device/toEditUI/${device.id}'"
								title="编辑" src="${ctp }/static/images/bt_edit.gif" class="op_button" alt="" /> 
							<img onclick="window.location.href='${ctp }/device/detail/${device.id}'"
								title="查询及预约" src="${ctp }/static/images/bt_detail.gif"
								class="op_button" />
							<c:if test="${device.status == 1 }">
							<img id="delete-${device.id}"
								title="故障" src="${ctp }/static/images/bt_del.gif" class="op_button" />
							</c:if>
							<c:if test="${device.status == 2 }">
							<img id="give-${device.id}"
								title="解决故障" src="${ctp }/static/images/bt_acti.gif" class="op_button" />
							</c:if>
						</td>					
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
