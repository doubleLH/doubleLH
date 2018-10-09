package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlackNameListFilter implements Filter{

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig= filterConfig;
	}
	

	@Override
	public void doFilter(ServletRequest res, ServletResponse resp, FilterChain chain)throws IOException, ServletException
	{
		HttpServletRequest request =  (HttpServletRequest)res;
		HttpServletResponse response =  (HttpServletResponse)resp;
		
		String remoteAddr = request.getRemoteAddr();
		
		
		String  blackNameStr = filterConfig.getInitParameter("blackNameList");
		
		
		if(blackNameStr.indexOf(remoteAddr)!= -1)
		{
			System.out.println("对不起，你是黑名单用户");
			
			response.sendRedirect(request.getContextPath()+"/balck.jsp");
			
			return;
		}
		
		chain.doFilter(request, response);
	}


	@Override
	public void destroy() {
		
		
	}
	

}
