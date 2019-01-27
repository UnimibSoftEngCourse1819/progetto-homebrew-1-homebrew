package recipe;

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


@WebServlet("/GetRecipe")
public class GetRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(false);
			List<Recipe> recipes = null;
			if (session != null) {
				int userID = Integer.parseInt((String) session.getAttribute("userID"));
				//ottiene anche le ricette private dell'utente
				RecipeDao recipeDao = new RecipeDao();
				recipes = recipeDao.findAllRecipes(userID);
				request.setAttribute("recipes", recipes);
			} else {
				
				RecipeDao recipeDao = new RecipeDao();
				recipes = recipeDao.findAllRecipes();
				request.setAttribute("recipes", recipes);
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/recipe/GlobalRecipe.jsp");
			request.setAttribute("recipes", recipes);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
