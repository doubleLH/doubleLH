package day01;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import util.FileNameUtil;
import util.Md5Password;
import util.ReadPropertiesUtil;

import entity.User;
import mapper.UserMapperImpl;

/**
 * http://localhost:8080/servlet_demo1/register.jsp
 * http://localhost:8080/servlet_demo1/user/regist
 * http://localhost:8080/servlet_demo1/user/login
 * 
 * @author zte
 *
 */
public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 当用户发送http://localhost:8080/servlet_demo1/user/regist此请求转发到 WEB-INF/pages/register.jsp页面
		String requestURI = request.getRequestURI();
		String path = requestURI.substring(requestURI.lastIndexOf("/"));
			if ("/regist".equals(path))
		{
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		}
		
			else if("/login".equals(path))
		{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String requestURI = request.getRequestURI();
		// System.out.println("requestURI:" + requestURI);
		
		/*
		 requestURI:/servlet_demo1/user/regist
		 requestURI:/servlet_demo1/user/login
		 */
		
		String path = requestURI.substring(requestURI.lastIndexOf("/"));
		// System.out.println("requestURI:" + path);
		
		if ("/regist".equals(path))
		{
			// 调用注册方法
			registUser(request, response);
		}else if("/login".equals(path))
		{
			// 调用登录方法
			loginUser(request,response);
		}
	}
/**
 * 用户登录
 * @throws IOException 
 * @throws ServletException 
 * 
 */
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserMapperImpl mapper = new	UserMapperImpl();
		 // 后台接收前台传递过来的参数
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String md5Password = Md5Password.getMd5(password);
		
		 String sql = new StringBuffer()
		                   .append(" select ")
		                   .append(" * ")
		                   .append(" from ")
		                   .append(" t_user ")
		                   .append(" where ")
		                   .append(" username = ? && password=? ")
		                   .toString();
		 
		
		
         
        Map<String,String> readProps = ReadPropertiesUtil.readProps();
        
        // 数据源配置
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName((String)readProps.get("driverClassName"));
        dataSource.setUrl((String)readProps.get("url"));
        dataSource.setUsername((String)readProps.get("username"));
        dataSource.setPassword((String)readProps.get("password"));
	        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        List<User> user = jdbcTemplate.query(sql, mapper,new Object[]{username,password});
        
        if("admin".equals(username) && "123".equals(password))
        {
        	request.setAttribute("errorlogin", "登录失败");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else
        {
        	request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);;
        }
	}
	
	
//	/**
//	 * 用户注册返回主键
//	 * 
//	 */
//	public 
	/**
	 * 用户注册
	 * @throws ServletException *
	 * 
	 */
	public void registUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		Part part = request.getPart("pic");
        
        // form-data; name="pic"; filename="baby.jpg"
        String headerInfo = part.getHeader("content-disposition");
        
        
        // 截取headerInfo字符串  ---> baby.jpg
        String fileName = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
        System.out.println(headerInfo);
        
        String renameFileName = FileNameUtil.renameFileName(fileName);
        
        // 我们本地的图片要上传到 --->   web服务器的地址
        String saveServerBasePath = getServletContext().getRealPath("/upload");
        String saveServerPath = saveServerBasePath + File.separator + renameFileName;
        System.out.println(saveServerPath);
		
        part.write(saveServerPath);
        
        //相对路径
        String path = "/upload"+File.separator +renameFileName;
		 // 后台接收前台传递过来的参数
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String md5Password = Md5Password.getMd5(password);
		 String sql = new StringBuffer()
		                   .append(" insert into ")
		                   .append(" t_user(username, password,address) ")
		                   .append(" values(?, ?, ? ) ")
		                   .toString();
         User user = new User();
         
         user.setUsername(username);
         user.setPassword(password);
        
         
         Map<String,String> readProps = ReadPropertiesUtil.readProps();
         
         // 数据源配置
         DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName((String)readProps.get("driverClassName"));
         dataSource.setUrl((String)readProps.get("url"));
         dataSource.setUsername((String)readProps.get("username"));
         dataSource.setPassword((String)readProps.get("password"));
	        
	     JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//	     jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
	   
	     
	     //成功之后要转发
	     try 
	     {
			 jdbcTemplate.update(sql, user.getUsername(),  md5Password,path);
			 
			 // 注册成功之后要转发
			 request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (DataAccessException e) 
		{
			 // TODO 注册失败失败的原因 --> 写到regist.jsp页面中(告诉给用户)
			 e.printStackTrace();
			 
			 // 设置属性 ---> 注册失败失败的原因 --> 写到regist.jsp页面中(告诉给用户)
			 request.setAttribute("errorMsg", "注册失败:" + e.getMessage());
			 request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		} catch (Exception e) 
		{
			 // TODO 注册失败失败的原因 --> 写到regist.jsp页面中(告诉给用户)
			 e.printStackTrace();
			 
			 // 设置属性 ---> 注册失败失败的原因 --> 写到regist.jsp页面中(告诉给用户) EL表达式(前提:isELIgnored="false" 使用EL表达式的意思) :  ${errorMsg}
			 request.setAttribute("errorMsg", "注册失败:" + e.getMessage());
			 request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		}
	}
   
}
