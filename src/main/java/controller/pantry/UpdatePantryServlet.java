package controller.pantry;

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

import model.pantry.Pantry;
import model.pantry.PantryDao;
import model.user.User;

@WebServlet("/updatePantry")
public class UpdatePantryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getId();
				ArrayList<Pantry> pantry = new ArrayList<>();
				
				if (request.getParameter("malt") != null) {
					int malt = Integer.parseInt((String) request.getParameter("malt"));
					Pantry pantryMalt = new Pantry(userID, 1 ,malt );
					pantry.add(pantryMalt);
				}
				
				if (request.getParameter("sugar") != null) {
					int sugar = Integer.parseInt((String) request.getParameter("sugar"));
					Pantry pantrySugar = new Pantry(userID, 2 ,sugar );
					pantry.add(pantrySugar);
				}
				
				if (request.getParameter("yeast") != null) {
					int yeast = Integer.parseInt((String) request.getParameter("yeast"));
					Pantry pantryYeast = new Pantry(userID, 3 ,yeast );
					pantry.add(pantryYeast);
				}
				
				if (request.getParameter("additives") != null) {
					int additives = Integer.parseInt((String) request.getParameter("additives"));
					Pantry pantryAdditives = new Pantry(userID, 4 ,additives );
					pantry.add(pantryAdditives);
				}
				
				if (request.getParameter("hop") != null) {
					int hop = Integer.parseInt((String) request.getParameter("hop"));
					Pantry pantryHop = new Pantry(userID, 5 ,hop );
					pantry.add(pantryHop);
				}
				
				if (request.getParameter("water") != null) {
					int water = Integer.parseInt((String) request.getParameter("water"));
					Pantry pantryWater = new Pantry(userID, 6 ,water );
					pantry.add(pantryWater);
				}

				if(pantry.size() > 0) {
					PantryDao pantryDao = new PantryDao();
					int update = pantryDao.updatePantry(pantry);

					if (update > 0) {
						response.sendRedirect("./pantry");
					} else {
						response.sendRedirect("./pantry");
					}
				} else {
					response.sendRedirect("./pantry");
				}
				
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
