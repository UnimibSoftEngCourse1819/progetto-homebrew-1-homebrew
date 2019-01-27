package recipe;
/*
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

@WebServlet("/GetRecipes")
public class GetRecipes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");    

    public RecipeController() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			RecipeDao recipeDao = new RecipeDao();
			List<Recipe> recipes = recipeDao.findAllRecipes();
			request.setAttribute("recipes", recipes);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/recipe/GlobalRecipe.jsp");
			dispatcher.forward(request, response);
    	}catch(ServletException | IOException e){
			logger.log(Level.SEVERE, "doGet Servlet error", e);
    	}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
*/