<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- http://localhost:8080/servlet_demo1/login3.jsp -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录页面</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		
		<script type="text/javascript">
		    $(function()
		    {
		        /**
		         7天免登陆
		                      当页面加载完成时, 从cookie中获取key为username和pwd的两个cookie值
		         */
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
                   // $("#nolanding7Form").submit();
                }	
                
                /**
                 * 切换验证码
                 */
                $(".updateImageCode").on('click', function()
                {
                    $("#img").attr("src", "${pageContext.request.contextPath}/createCode.code?id=" + new Date().getTime());
                });
                
                
                // 当用户输入的验证码框失去焦点时 采用$.ajax()发送请求到相应的后台
                $("[name='code']").blur(function()
                {
                    var options = 
                    {
                       "data"     : "code=" + $(this).val(),
                       "type"     : "post",
                       "url"      : "${pageContext.request.contextPath}/checkCode.code",
                       "dataType" : "json", 
                       "success"  : function(result)
                       {
                           if (result == "验证码输入错误")
                           {   
                              $("#errCode").html(result);
                           }else if (result == "验证码输入正确")
                           {
                              $("#errCode").html("");
                           }
                       },
                       error : function()
                       {
                           alert("发送ajax请求错误了");
                       }
                    }
                    
                    $.ajax
                    (
                       options
                    );
                });
                
                  /* $("[name='code']").on("blur", function()
                 {
					  $.getJSON
					  (
					      "${pageContext.request.contextPath}/checkCode.code",
					      {"code" : $(this).val()},
					      function(result)
						  {
					          if (result == "验证码输入错误")
		                      {   
		                         $("#errCode").html(result);
		                      }else if (result == "验证码输入正确")
		                      {
		                         $("#errCode").html("");
		                      }
		                      
						  }
					  );
                }); */
               
                      
		    });
		</script>
	</head>
	<body>
	     <form action="${pageContext.request.contextPath}/user/login" method="post">
	     <%-- <form id="nolanding7Form" action="${pageContext.request.contextPath}/nolanding7" method="post"> --%>
	     <%-- <form id="nolanding7Form" action="${pageContext.request.contextPath}/checkCode.code" method="post">  --%>
	                       用户名: <input type="text" name="username" id="username" /><br/>
	                       密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" id="password" /> <br/>
	         
	         <!-- <input type="checkbox" name="rember" />7天免登录 <br/> -->
	        
	         
	                     验证码:<a href="javascript:void(0);" class="updateImageCode">
			         <!-- 发送请求到${pageContext.request.contextPath}/createCode.code去生成验证码 -->
			         <img id="img" alt="图片加载失败" src="${pageContext.request.contextPath}/createCode.code" />
	              </a> 
	              <input type="text" name="code" /> <br/>
	          
	         <input type="submit" value="登录" /> 
	         <span style="color:red;">
	              ${errMsg}
	         </span>
	         
	         <span style="color:red;" id="errCode">
	         </span>
	         
	         
	     </form>
	</body>
</html>