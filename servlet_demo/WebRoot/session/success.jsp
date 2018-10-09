<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <h1>用户名:${name}</h1>
	  <h1>密&nbsp;&nbsp;&nbsp;码:${pwd}</h1>
	  <h1>性&nbsp;&nbsp;&nbsp;別:
		 <c:choose>
		 	<c:when test="${sex ==1}">男</c:when>
		 	<c:when test="${sex ==2}">女</c:when>
		 </c:choose>
	   </h1>
	  <h1>职&nbsp;&nbsp;&nbsp;位:
	  		<c:choose>
	  			<c:when test="${job == 1}"></c:when>
	  			<c:when test="${job == 2}"></c:when>
	  		</c:choose>
	  </h1> 
  </body>
</html>
