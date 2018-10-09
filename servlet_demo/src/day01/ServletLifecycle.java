package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * http://localhost:8080/servlet_demo1/servletLife
 * 
 *     (1) servlet ---> 编译成 ServletLifecycle.class
 *     (2) 实例化此servlet(是由web容器创建)
 *     (3) 初始化servlet: init()  在service方法被调用之前执行 且只会被执行一次
 *     (4) 核心方法是service()/doGet()/doPost() ---> service方法的优先级高于doGet/doPost (service能处理get/post请求 我们一般都用serivce())
 *            当用户每请求一次此servlet,service方法就会被调用一次
 *     (5) 销毁servlet: 当停止web服务器的时候停止(servlet是运行在web容器中 Jboss WebLogic Tomcat)
 *     (6) gc会在适当的时机回收此servlet实例
 *     
 *     
 * @author zte
 *
 */
// 表示serlvet的访问路径: value or urlPatterns, 但是value 和  urlPatterns 不能同时使用,且都是数组形式 
// Java EE 6.0    and    >= Tomcat 7   and    >= Servlet 3.0
// @WebServlet("/servletLife")
// @WebServlet(urlPatterns="/servletLife",loadOnStartup=0)
public class ServletLifecycle extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化serlvet
	 */
	/*@Override
	public void init(ServletConfig config) throws ServletException 
	{
		System.out.println("ServletLifecycle.init()...");
	}*/
	
	/**
	 * service方法什么时候被调用: 每请求一次此servlet都会调用一次service(参数)方法
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		// 解决响应乱码问题
		resp.setContentType("text/html; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		System.out.println("ServletLifecycle.service()....");
		
		PrintWriter out = resp.getWriter();
		out.println("<h1>我美吗?</h1>");
	}


	/**
	 * HTTP常用的状态: 405 请求方式不支持
	 */
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		// 解决响应乱码问题
		resp.setContentType("text/html; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		System.out.println("ServletLifecycle.doPost()....");
		
		PrintWriter out = resp.getWriter();
		out.println("<h1>很美</h1>");
	}
	*/
	
    /**
     * 销毁servlet
     */
	@Override
	public void destroy()
	{
		System.out.println("ServletLifecycle.destroy()...");
	}
	
	
}
