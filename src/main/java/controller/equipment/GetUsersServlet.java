package controller.equipment;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/prova4")
public class GetUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String rights = (String) session.getAttribute("rights");
			if (rights.equals("admin")) {

				UserDao userDao = new UserDao();

				String action = request.getParameter("action");
				String idStr = request.getParameter("id");
				if ("delete".equals(action) && idStr != null) {
					int id = Integer.parseInt(idStr);
					userDao.deleteUser(id);
				}
				List<User> users = userDao.findAllUsers();

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/menage/ListUser.jsp");
				request.setAttribute("users", users);
				dispatcher.forward(request, response);

			}
		}

	}
*/
}