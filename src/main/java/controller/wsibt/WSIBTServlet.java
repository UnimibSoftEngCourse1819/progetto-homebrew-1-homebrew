package controller.wsibt;

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
import model.equipment.EquipmentDao;
import model.recipe.IngredientRecipe;
import model.recipe.IngredientRecipeDao;
import model.recipe.Recipe;
import model.recipe.RecipeDao;
import model.user.User;
import model.user.UserDao;

@WebServlet("/wsibt")
public class WSIBTServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				String section = (String) session.getAttribute("section");
				User user = (User) session.getAttribute("user");
				RecipeDao recipeDao = new RecipeDao();
				EquipmentDao eqDao = new EquipmentDao();
				int batchSize = eqDao.findBatchSize(user.getUserID());
				if (batchSize > 0) {
					Recipe recipe = recipeDao.wsibt(user.getUserID());
					UserDao userDao = new UserDao();
					User userRecipe = userDao.selectUserById(recipe.getUserID());
					IngredientRecipeDao ingredientRecipeDao = new IngredientRecipeDao();
					List<IngredientRecipe> ingredientsRecipe = ingredientRecipeDao
							.findIngredientsRecipe(recipe.getRecipeID());
					BrewDao brewDao = new BrewDao();
					List<Brew> brews = brewDao.findBrewByRecipeID(recipe.getRecipeID());
					if (user.getUserID() == recipe.getUserID()) {
						request.setAttribute("editable", true);
					}
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/wsibt.jsp");
					session.setAttribute("recipe", recipe);
					request.setAttribute("recipe", recipe);
					request.setAttribute("userRecipe", userRecipe);
					request.setAttribute("ingredientsRecipe", ingredientsRecipe);
					request.setAttribute("brews", brews);
					request.setAttribute("page", "wsibt");
					request.setAttribute("section", section);
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/wsibt.jsp");
					session.removeAttribute("recipe");
					request.setAttribute("recipe", null);
					request.setAttribute("page", "wsibt");
					request.setAttribute("section", section);
					dispatcher.forward(request, response);
				}
			} else {
				response.sendRedirect("./recipe");
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.sendRedirect("./");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}
}
