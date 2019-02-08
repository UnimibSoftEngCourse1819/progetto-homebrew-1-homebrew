package controller.brew;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

import model.equipment.EquipmentDao;
import model.ingredient.Ingredient;
import model.ingredient.IngredientDao;
import model.pantry.Pantry;
import model.pantry.PantryDao;
import model.recipe.IngredientRecipe;
import model.recipe.IngredientRecipeDao;
import model.recipe.Recipe;
import model.user.User;

@WebServlet("/new_brew")
public class BrewCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null && session.getAttribute("recipe") != null) {
				User user = (User) session.getAttribute("user");

				EquipmentDao eqDao = new EquipmentDao();
				int batchSize = eqDao.findBatchSize(user.getUserID());

				Recipe recipe = (Recipe) session.getAttribute("recipe");

				IngredientRecipeDao irDao = new IngredientRecipeDao();
				List<IngredientRecipe> ingredientsRecipe = irDao.findIngredientsRecipe(recipe.getRecipeID());

				PantryDao pnDao = new PantryDao();
				List<Pantry> pantries = pnDao.findUserPantry(user.getUserID());

				boolean brewable = canBrew(user.getUserID(), recipe.getRecipeID());

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/brewCreate.jsp");
				request.setAttribute("recipe", recipe);
				request.setAttribute("batchSize", batchSize);
				request.setAttribute("ingredientsRecipe", ingredientsRecipe);
				request.setAttribute("pantries", pantries);
				request.setAttribute("brewable", brewable);
				request.setAttribute("page", "createBrew");
				dispatcher.forward(request, response);

			} else {
				response.sendRedirect("/homebrew/recipes");
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

			if (session != null && session.getAttribute("user") != null && session.getAttribute("recipe") != null) {

				User user = (User) session.getAttribute("user");
				Recipe recipe = (Recipe) session.getAttribute("recipe");

				List<Pantry> pantryNew = newPantry(user.getUserID(), recipe.getRecipeID());
				PantryDao pantryDao = new PantryDao();
				pantryDao.updatePantry(pantryNew);

				IngredientDao ingredientDao = new IngredientDao();

				List<Ingredient> ingredients = ingredientDao.findAllIngredient();

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

	private boolean canBrew(int user, int recipe) {
		boolean resp = true;
		EquipmentDao eqDao = new EquipmentDao();
		int batchSize = eqDao.findBatchSize(user);
		IngredientRecipeDao irDao = new IngredientRecipeDao();
		List<IngredientRecipe> ingredientsRecipe = irDao.findIngredientsRecipe(recipe);
		PantryDao pnDao = new PantryDao();
		List<Pantry> pantries = pnDao.findUserPantry(user);

		Iterator<IngredientRecipe> recipeIT = ingredientsRecipe.iterator();
		Iterator<Pantry> pantryIT = null;
		while (resp && recipeIT.hasNext()) {
			IngredientRecipe ingr = recipeIT.next();
			int ingrID = ingr.getIngredientID();
			int ingrQuantity = ingr.getQuantity() * batchSize;
			pantryIT = pantries.iterator();
			while (resp && pantryIT.hasNext()) {
				Pantry pant = pantryIT.next();
				if (ingrID == pant.getIngredientID()) {
					int availability = pant.getAvailability();
					if (ingrQuantity <= availability) {
						resp = true;
					} else {
						resp = false;
					}
				}
			}

		}
		return resp;
	}

	private List<Pantry> newPantry(int user, int recipe) {
		boolean resp = true;
		EquipmentDao eqDao = new EquipmentDao();
		int batchSize = eqDao.findBatchSize(user);
		IngredientRecipeDao irDao = new IngredientRecipeDao();
		List<IngredientRecipe> ingredientsRecipe = irDao.findIngredientsRecipe(recipe);
		PantryDao pnDao = new PantryDao();
		List<Pantry> pantries = pnDao.findUserPantry(user);

		List<Pantry> pantriesNew = new ArrayList<Pantry>();
		Iterator<IngredientRecipe> recipeIT = ingredientsRecipe.iterator();
		Iterator<Pantry> pantryIT = null;
		while (resp && recipeIT.hasNext()) {
			IngredientRecipe ingr = recipeIT.next();
			int ingrID = ingr.getIngredientID();
			int ingrQuantity = ingr.getQuantity() * batchSize;
			pantryIT = pantries.iterator();
			while (resp && pantryIT.hasNext()) {
				Pantry pant = pantryIT.next();
				if (ingrID == pant.getIngredientID()) {
					int availability = pant.getAvailability();
					if (ingrQuantity <= availability) {
						availability -= ingrQuantity;
						Pantry pantry = new Pantry(pant.getUserID(), pant.getIngredientID(), pant.getIngredientName(),
								availability, pant.getMeasure());
						pantriesNew.add(pantry);
						resp = true;
					} else {
						resp = false;
					}
				}
			}

		}
		if (resp) {
			return pantriesNew;
		} else {
			return null;

		}
	}

}
