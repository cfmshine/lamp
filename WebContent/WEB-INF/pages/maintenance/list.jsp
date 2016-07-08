<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备管理人列表</title>
<script type="text/javascript">
	$(function() {
		$("#new").click(function() {
			window.location.href = "${ctp}" + "/maintenance/toAddUI";
			return false;
		});

		$("img[id^='update-']").click(function() {
			var id = this.id.split("-")[1];

			window.location.href = "${ctp}/maintenance/toEditUI/" + id;
		})

		$("img[id^='delete-']").click(function() {

			var flag = confirm("确认删除吗");
			if (flag) {
				var id = this.id.split("-")[1];
				var thisImg = $(this);

				var url = "${ctp}/maintenance/" + id;
				var args = {
					"_method" : "DELETE",
					"time" : new Date()
				};

				$.post(url, args, function(data) {
					if (data == 1) {
						alert("删除成功");
						thisImg.parent().parent().remove();
					}

				});
			}
		})
	})
</script>
</head>
<body>

	<div class="page_title">设备管理人列表</div>
	<div class="button_bar">
		<button class="common_button" id="new">新建</button>
	</div>

	<c:if test="${!empty maintenances }">
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>管理人姓名</th>
				<th>电话1</th>
				<th>电话2</th>
				<th>操作</th>
			</tr>

			<c:forEach var="maintenance" items="${maintenances }">
				<tr>
					<td class="list_data_text">${maintenance.name }&nbsp;</td>
					<td class="list_data_text">${maintenance.telephone }&nbsp;</td>
					<td class="list_data_text">${maintenance.phone }&nbsp;</td>
					<td class="data_cell">
						<img id="delete-${maintenance.id}" src="${ctp }/static/images/bt_del.gif"
							class="op_button" title="删除" />
						<img id="update-${maintenance.id}" src="${ctp }/static/images/bt_edit.gif"
							class="op_button" title="编辑" />
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty maintenances }">
			没有任何数据
		</c:if>

</body>
</html>
