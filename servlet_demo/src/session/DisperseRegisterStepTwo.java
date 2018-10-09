package session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 */
@WebServlet("/DisperseRegisterStepTwo")
public class DisperseRegisterStepTwo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object name = (String)request.getSession().getAttribute("name");
		Object pwd =(String)request.getSession().getAttribute("pwd");
		
		String sex = request.getParameter("sex");
		String job = request.getParameter("job");
		
		
		HttpSession session = request.getSession();
		
		session.setAttribute("name", name);
		session.setAttribute("pwd", pwd);
		session.setAttribute("sex", sex);
		session.setAttribute("job", job);
		
		
		response.sendRedirect(request.getContextPath()+"/session/success.jsp");
		
	}
	

}
