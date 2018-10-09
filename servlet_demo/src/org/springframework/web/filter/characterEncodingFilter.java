package org.springframework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class characterEncodingFilter implements Filter{

	private String encoding = null;

	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		
	}
	
	
	
	@Override
	public void doFilter(ServletRequest res, ServletResponse resp, FilterChain filterChain)throws IOException, ServletException 
	{
		HttpServletRequest request =  (HttpServletRequest)res;
		HttpServletResponse response =  (HttpServletResponse)resp;
		
		request.setCharacterEncoding(encoding);
		
		//放行
		filterChain.doFilter(request, response);
	}

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
