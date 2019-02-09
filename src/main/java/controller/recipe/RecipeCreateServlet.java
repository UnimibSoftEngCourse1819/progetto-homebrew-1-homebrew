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

@WebServlet("/new_recipe")
public class RecipeCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				IngredientDao ingredientDao = new IngredientDao();
				List<Ingredient> ingredients = ingredientDao.findAllIngredient();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeCreate.jsp");
				request.setAttribute("ingredients", ingredients);
				request.setAttribute("page", "createRecipe");
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
			String name = request.getParameter("name");
			String visibility = request.getParameter("visibility");
			String description = request.getParameter("description");

			if (session != null && session.getAttribute("user") != null) {

				User user = (User) session.getAttribute("user");

				RecipeDao recipeDao = new RecipeDao();
				int step = 1;
				String paramStep = "step-";
				Map<Integer, String> steps = new HashMap<>();
				while (request.getParameter(paramStep + step) != null) {
					String text = request.getParameter(paramStep + step);
					steps.put(step, text);
					step++;
				}
				String image = "images/recipes/beer_" + ((int) (Math.random() * 4) + 1) + ".jpg";

				Recipe recipe = new Recipe(0, user.getUserID(), name, null, description, visibility, image, steps);

				int recipeID = recipeDao.createRecipe(recipe);
				if (recipeID > 0) {
					IngredientDao ingredientDao = new IngredientDao();
					List<Ingredient> ingredients = ingredientDao.findAllIngredient();

					List<IngredientRecipe> ingredientsRecipe = new ArrayList<IngredientRecipe>();

					Iterator<Ingredient> iterator = ingredients.iterator();
					while (iterator.hasNext()) {
						Ingredient ingredient = iterator.next();
						int quantity = Integer
								.parseInt(request.getParameter("valueIngr-" + ingredient.getIngredientID()));
						String measure = request.getParameter("measureIngr-" + ingredient.getIngredientID());
						IngredientRecipe ingredientRecipe = new IngredientRecipe(recipeID, ingredient.getIngredientID(),
								ingredient.getName(), quantity, measure);
						ingredientsRecipe.add(ingredientRecipe);

					}
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();
					ingredientRecipeDao.createIngredientsRecipe(ingredientsRecipe);
					session.setAttribute("alertMessage", "Ricetta creata con successo");
					session.setAttribute("alertType", "success");
					if (((String) session.getAttribute("section")).equals("personal")) {
						response.sendRedirect("/homebrew/my_recipes");
					} else {
						response.sendRedirect("/homebrew/recipes");
					}
				}

			} else {
				session.setAttribute("alertMessage", "Ricetta non creata");
				session.setAttribute("alertType", "error");
				if (((String) session.getAttribute("section")).equals("personal")) {
					response.sendRedirect("/homebrew/my_recipes");
				} else {
					response.sendRedirect("/homebrew/recipes");
				}
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
