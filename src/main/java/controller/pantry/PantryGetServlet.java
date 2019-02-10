package controller.pantry;

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

import model.pantry.Pantry;
import model.pantry.PantryDao;
import model.user.User;

@WebServlet("/pantry")
public class PantryGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				PantryDao pantryDao = new PantryDao();
				List<Pantry> pantry = pantryDao.findUserPantry(userID);

				request.setAttribute("pantry", pantry);
				request.setAttribute("page", "pantry");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/pantryList.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("./");
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}