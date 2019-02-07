package controller.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.login.Login;
import model.user.User;
import model.user.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			if (session != null) {
				request.setAttribute("page", "login");
				dispatcher.forward(request, response);
				session.removeAttribute("alertMessage");
				session.removeAttribute("alertType");
				session.invalidate();
			} else {
				request.setAttribute("page", "login");
				dispatcher.forward(request, response);
			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Login login = new Login();
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if (login.match(username, password)) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(20 * 60);
				UserDao userDao = new UserDao();
				User user = userDao.selectUserByEmail(username);
				session.setAttribute("user", user);
				session.setAttribute("logged", true);
				response.sendRedirect("/homebrew/home");
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
				String error = "Login errata";
				request.setAttribute("errorLogin", error);
				request.setAttribute("page", "login");
				dispatcher.forward(request, response);
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}

}