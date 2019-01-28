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
import model.user.User;


@WebServlet("/home")
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

		// get request parameters for username and password
		String username = request.getParameter("user");
		String password = request.getParameter("pass");

		User user = login.match(username, password);

		if (user != null) {
			// get the old session and invalidate
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}
			// generate a new session
			HttpSession session = request.getSession(true);

			// setting session to expiry in 20 mins
			session.setMaxInactiveInterval(20 * 60);
			session.setAttribute("user", user);
			session.setAttribute("logged", true);


			// Cookie message = new Cookie("message", "Welcome");
			// Cookie rightsCookie = new Cookie("rights", rights);

			// response.addCookie(message);
			// response.addCookie(rightsCookie);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/homepage.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			String error = "Login errata";
			request.setAttribute("errorLogin", error);
			dispatcher.forward(request, response);
		}
	}
}