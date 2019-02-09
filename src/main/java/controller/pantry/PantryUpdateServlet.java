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

@WebServlet("/edit_pantry")
public class PantryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				List<Ingredient> ingredient = new ArrayList<>();
				IngredientDao ingredientDao = new IngredientDao();
				ingredient = ingredientDao.findAllIngredient();

				request.setAttribute("ingredient", ingredient);
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
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				ArrayList<Pantry> pantrys = new ArrayList<>();
				Pantry pantry = null;
				for (int i = 10000001; i <= 10000018; i++) {
					if (request.getParameter(i + "") != null && !request.getParameter(i + "").equals("")) {
						int avaliability = Integer.parseInt((String) request.getParameter(i + ""));
						if (avaliability > 0) {
							pantry = new Pantry(userID, i, null, avaliability, null);
						}
						pantrys.add(pantry);
					}
				}

				if (pantrys.size() > 0) {
					PantryDao pantryDao = new PantryDao();
					int update = pantryDao.updatePantry(pantrys);

					if (update > 0) {
						session.setAttribute("alertMessage", "Dispensa modificata con successo");
						session.setAttribute("alertType", "success");
						response.sendRedirect("./pantry");
					} else {
						session.setAttribute("alertMessage", "Dispensa non modificata");
						session.setAttribute("alertType", "error");
						response.sendRedirect("./pantry");
					}
				} else {
					session.setAttribute("alertMessage", "Dispensa non modificata");
					session.setAttribute("alertType", "error");
					response.sendRedirect("./pantry");
				}

			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
