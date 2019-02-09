package controller.brew;

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

import model.brew.Brew;
import model.brew.BrewDao;
import model.user.User;

//IN MENU -> SET SECTION IN SESSION
@WebServlet("/my_brews")
public class BrewsUserListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				
				session.setAttribute("section", "personal");
				User user = (User) session.getAttribute("user");	

				BrewDao brewDao = new BrewDao();
				List<Brew> brews = brewDao.findAllBrewsUser(user.getUserID());

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/brewList.jsp");
				request.setAttribute("brews", brews);
				request.setAttribute("page", "my_brews");
				request.setAttribute("section", "personal");
				dispatcher.forward(request, response);

			} else {
				response.sendRedirect("./");
			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
