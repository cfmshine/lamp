<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�޸��ҵ���Ϣ</title>
  </head>
  <body class="main">
  
  	<form:form action="${ctp }/user/user/update" method="post" modelAttribute="user">
  		
	  		<span class="page_title">�û�����&gt;���޸��ҵ���Ϣ</span>
			<form:hidden path="id"/>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">����</button>
			<button class="common_button" onclick="document.forms[1].submit()">����</button>
		</div>
		
		<table  class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title">�û���</th>
				<td class="input_content">
					<form:input path="name"/>
					<div id='divCheck'></div>
				</td>
				
				<th class="input_title">����</th>
				<td class="input_content">
					<form:password path="password"/>
				</td>
			</tr>
			<tr>
				<th class="input_title">�����ʼ�</th>
				<td class="input_content">
					<form:input path="email"/>
					<div id='divCheck'></div>
				</td>
				<th class="input_title">�绰</th>
				<td class="input_content">
					<form:input path="telephone"/>
					<div id='divCheck'></div>
				</td>
			</tr>
		</table>
	</form:form>
	
  </body>
</html>
