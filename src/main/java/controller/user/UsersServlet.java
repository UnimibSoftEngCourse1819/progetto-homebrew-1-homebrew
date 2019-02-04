package controller.user;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/GetUsers")
public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		List<User> users = null;
		if (session != null) {
			String rights = (String) session.getAttribute("rights");
			if (rights.equals("admin")) {

				UserDao userDao = new UserDao();

				String action = request.getParameter("action");
				String idStr = request.getParameter("id");
				if ("delete".equals(action) && idStr != null) {
					int id = -1;
					try {
						id = Integer.parseInt(idStr);
					} catch (NumberFormatException e) {
						logger.log(Level.SEVERE, "Parser error", e);
					}

					userDao.deleteUser(id);
				}
				users = userDao.findAllUsers();
			}
		}
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/menage/ListUser.jsp");
			request.setAttribute("users", users);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}