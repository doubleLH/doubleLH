<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
			function  regist(){
				window.location.href = 'user/regist';
			}
		</script>
		<title>登录页面</title>
	</head>
	<body>
	<form action="${pageContext.request.contextPath}/user/login" method="post">
	       用户名: <input type="text" name="username" id="username" />
	        <br>
	                       密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" id="password" />
	        <br>
	         <input type="submit" value="登录" />
	 </form>
	         <input type="button" value="注册" id="regist" onclick="regist()">
	</body>
</html>
