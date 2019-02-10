package controller.recipe;

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
import model.recipe.IngredientRecipe;
import model.recipe.Recipe;
import model.recipe.RecipeDao;
import model.user.User;

//IN MENU -> SET SECTION IN SESSION

@WebServlet("/recipes")
public class RecipeListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				session.setAttribute("section", "general");

				User user = (User) session.getAttribute("user");
				RecipeDao recipeDao = new RecipeDao();
				List<Recipe> recipes = recipeDao.findAllRecipesUser(user.getUserID());

				IngredientDao ingredientDao = new IngredientDao();
				List<Ingredient> ingredient = ingredientDao.findAllIngredient();

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeList.jsp");
				request.setAttribute("recipes", recipes);
				request.setAttribute("page", "recipes");
				request.setAttribute("section", "general");
				request.setAttribute("ingredient", ingredient);
				dispatcher.forward(request, response);
				session.removeAttribute("alertMessage");
				session.removeAttribute("alertType");

			} else {
				RecipeDao recipeDao = new RecipeDao();
				List<Recipe> recipes = recipeDao.findAllRecipes();

				IngredientDao ingredientDao = new IngredientDao();
				List<Ingredient> ingredient = ingredientDao.findAllIngredient();

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeList.jsp");
				request.setAttribute("recipes", recipes);
				request.setAttribute("ingredient", ingredient);

				request.setAttribute("page", "recipes");

				dispatcher.forward(request, response);
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

			RecipeDao recipeDao = new RecipeDao();
			List<Recipe> recipes = null;

			if (request.getParameter("typeSearch") != null
					&& (request.getParameter("typeSearch")).equals("searchName")) {
				String name = request.getParameter("nameRecipe");
				recipes = recipeDao.findRecipesByName(name);
			}

			if (request.getParameter("typeSearch") != null
					&& (request.getParameter("typeSearch")).equals("searchIngredients")) {
				List<IngredientRecipe> ingRecipes = new ArrayList<>();
				IngredientRecipe ingRecipe = null;
				for (int i = 10000001; i <= 10000018; i++) {
					if (request.getParameter("ingrName-" + i) != null
							&& !request.getParameter("ingrName-" + i).equals("")) {
						int quantity = Integer.parseInt((String) request.getParameter("ingrName-" + i));
						if (quantity > 0) {
							ingRecipe = new IngredientRecipe(0, i, null, quantity, null);
						}
						ingRecipes.add(ingRecipe);
					}
				}
				recipes = recipeDao.findRecipesByIngredients(ingRecipes);
			}
			if (recipes != null) {
				IngredientDao ingredientDao = new IngredientDao();
				List<Ingredient> ingredient = ingredientDao.findAllIngredient();

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeList.jsp");
				request.setAttribute("recipes", recipes);
				request.setAttribute("ingredient", ingredient);

				request.setAttribute("page", "recipes");

				dispatcher.forward(request, response);

			} else {
				session.setAttribute("alertMessage", "La ricerca non ha prodotto risultati");
				session.setAttribute("alertType", "error");
				response.sendRedirect("./recipes");
			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
