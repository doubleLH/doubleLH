package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie  CRUD   工具类
 * @author Administrator
 *
 */
public class CookieUtil 
{
	/**
	 * 添加cookie      cookie的生命周期一个会话
	 * @param cookieName
	 * @param cookieValue
	 * @param response
	 */
	public static void  addCookie(String cookieName,String cookieValue,HttpServletResponse response)
	{
		
		try {
			Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "utf-8"));
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 自己添加cookie  的超时时间
	 * @param cookieName
	 * @param cookieValue
	 * @param maxAge
	 * @param response
	 */
	public static void  addCookie(String cookieName,String cookieValue,int maxAge,HttpServletResponse response)
	{
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	/**
	 * 删除cookie
	 * @param cookieName
	 * @param response
	 */
	public static void  deleteCookie(String cookieName,HttpServletResponse response)
	{
		addCookie(cookieName,"",0, response);
		
	}
	
	/**
     * 修改cookie ---> 修改cookie的值
     */
    public static void updateCookie(String cookieName, String cookieValue, HttpServletResponse response)
    {
        addCookie(cookieName, cookieValue, response);
    }
	
    
    
    /**
     * 查询Cookie
     *  根据指定的Cookiename查询出指定的Value
     */
    
    public static String readCookie(String cookieName, String cookieValue, HttpServletRequest request)
    {
    	Cookie [] cookies = request.getCookies();
		if( cookies != null && cookies.length != 0)
		{
			for (Cookie c : cookies)
			{
				if (cookieName.equals(c.getName()))
				{
					 // 记日志  Logger ---> log4j.jar(log4j.properties  自己搞----> 定位bugger 日志文件)  logback.jar   slf4j.jar
					try {
						return URLDecoder.decode(c.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		return cookieName;
    }

}
