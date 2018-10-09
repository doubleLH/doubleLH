package session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  http://localhost:8080/servlet_demo1/session/registerStep1.jsp
 * 
 */
@WebServlet("/DisperseRegisterStepOne")
public class DisperseRegisterStepOne extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("name", username);
		session.setAttribute("pwd", password);
		
		request.getRequestDispatcher("/session/registerStep2.jsp").forward(request, response);
		
	}
	

}
