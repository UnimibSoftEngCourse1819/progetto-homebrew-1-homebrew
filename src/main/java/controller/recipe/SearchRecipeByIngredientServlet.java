package controller.recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.recipe.IngredientRecipe;
import model.recipe.RecipeDao;

@WebServlet("/SearchRecipeByIngredient")
public class SearchRecipeByIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				ArrayList<IngredientRecipe> ingRecipes = new ArrayList<>();
				IngredientRecipe ingRecipe = null;
				
				for (int i = 10000001; i <= 10000018; i++) {
					if (request.getParameter("ingrName-"+i) != null && !request.getParameter("ingrName-"+i).equals("")) {
						int quantity = Integer.parseInt((String) request.getParameter("ingrName-"+i));
						if (quantity > 0) {
							ingRecipe = new IngredientRecipe(-1, i, null, quantity, null);
						}
						ingRecipes.add(ingRecipe);
					}
				}
				
				RecipeDao recipeDao = new RecipeDao();
				recipeDao.searchByIngredients(ingRecipes);
				
				response.sendRedirect("./recipes");
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
