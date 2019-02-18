package controller.recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import model.recipe.IngredientRecipeDao;
import model.recipe.Recipe;
import model.recipe.RecipeDao;
import model.user.User;

@WebServlet("/edit_recipe")
public class RecipeEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				int recipeID = (int) session.getAttribute("recipeID");

				User user = (User) session.getAttribute("user");
				RecipeDao recipeDao = new RecipeDao();

				Recipe recipe = recipeDao.findRecipeByID(recipeID);

				if (user.getUserID() == recipe.getUserID()) {
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();
					IngredientDao ingredientDao = new IngredientDao();

					List<IngredientRecipe> ingredientsRecipe = ingredientRecipeDao.findIngredientsRecipe(recipeID);
					List<Ingredient> ingredients = ingredientDao.findAllIngredient();

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeEdit.jsp");
					request.setAttribute("recipe", recipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("ingredients", ingredients);
					request.setAttribute("page", "edit_recipe");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/homebrew/recipes");
				}
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
		String redirect = "./recipes";

		HttpSession session = request.getSession(false);
		int recipeID = -1;
		try {
			recipeID = Integer.parseInt(request.getParameter("recipeID"));
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "Parser error", e);
		}
		String name = request.getParameter("name");
		String visibility = request.getParameter("visibility");
		String description = request.getParameter("description");

		if (session != null && session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			RecipeDao recipeDao = new RecipeDao();
			Recipe recipe = recipeDao.findRecipeByID(recipeID);
			if (user.getUserID() == recipe.getUserID()) {
				int step = 1;
				String paramStep = "step-";
				Map<Integer, String> steps = new HashMap<>();
				while (request.getParameter(paramStep + step) != null) {
					String text = request.getParameter(paramStep + step);
					steps.put(step, text);
					step++;
				}
				recipe.setName(name);
				recipe.setDescription(description);
				recipe.setVisibility(visibility);
				recipe.setSteps(steps);
				recipeDao.updateRecipe(recipe);
				IngredientDao ingredientDao = new IngredientDao();
				List<Ingredient> ingredients = ingredientDao.findAllIngredient();
				List<IngredientRecipe> ingredientsRecipe = new ArrayList<>();
				Iterator<Ingredient> iterator = ingredients.iterator();
				while (iterator.hasNext()) {
					Ingredient ingredient = iterator.next();
					int quantity = Integer.parseInt(request.getParameter("valueIngr-" + ingredient.getIngredientID()));
					String measure = request.getParameter("measureIngr-" + ingredient.getIngredientID());
					IngredientRecipe ingredientRecipe = new IngredientRecipe(recipe.getRecipeID(),
							ingredient.getIngredientID(), ingredient.getName(), quantity, measure);
					ingredientsRecipe.add(ingredientRecipe);
				}
				IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();
				ingredientRecipeDao.updateIngredientsRecipe(recipe.getRecipeID(), ingredientsRecipe);
				session.setAttribute("alertMessage", "Ricetta modificata con successo");
				session.setAttribute("alertType", "success");
				redirect = "./recipe?n=" + recipeID;
			}
		}
		try {
			response.sendRedirect(redirect);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}
}
