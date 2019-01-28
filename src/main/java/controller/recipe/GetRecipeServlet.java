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

import model.recipe.Recipe;
import model.recipe.RecipeDao;
import model.user.User;

@WebServlet("/ricette")
public class GetRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			List<Recipe> recipes = null;
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				RecipeDao recipeDao = new RecipeDao();
				recipes = recipeDao.findAllRecipes(user.getId());
			} else {
				RecipeDao recipeDao = new RecipeDao();
				recipes = recipeDao.findAllRecipes();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeList.jsp");
			request.setAttribute("recipes", recipes);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
