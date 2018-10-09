package cookie;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import entity.User;
import mapper.UserMapperImpl;
import util.CookieUtil;
import util.ReadPropertiesUtil;

/**
 * 7天免登陆
 * @author Administrator
 *
 */

@WebServlet("/nolanding7")
public class UserNolanding extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		UserMapperImpl mapper = new	UserMapperImpl();
		//获取用户在登录界面上输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		 String sql = new StringBuffer()
		                   .append(" select ")
		                   .append(" * ")
		                   .append(" from ")
		                   .append(" t_login ")
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
       
       if(!user.isEmpty())
       {
//    	    如果用户勾选了7天免登录
           if (request.getParameter("rember") != null)
           {
               // 添加2个cookie  username  和  pwd 分别设置这2个cookie有效期为7天
               /*CookieUtil.addCookie("username", username, 7 * 24 * 60 * 60, response);
               CookieUtil.addCookie("pwd", password, 7 * 24 * 60 * 60, response);*/
           }
           
           // 用户登录成功 ---> 重定向
           response.sendRedirect(request.getContextPath() + "/success.jsp");
          
       }
       else
       {
    	// 用户登录失败 ---> 转发
           request.setAttribute("errMsg", "用户名或者密码错误");
           request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
       }
       
       
   }

}
