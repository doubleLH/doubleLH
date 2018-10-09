<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录页面</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		
		<script type="text/javascript">
		    $(function()
		    {
                // 当页面加载完成时, 从cookie中获取key为username和pwd的两个cookie值
                var name = $.cookie("username");
                var pwd = $.cookie("pwd");
                
                // 如果这两个cookie值存在
                if (name && pwd)
                {
                   // $("input[name='username']") = name;  // error
                   
                   // 当用户登录成功并且勾选了7天免登陆之后再次访问改系统时我们要自动把正确的用户名和密码填上
                   $("input[name='username']").val(name);
                   $("input[name='password']").val(pwd);
                   
                   // !!! 手动的提交表单
//                    $("#nolanding7Form").submit();
                }	      
		    });
		</script>
	</head>
	<body>
	<form id="nolanding7Form" action="${pageContext.request.contextPath}/nolanding7" method="post">
	       用户名: <input type="text" name="username" id="username" />
	        <br>
	                       密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" id="password" />
	        <br>
	         <input type="checkbox" name = "rember"	/>7天免登陆
	         <input type="submit" value="登录" />
	         
	         <span style="color:red;">
	         
	         	${errMsg}
	         
	         </span>
	                  
	 </form>
	         
	</body>
</html>
