package day01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;




@WebServlet(urlPatterns="/servletContext",initParams={@WebInitParam(name="encoding",value="utf-8")})
public class ServletContextTest extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    /**http://localhost:8080/servlet_demo1/servletContext
     * HttpServlet  extends GenericServlet
     * GenericServlet implements Servlet
     * 
     *    GenericServlet类中有一个getServletContext();
     */
    @Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		String encoding = config.getInitParameter("encoding");
		System.out.println("**********"+encoding);
	} 
    
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
           /**
            *  获取ServletContext方法1: GenericServlet 类中有一个getServletContext();
            */
          ServletContext application = getServletContext();
          System.out.println(application);
        
          /**
           * 获取ServletContext方法2:从request对象中的取
           */
          ServletContext application2 = request.getServletContext();
          System.out.println(application2);
          
          /**
           * 获取ServletContext方法3:通过ServletConfig对象获取(GenericServlet 类中有一个getServletConfig())
           */
           ServletConfig servletConfig = getServletConfig();  // ServletConfig  Servlet配置对象
           ServletContext application3 = servletConfig.getServletContext();
           System.out.println(application3);
           
           /**
            * ServletContext的作用:
            *    1.获取的是全局初始化参数
            *    2.文件上传时用:获取web服务器的根目录
            *    3.读取src下面的配置文件（了解）
	    *	 4.application作用域中放值    前台页面使用	
            */  
           
          // 调用初始化在web.xml中存放的参量
          
           //全局
//           String username = application3.getInitParameter("username");
//           System.out.println(username);
//           //局部
//           String username1 = this.getInitParameter("encoding");
//           System.out.println("++++++"+username1);
           
           //F:\MyEclipse 2017 Workspaces\.metadata\.me_tcat85\webapps\servlet_demo1\
           String realPath1 = application3.getRealPath("");
           System.out.println(realPath1);
           
           String realPath2 = application3.getRealPath("/");
           System.out.println(realPath2);
           
           // web服务器的根目录/upload
           //F:\MyEclipse 2017 Workspaces\.metadata\.me_tcat85\webapps\servlet_demo1/upload
           String realPath3 = application3.getRealPath("/upload");
           System.out.println(realPath3);
           
           // 跨平台的 斜杠
           String separator = File.separator;
           System.out.println(separator);
           
           // className的值在整个应用servlet_demo1中都能取到
           application3.setAttribute("className", "zte6");
           
           InputStream in = ServletContextTest.class.getClassLoader().getResourceAsStream("dataSource.properties");
           //java.io.ByteArrayInputStream@52c93640
           System.out.println(in);
           
           
            //   /WEB-INF/classes/dataSource.properties
           InputStream in2 = application3.getResourceAsStream("/WEB-INF/classes/dataSource.properties");
           //java.io.ByteArrayInputStream@65983660
           System.out.println(in2);
           
           long startTime = System.currentTimeMillis();
           
           // 文件copy  org.springframework.util.StreamUtils
           StreamUtils.copy(in2, new FileOutputStream("D:/1.properties"));
           long endTime = System.currentTimeMillis();
           System.out.println("cost: " + (endTime - startTime) + " 毫秒");
           
           // since jdk 1.7 有了 Files类 ---> 文件复制
           Files.copy(new File(application3.getRealPath("/") + "/WEB-INF/classes/dataSource.properties").toPath(), new FileOutputStream("D:/2.properties"));
    }

	

}
