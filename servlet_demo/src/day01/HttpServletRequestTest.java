package day01;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpServletRequest 接口中的重要方法
 * 
 * http://localhost:8080/servlet_demo1/request
 * 
 * http://localhost:8080/servlet_demo1/request.jsp  ---> 点击登录   ---> 进入到 HttpServletRequestTest
 * 
 * 
 * http://192.168.50.1:8080/servlet_demo1/request.jsp
 * 
 * @author zte
 *
 */
public class HttpServletRequestTest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)  throws IOException,  ServletException
	{ 
		// 设置post请求体编码方式(post请求才有请求体) ---> 解决post请求的乱码问题
		request.setCharacterEncoding("utf-8");
		
		// 解决响应乱码问题
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		/**
		 * 后台接收单个请求参数
		 * 用户名: <input type="text" name="username" id="username" />
		 */
		String username = request.getParameter("username"); // 获取表单中的提交的用户名的值
		System.out.println("后台接收单个请求参数  username:" + username);
		
	    /**
	     * 后台接收多个请求参数 
	     */
		// Map中的key是请求参数的key
		// Map中的key是请求参数的value(数组多个值 )
		Map<String, String[]> paramMap = request.getParameterMap(); 
		/*
		Set<Entry<String, String[]>> entrySet = paramMap.entrySet();
		Iterator<Entry<String, String[]>> it = entrySet.iterator();
		while (it.hasNext())
		{
			Entry<String, String[]> entry = it.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		*/
		
		/*
		for (Entry<String, String[]> entry : paramMap.entrySet())
		{
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		// http://localhost:8080/servlet_demo1/request
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("请求地址URL:" + requestURL);
		*/
		
		// /servlet_demo1/request =  getContextPath() + getServletPath()
		String requestURI = request.getRequestURI();
		System.out.println("请求地址URI:" + requestURI);
		
		// /request
		String servletPath = request.getServletPath();
		System.out.println("servletPath:" + servletPath);
		
		// /servlet_demo1
		String contextPath = request.getContextPath();
		System.out.println("上下文路径(应用名):" + contextPath);
		
		// 黑名单
		String remoteIp = request.getRemoteAddr();
		System.out.println("远程访问者的IP地址:  " + remoteIp);
		
		// 会话
		HttpSession session = request.getSession();
		System.out.println("HttpSession:  " + session);
		
		/**
		 * servlet上下文: 非常重要
		 */
		// 解决:  java.lang.NoSuchMethodError: javax.servlet.http.HttpServletRequest.getServletContext()Ljavax/servlet/ServletContext;
		ServletContext servletContext = request.getServletContext();
		System.out.println("servlet上下文:  " + servletContext);
		
		/**
		 *  请求转发(服务器内部跳转)
		 *     地址栏不会发生变化
		 *     request.getRequestDispatcher("/servletLife") 一定不要写应用名
		 */
		request.getRequestDispatcher("/servletLife").forward(request, response);
		
		
	}
}
