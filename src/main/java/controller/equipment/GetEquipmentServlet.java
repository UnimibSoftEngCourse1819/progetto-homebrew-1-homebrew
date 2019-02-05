package controller.equipment;

import java.io.IOException;
import java.util.ArrayList;
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

import model.equipment.ToolEquipmentDao;
import model.tool.Tool;
import model.user.User;

@WebServlet("/getEquipment")
public class GetEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getId();
				List<Tool> equipment = new ArrayList<>();
				ToolEquipmentDao equipmentDao = new ToolEquipmentDao();
				equipment = equipmentDao.userEquipment(userID);
					
				request.setAttribute("equipment", equipment);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
				dispatcher.forward(request, response);
					
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
