package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8080/servlet_demo1/response
 * @author zte
 *
 */
@WebServlet("/response")
public class HttpServletResponseTest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * 一般在
	 *    查询成功的之后用转发
	 *    增 删  改 成功 用重定向
	 *    
	 *    
	 *    查询失败的之后用重定向？
	 *    增 删  改 失败 用重定向？
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/**
		 * 将指定的内容写入到客户端浏览器  ---> ajax的时候用(局部刷新)
		 */
		PrintWriter out = response.getWriter();
		out.println("世间安得两全法,不负如来不负卿");
		
		// OutputStream outputStream = response.getOutputStream();
		
		Cookie cookie = new Cookie("SSOCookie", "sso");
		response.addCookie(cookie);  // 将cookie写入到客户端浏览器
		
		/**
		 *  响应重定向(客户端跳转)
		 *     地址栏改变
		 *     response.sendRedirect("") 一定要写应用名
		 */
		response.sendRedirect(request.getContextPath() + "/servletLife");
	}
    
}
