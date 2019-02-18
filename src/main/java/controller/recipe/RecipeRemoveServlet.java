package controller.recipe;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.recipe.RecipeDao;

@WebServlet("/remove_recipe")
public class RecipeRemoveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.sendRedirect("./recipes");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String redirect = "./";
		if (session != null && session.getAttribute("user") != null) {
			if (((String) session.getAttribute("section")).equals("general")) {
				redirect = "./recipes";
			} else {
				redirect = "./my_recipes";
			}
			int recipeID = (int) session.getAttribute("recipeID");
			int recipePOST = 0;
			try {
				recipePOST = Integer.parseInt(request.getParameter("recipeID"));
			} catch (NumberFormatException e) {
				logger.log(Level.SEVERE, "Parser error", e);
			}
			int sqlResp = -1;
			if (recipeID == recipePOST) {
				RecipeDao recipeDao = new RecipeDao();
				sqlResp = recipeDao.removeRecipe(recipeID);
			}
			if (sqlResp != -1) {
				session.setAttribute("alertMessage", "Ricetta cancellata con successo");
				session.setAttribute("alertType", "success");
			} else {
				session.setAttribute("alertMessage", "Cancellazione non avvenuta");
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
