package controller.equipment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.equipment.Equipment;
import model.equipment.EquipmentDao;
import model.user.User;

@WebServlet("/updateEquipmen")
public class UpdateEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getId();
				ArrayList<Equipment> equipment = new ArrayList<>();
				
				if (request.getParameter("weightScale") != null) {
					int weightScale = Integer.parseInt((String) request.getParameter("weightScale"));
					Equipment equipmentWeightScale = new Equipment(userID, 1 ,weightScale );
					equipment.add(equipmentWeightScale);
				}
				
				if (request.getParameter("cauldron") != null) {
					int cauldron = Integer.parseInt((String) request.getParameter("cauldron"));
					Equipment equipmentCauldron = new Equipment(userID, 2 ,cauldron );
					equipment.add(equipmentCauldron);
				}
				
				if (request.getParameter("fermenter") != null) {
					int fermenter = Integer.parseInt((String) request.getParameter("fermenter"));
					Equipment equipmentFermenter = new Equipment(userID, 3 ,fermenter );
					equipment.add(equipmentFermenter);
				}
				
				if (request.getParameter("ladles") != null) {
					int ladles = Integer.parseInt((String) request.getParameter("ladles"));
					Equipment equipmentLadles = new Equipment(userID, 4 ,ladles );
					equipment.add(equipmentLadles);
				}
				
				if (request.getParameter("thermometers") != null) {
					int thermometers = Integer.parseInt((String) request.getParameter("thermometers"));
					Equipment equipmentThermometers = new Equipment(userID, 5 ,thermometers );
					equipment.add(equipmentThermometers);
				}
				
				if (request.getParameter("tube") != null) {
					int tube = Integer.parseInt((String) request.getParameter("tube"));
					Equipment equipmentTube = new Equipment(userID, 6 ,tube );
					equipment.add(equipmentTube);
				}
				
				if(equipment.size() > 0) {
					EquipmentDao equipmentDao = new EquipmentDao();
					int update = equipmentDao.updateEquipment(equipment);
					if (update > 0) {
						String success ="L'aggiornamento è andato a buon fine";
						request.setAttribute("success", success);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
						dispatcher.forward(request, response);
					} else {
						String failure ="L'aggiornamento non è andato a buon fine";
						request.setAttribute("failure", failure);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					String failure ="L'aggiornamento non è andato a buon fine";
					request.setAttribute("failure", failure);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
					dispatcher.forward(request, response);
				}
				
			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
