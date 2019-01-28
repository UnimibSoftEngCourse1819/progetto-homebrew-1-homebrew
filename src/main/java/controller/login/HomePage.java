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

import model.user.User;
import model.user.UserDao;

@WebServlet("/home")
public class HomePage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(false);
			if (session != null) {

				String username = (String) session.getAttribute("username");
				UserDao userDao = new UserDao();
				User user = userDao.selectUserByEmail(username);
				session.setAttribute("user", user);
				session.setAttribute("logged", true);

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/homepage.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}