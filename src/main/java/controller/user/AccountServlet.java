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

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		try {
			HttpSession session = request.getSession(false);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");

			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				request.setAttribute("user", user);
				request.setAttribute("page", "account");

				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("/homebrew/home");

			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}