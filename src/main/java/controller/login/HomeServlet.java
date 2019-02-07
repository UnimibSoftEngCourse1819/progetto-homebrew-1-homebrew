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

//IN MENU -> SET SECTION IN SESSION
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");

			if (session != null && session.getAttribute("user") != null) {
				session.setAttribute("section", "general");

				User user = (User) session.getAttribute("user");
				request.setAttribute("user", user);
				request.setAttribute("logged", true);
				request.setAttribute("page", "home");

				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("/homebrew/");

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
				session.setAttribute("username", username);
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