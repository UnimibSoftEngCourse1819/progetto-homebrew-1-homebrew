package controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.login.Login;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login login = new Login();

		//get request parameters for username and password
		String username = request.getParameter("user");
		String password = request.getParameter("pass");

		if (login.match(username, password)) {
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			session.setMaxInactiveInterval(20 * 60);


			// Cookie message = new Cookie("message", "Welcome");
			// Cookie rightsCookie = new Cookie("rights", rights);

			// response.addCookie(message);
			// response.addCookie(rightsCookie);

			response.sendRedirect("/homebrew/home");

		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			String error = "Login errata";
			request.setAttribute("errorLogin", error);
			dispatcher.forward(request, response);
		}
	}


}