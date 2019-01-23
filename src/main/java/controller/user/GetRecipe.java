package controller.user;
/*
import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 
@WebServlet("/GetRecipe")
public class GetRecipe extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserDao user = new UserDao();

		
		if (login.match(username, password)) {
//get the old session and invalidate
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
//generate a new session
			HttpSession newSession = request.getSession(true);

//setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);

			Cookie message = new Cookie("message", "Welcome");
			response.addCookie(message);
			response.sendRedirect("/homebrew/LoginSuccess.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either username or password is wrong.</font>");
			rd.include(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Login login = new Login();

		// get request parameters for username and password
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		if (login.match(username, password)) {
			// get the old session and invalidate
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			// generate a new session
			HttpSession newSession = request.getSession(true);

			// setting session to expiry in 5 mins
			newSession.setMaxInactiveInterval(5 * 60);

			Cookie message = new Cookie("message", "Welcome");
			response.addCookie(message);
			response.sendRedirect("/homebrew/LoginSuccess.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either username or password is wrong.</font>");
			rd.include(request, response);
		}
		
	}
}
*/