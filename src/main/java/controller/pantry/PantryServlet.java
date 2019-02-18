package controller.pantry;

import java.io.IOException;
import java.util.ArrayList;
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

import model.ingredient.Ingredient;
import model.ingredient.IngredientDao;
import model.pantry.Pantry;
import model.pantry.PantryDao;
import model.user.User;

@WebServlet("/pantry")
public class PantryServlet extends HttpServlet {
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
				IngredientDao ingredientDao = new IngredientDao();
				List<Ingredient> ingredients = ingredientDao.findAllIngredient();

				request.setAttribute("pantry", pantry);

				request.setAttribute("ingredients", ingredients);
				request.setAttribute("page", "pantry");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/pantry.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("./");
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirect = "./";
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			redirect = "./pantry";
			User user = (User) session.getAttribute("user");
			int userID = user.getUserID();
			ArrayList<Pantry> pantrys = new ArrayList<>();
			Pantry pantry = null;
			for (int i = 10000001; i <= 10000018; i++) {
				if (request.getParameter(i + "") != null && !request.getParameter(i + "").equals("")) {
					int avaliability = 0;
					try {
						avaliability = Integer.parseInt((String) request.getParameter(i + ""));
					} catch (NumberFormatException e) {
						logger.log(Level.SEVERE, "Parser error", e);
					}
					if (avaliability > 0) {
						pantry = new Pantry(userID, i, null, avaliability, null);
					}
					pantrys.add(pantry);
				}
			}
			if (!pantrys.isEmpty()) {
				PantryDao pantryDao = new PantryDao();
				int update = pantryDao.updatePantry(pantrys);

				if (update > 0) {
					session.setAttribute("alertMessage", "Dispensa modificata con successo");
					session.setAttribute("alertType", "success");
				} else {
					session.setAttribute("alertMessage", "Dispensa non modificata");
					session.setAttribute("alertType", "error");
				}
			} else {
				session.setAttribute("alertMessage", "Dispensa non modificata");
				session.setAttribute("alertType", "error");
			}
		}
		try {
			response.sendRedirect(redirect);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}