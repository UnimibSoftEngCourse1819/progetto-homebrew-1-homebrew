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


// IN MENU -> SET SECTION IN SESSION
@WebServlet("/my_recipes")
public class RecipeUserListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				session.setAttribute("section", "personal");

				User user = (User) session.getAttribute("user");	
				RecipeDao recipeDao = new RecipeDao();
				List <Recipe> recipes = recipeDao.findRecipesUser(user.getId());

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeUserList.jsp");
				request.setAttribute("recipes", recipes);
				request.setAttribute("page", "my_recipes");
				dispatcher.forward(request, response);
				session.removeAttribute("alertMessage");
				session.removeAttribute("alertType");

			} else {
				response.sendRedirect("/homebrew/home");

			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

}
