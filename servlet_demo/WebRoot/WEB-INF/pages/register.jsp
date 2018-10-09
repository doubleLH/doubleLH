<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
 
  
  <body>
         <form action="${pageContext.request.contextPath}/user/register" method="post" enctype="multipart/form-data">
	                       用户名: <input type="text" name="username" id="username" />
	                       密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" id="password" />
	                       头&nbsp;&nbsp;&nbsp;像: <input type="file" name="pic"/>
	      <input type="submit" value="注册"/> <span style="color:red;">${errorMsg}</span>
	     </form>
  </body>
</html>
