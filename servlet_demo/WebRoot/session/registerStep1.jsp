<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册第一步</title>
	</head>
	<body>
	<h1>用户注册</h1>
	<hr>
	<form action="${pageContext.request.contextPath}/DisperseRegisterStepOne" method="post">
	            用户名:<input type="text" name="username"/><br/>
	            密    码:<input type="password"name="password"/><br/>
	         <input type="submit"value="下一步"/><br/>
	</form>
	</body>
	</> 
