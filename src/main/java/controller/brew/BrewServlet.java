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

import model.recipe.IngredientRecipe;
import model.recipe.IngredientRecipeDao;
import model.recipe.Recipe;
import model.recipe.RecipeDao;
import model.user.User;

@WebServlet("/brew")
public class BrewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = (int) Integer.parseInt((String) request.getParameter("n"));
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {

				User user = (User) session.getAttribute("user");
				RecipeDao recipeDao = new RecipeDao();

				Recipe recipe = recipeDao.findRecipeByID(id);

				if (recipe.getVisibility().equals("public")
						|| (recipe.getVisibility().equals("private") && user.getUserID() == recipe.getUserID())) {
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();

					List<IngredientRecipe> ingredientsRecipe = ingredientRecipeDao.findIngredientsRecipe(id);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeSingle.jsp");

					if (user.getUserID() == recipe.getUserID()) {
						request.setAttribute("editable", true);
					}
					request.setAttribute("recipe", recipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("page", "recipe");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/homebrew/recipes");
				}

			} else {
				RecipeDao recipeDao = new RecipeDao();
				Recipe recipe = recipeDao.findRecipeByID(id);

				if (recipe.getVisibility().equals("public")) {
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();

					List<IngredientRecipe> ingredientsRecipe = ingredientRecipeDao.findIngredientsRecipe(id);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeSingle.jsp");
					request.setAttribute("recipe", recipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("page", "recipe");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/homebrew");
				}

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
				int recipeID = (int) Integer.parseInt((String) request.getParameter("recipeID"));

				session.setAttribute("recipeID", recipeID);
				response.sendRedirect("./edit_recipe");
			} else {
				response.sendRedirect("/homebrew");
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
