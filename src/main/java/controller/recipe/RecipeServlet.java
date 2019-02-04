package controller.recipe;

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

import model.recipe.Recipe;
import model.recipe.RecipeDao;

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = (int) Integer.parseInt((String) request.getParameter("n"));
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				/*
				 * User user = (User) session.getAttribute("user");
				 * 
				 * if (request.getParameter("id") != null) { int idRecipe =
				 * Integer.parseInt((String) request.getParameter("id")); RecipeDao recipeDao =
				 * new RecipeDao();
				 * 
				 * Recipe recipe = recipeDao.findRecipeByID(idRecipe); if (user.getId() ==
				 * recipe.getUserID()) { RequestDispatcher dispatcher =
				 * getServletContext().getRequestDispatcher("/jsp/recipeEdit.jsp");
				 * request.setAttribute("recipe", recipe); dispatcher.forward(request,
				 * response); } }
				 * 
				 * RecipeDao recipeDao = new RecipeDao(); List<Recipe> recipes =
				 * recipeDao.findAllRecipes((int) session.getAttribute("user"));
				 * 
				 * RequestDispatcher dispatcher =
				 * getServletContext().getRequestDispatcher("/jsp/recipe.jsp");
				 * request.setAttribute("recipes", recipes); request.setAttribute("page",
				 * "recipe"); dispatcher.forward(request, response);
				 */

			} else {
				RecipeDao recipeDao = new RecipeDao();
				Recipe recipe = recipeDao.findRecipeByID(id);

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipe.jsp");
				request.setAttribute("recipe", recipe);
				request.setAttribute("page", "recipe");
				dispatcher.forward(request, response);

			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
