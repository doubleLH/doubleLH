<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--    http://localhost:8080/servlet_demo1/jstl/jstl.jsp -->
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%
		int a =10;
		out.print(a);
			
	 %>
	 
	 <%
   
	  %>
	  
	  

	  
<%-- 	  ${10/2}; --%>
	  <br>
<!-- 打印9*9 -->
	 <c:forEach begin="1" end="8" var="i" varStatus="u">
		 <c:forEach begin="1" end="8" var="j">
				 <c:out value="*" /> 
		 </c:forEach>
	 		
	 			 <c:out value="*"/>	<br>
		 </c:forEach>
	  
	  
	  
	  
</body>
</html>