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

import model.brew.Brew;
import model.brew.BrewDao;
import model.recipe.IngredientRecipe;
import model.recipe.IngredientRecipeDao;
import model.recipe.Recipe;
import model.recipe.RecipeDao;
import model.user.User;
import model.user.UserDao;

// NOT IN MENU GENERAL + PERSONAL -> GET SECTION IN SESSION
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
				String section = (String) session.getAttribute("section");
				User user = (User) session.getAttribute("user");
				RecipeDao recipeDao = new RecipeDao();

				Recipe recipe = recipeDao.findRecipeByID(id);
				UserDao userDao = new UserDao();
				User userRecipe = userDao.selectUserById(recipe.getUserID());

				if (recipe.getVisibility().equals("public")
						|| (recipe.getVisibility().equals("private") && user.getUserID() == recipe.getUserID())) {
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();

					List<IngredientRecipe> ingredientsRecipe = ingredientRecipeDao.findIngredientsRecipe(id);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeSingle.jsp");

					BrewDao brewDao = new BrewDao();
					List<Brew> brews = brewDao.findBrewByRecipeID(id);

					if (user.getUserID() == recipe.getUserID()) {
						request.setAttribute("editable", true);
					}
					session.setAttribute("recipe", recipe);
					request.setAttribute("recipe", recipe);
					request.setAttribute("userRecipe", userRecipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("brews", brews);
					request.setAttribute("page", "recipe");
					request.setAttribute("section", section);
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
					UserDao userDao = new UserDao();
					User userRecipe = userDao.selectUserById(recipe.getUserID());

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeSingle.jsp");
					request.setAttribute("recipe", recipe);
					request.setAttribute("userRecipe", userRecipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("page", "recipe");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("./recipe");
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
				int recipeID = 0;
				try {
					recipeID = (int) Integer.parseInt((String) request.getParameter("recipeID"));
				} catch (NumberFormatException e) {
					logger.log(Level.SEVERE, "Parser error", e);
				}

				session.setAttribute("recipeID", recipeID);
				response.sendRedirect("./edit_recipe");
			} else {
				response.sendRedirect("./");
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
