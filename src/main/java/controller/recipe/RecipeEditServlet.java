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

				if (recipe.getVisibility().equals("public")
						|| (recipe.getVisibility().equals("private") && user.getId() == recipe.getUserID())) {
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();

					List<IngredientRecipe> ingredientsRecipe = ingredientRecipeDao.findIngredientsRecipe(recipeID);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeEdit.jsp");
					request.setAttribute("recipe", recipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("page", "edit_recipe");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/homebrew/recipes");
				}
			} else {
				response.sendRedirect("/homebrew/recipes");
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
