package cookie;
/**
 * logger4j.jar  在控制台打印，记录一份日志文件   .properties
 * 
 *    logback.jar
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CookieUtil;
/**
 * 服务端对cookie的CRUD
 * 
 * 增: Create    ---> 创建Cookie
 * 查: Read      ---> 查询Cookie
 * 改: Update    ---> 修改Cookie
 * 删 : Delete    ---> 删除Cookie
 * 
 * http://localhost:8080/servlet_demo1/cookie/create
 * http://localhost:8080/servlet_demo1/cookie/update
 * http://localhost:8080/servlet_demo1/cookie/read
 * http://localhost:8080/servlet_demo1/cookie/delete
 * 
 * 
 * http://localhost:8080/servlet_demo1/create.cookie
 * http://localhost:8080/servlet_demo1/update.cookie
 * http://localhost:8080/servlet_demo1/read.cookie
 * http://localhost:8080/servlet_demo1/delete.cookie
 * 
 * 
 * 
 * @author zte
 *
 */


@WebServlet("/cookie/*")
//@WebServlet("*.cookie") //扩展匹配    只要以.cookie结尾 的请求 都能进入到ServerCookieCRUD这个servlet
public class ServerCookieCRUD extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// C 增   http://localhost:8080/servlet_demo1/cookie/create
		String requestURI = request.getRequestURI();
		
		// /servlet_demo1/cookie/create
		System.out.println(requestURI);
		
		if(requestURI.indexOf("create") != -1)
		{
//			CookieUtil.addCookie("username", "ly", 60, response);
			
			addCookie(request, response);
		}
		else if(requestURI.indexOf("update") != -1)
		{
			updateCookie(request,response, requestURI, requestURI);
		}
		else if(requestURI.indexOf("read") != -1)
		{
			readCookie(request,response);
		}
		else if(requestURI.indexOf("delete") != -1)
		{
			deleteCookie(request,response);
		}
		
		
		
	}


/**
 * 添加的方法
 * @param request
 * @param response
 * @throws UnsupportedEncodingException 
 */
	public  void addCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
			
			String cookieValue = "张三";
			
			//给cookieValue 值     utf - 8 
			//取username  cookie值的时候  要解码
			cookieValue = URLEncoder.encode(cookieValue,"utf-8");
			Cookie cookie = new Cookie("username", cookieValue);
			
			//设置cookie的超时时间    单位是秒
			cookie.setMaxAge(60);
			
			
			//设置cookie的path
			cookie.setPath(request.getContextPath());
			
			//在localhost后面。不加应用名
//			cookie.setPath("/");
			
			//将cookie 写入到浏览器
			response.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("password", "123");
			response.addCookie(cookie2);
		
	}
	/**
	 * 修改
	 * @param request
	 * @param response
	 */
	
	public  void updateCookie(HttpServletRequest request, HttpServletResponse response,String name,String value) {
		Cookie cookie = new Cookie("username", "ww");
		response.addCookie(cookie);
		
		
	}
	
	/**
	 * 
	 * 查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	 public void readCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	    {
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null && cookies.length != 0)
	        {
	            // 遍历cookie数组
	            for (Cookie c : cookies)
	            {
	                if ("username".equals(c.getName()))
	                {
	                    // 记日志  Logger ---> log4j.jar(log4j.properties  自己搞----> 定位bugger 日志文件)  logback.jar   slf4j.jar
	                    System.out.println(c.getName() + "=" + URLDecoder.decode(c.getValue(), "utf-8"));
	                }
	            }
	        }
	    }

	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void deleteCookie(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		Cookie cookie = new Cookie("username", "sz");
		
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
		
}
