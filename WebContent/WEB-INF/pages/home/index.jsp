<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验室预约管理系统</title>
<c:set var="ctp" value="${pageContext.request.contextPath }"></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="${ctp }/static/images/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="${ctp }/static/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="${ctp }/static/css/style4.css" />
<link rel="stylesheet" type="text/css"
	href="${ctp }/static/css/animate-custom.css" />
</head>
<c:if test="${message != null }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<body>
	<div class="container">
		<!--/ Codrops top bar -->
		<header>
		<h1>
			实验室设备<span>预约</span>管理平台
		</h1>
		</header>
		<section>
		<div id="container_demo">
			<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
			<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
				id="tologin"></a>
			<div id="wrapper">
				<div id="login" class="animate form">
					<form action="${ctp }/user/shiro-login" method="post">
						<h1>登录</h1>
						<p>
							<label for="username" class="uname" data-icon="u"> 用户名 </label> <input
								id="username" name="name" required="required" type="text"
								placeholder="myusername" />
						</p>
						<p>
							<label for="password" class="youpasswd" data-icon="p"> 密码
							</label> <input id="password" name="password" required="required"
								type="password" placeholder="eg. X8df!90EO" />
						</p>
						<p class="login button">
							<input type="submit" value="登录" />
						</p>
						<p class="change_link">
							还未注册？ <a href="#toregister" class="to_register">点我</a>
						</p>
					</form>
				</div>

				<div id="register" class="animate form">
					<form action="${ctp }/user/register" method="post">
						<h1>注册</h1>
						<p>
							<label for="usernamesignup" class="username" data-icon="u">用户名</label>
							<input id="usernamesignup" name="name" required="required"
								type="text" placeholder="mysuperusername690" />
						</p>
						<p>
							<label for="emailsignup" class="youmail" data-icon="e">
								电子邮箱</label> <input id="emailsignup" name="email" required="required"
								type="email" placeholder="mysupermail@mail.com" />
						</p>
						<p>
							<label for="emailsignup" class="youmail" data-icon="e">
								电话号码</label> <input id="emailsignup" name="telephone" />
						</p>
						<p>
							<label for="passwordsignup" class="youpasswd" data-icon="p">
								密码 </label> <input id="passwordsignup" name="password"
								required="required" type="password" placeholder="eg. X8df!90EO" />
						</p>
						<p>
							<label for="passwordsignup_confirm" class="youpasswd"
								data-icon="p">确认密码 </label> <input id="passwordsignup_confirm"
								name="password_confirm" required="required" type="password"
								placeholder="eg. X8df!90EO" />
						</p>
						<p class="signin button">
							<input type="submit" value="注册" />
						</p>
						<p class="change_link">
							拥有一个账户？ <a href="#tologin" class="to_register"> 去登陆 </a>
						</p>
					</form>
				</div>

			</div>
		</div>
		</section>
	</div>
</body>
</html>