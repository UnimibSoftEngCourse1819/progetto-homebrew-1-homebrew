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

@WebServlet("/brews")
public class BrewsListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				
				BrewDao brewDao = new BrewDao();
				List<Brew> brews = brewDao.findAllBrews();

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/brewList.jsp");
				request.setAttribute("brews", brews);
				request.setAttribute("page", "brews");
				dispatcher.forward(request, response);

			} else {
				response.sendRedirect("/homebrew/recipes");
			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
