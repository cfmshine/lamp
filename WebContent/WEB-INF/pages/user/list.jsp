<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		$(function() {
			$("img[id^='delete-']").click(function() {del(this);return false;});
			
			function del(button){
				var $this = $(button);
				var userId = button.id.split("-")[1];
				var url = "${ctp}/user/delete/"+userId;
				$.ajax({
					type: "PUT",
					url: url,
					success: function(message) {
						if(message == "1") {
							$img = $("<img id='give-"+userId+"' title='给予权限' src='${ctp }/static/images/bt_acti.gif' class='op_button' />");
							$this.after($img);
							$img.click(function() {give(this);return false;});
							$this.remove();
							$("#state-" + userId).text("游客");
							alert("删除成功");
						} else {
							alert("删除失败");
						}
					}
				});
			}
			
			$("img[id^='give-']").click(function() {give(this);return false;});
			
			function give(button){
				var $this = $(button);
				var userId = button.id.split("-")[1];
				var url = "${ctp}/user/give/"+userId;
				$.ajax({
					type: "PUT",
					url: url,
					success: function(message) {
						if(message == "1") {
							$img = $("<img id='delete-"+userId+"' title='删除权限' src='${ctp }/static/images/bt_del.gif' class='op_button' />");
							$this.after($img);
							$img.click(function() {del(this);return false;});
							$this.remove();
							$("#state-" + userId).text("用户");
							alert("授权成功");
						} else {
							alert("授权失败");
						}
					}
				});
			}
		})
	</script>
</head>

<body class="main">
	<form action="${ctp }/user/list">
		<div class="page_title">
			用户列表及角色分配
		</div>
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[1].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					用户名
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKES_userName" value="${param.search_LIKES_userName }"/>
				</td>
				<th class="input_title">
					权限
				</th>
				<td class="input_content">
					<select name="search_EQS_roleId" >
						<option value="">
							全部
						</option>
						<option value="2" <c:if test="${param.search_EQS_roleId == 2 }">
							selected="selected"
						</c:if> >
							用户
						</option>
						<option value="3" <c:if test="${param.search_EQS_roleId == 3 && !empty param.search_EQS_roleId}">
							selected="selected"
						</c:if> >
							游客
						</option>
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
					<th class="data_title" style="width: 40px;">
						用户名
					</th>
					<th class="data_title" style="width: 50%;">
						电话
					</th>
					<th class="data_title" style="width: 20%;">
						邮箱
					</th>
					<th class="data_title" style="width: 20%;">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				<c:forEach var="user" items="${page.content }">
					<tr>
						<td class="data_cell" style="text-align: center;">
						${user.name}
						</td>
						<td id="nameLable" class="data_cell" style="text-align: center;">
						${user.telephone}
						</td>
						<td class="data_cell" style="text-align: center;">
						${user.email}
						</td>
						<td id="state-${user.id }" class="data_cell" style="text-align: center;">
							${user.role.id == 2 ? "用户" : "游客" }
						</td>
						<td class="data_cell" >
							<c:if test="${user.role.id == 3 }">
							<img id="give-${user.id}"
								title="给予权限" src="${ctp }/static/images/bt_acti.gif" class="op_button" />
							</c:if>
							<c:if test="${user.role.id == 2 }">
							<img id="delete-${user.id}"
								title="删除权限" src="${ctp }/static/images/bt_del.gif" class="op_button" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<tags:page page="${page }"></tags:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
</body>
</html>
