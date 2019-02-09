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

@WebServlet("/remove_recipe")
public class RecipeRemoveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("user") != null) {
				int recipeID = (int) session.getAttribute("recipeID");
				System.out.println(recipeID);
				/*
				 * User user = (User) session.getAttribute("user"); RecipeDao recipeDao = new
				 * RecipeDao();
				 * 
				 * Recipe recipe = recipeDao.findRecipeByID(recipeID);
				 * 
				 * if (user.getId() == recipe.getUserID()) { IngredientRecipeDao
				 * ingredientRecipeDao = new IngredientRecipeDao(); IngredientDao ingredientDao
				 * = new IngredientDao();
				 * 
				 * List<IngredientRecipe> ingredientsRecipe =
				 * ingredientRecipeDao.findIngredientsRecipe(recipeID); List<Ingredient>
				 * ingredients = ingredientDao.findAllIngredient();
				 * 
				 * RequestDispatcher dispatcher =
				 * getServletContext().getRequestDispatcher("/jsp/recipeEdit.jsp");
				 * request.setAttribute("recipe", recipe);
				 * request.setAttribute("ingredientsRecipe", ingredientsRecipe);
				 * request.setAttribute("ingredients", ingredients);
				 * request.setAttribute("page", "edit_recipe"); dispatcher.forward(request,
				 * response); } else { response.sendRedirect("/homebrew/recipes"); }
				 */
			} else {
				response.sendRedirect("./");
			}
		} catch (/* ServletException | */ IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("user") != null) {
				int recipeID = (int) session.getAttribute("recipeID");
				int recipePOST = Integer.parseInt(request.getParameter("recipeID"));
				int sqlResp = -1;
				if (recipeID == recipePOST) {
					RecipeDao recipeDao = new RecipeDao();
					sqlResp = recipeDao.removeRecipe(recipeID);
				}

				if (sqlResp != -1) {
					session.setAttribute("alertMessage", "Ricetta cancellata con successo");
					session.setAttribute("alertType", "success");
					response.sendRedirect("/homebrew/recipes");
				} else {
					session.setAttribute("alertMessage", "Impossibile cancellare la ricetta");
					session.setAttribute("alertType", "error");
					response.sendRedirect("/homebrew/recipes");
				}

			} else {
				response.sendRedirect("./");
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
