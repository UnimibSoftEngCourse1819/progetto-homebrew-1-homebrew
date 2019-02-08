package controller.user;

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

//IN MENU -> SET SECTION IN SESSION
@WebServlet("/account")
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				session.setAttribute("section", "personal");
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				UserDao userDao = new UserDao();
				User getUser = userDao.selectUserById(userID);
				
				request.setAttribute("user", getUser);
				request.setAttribute("page", "account");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userData.jsp");
				dispatcher.forward(request, response);
					
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}