package controller.recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ingredient.Ingredient;
import model.ingredient.IngredientDao;
import model.pantry.Pantry;
import model.recipe.IngredientRecipe;
import model.recipe.RecipeDao;

public class SearchRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				List<IngredientRecipe> ingredients = new ArrayList<>();
				String name = null;
				IngredientRecipe ingRecipe = null;
				if (request.getParameter("name") != null ) {
					name = request.getParameter("name");
				}
				
				for (int i = 10000001; i <= 10000018; i++) {
					if (request.getParameter("ingrName-" + i) != null && !request.getParameter("ingrName-" + i).equals("")) {
						int intSearch = Integer.parseInt((String) request.getParameter("ingrName-" + i));
						if (intSearch > 0) {
							ingRecipe = new IngredientRecipe(-1, i, null, intSearch, null);
						}
						ingredients.add(ingRecipe);
					}
				}
				
				RecipeDao recipeDao = new RecipeDao();
				recipeDao.search(name, ingredients);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
