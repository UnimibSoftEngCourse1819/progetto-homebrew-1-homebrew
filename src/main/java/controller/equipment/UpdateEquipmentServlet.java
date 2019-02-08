package controller.equipment;

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

import model.equipment.Equipment;
import model.equipment.EquipmentDao;
import model.equipment.ToolEquipment;
import model.equipment.ToolEquipmentDao;
import model.user.User;

@WebServlet("/updateEquipment")
public class UpdateEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				EquipmentDao equipmentDao = new EquipmentDao();
				Equipment equipment = equipmentDao.findEquipmentByUser(userID);
				int equipmentID = equipment.getEquipmentID();
				ArrayList<ToolEquipment> toolEquipments = new ArrayList<>();
				ToolEquipment toolEquipment = null;
				for (int i = 10000001; i <= 10000011; i++) {
					if (request.getParameter(i + "") != null && !request.getParameter(i + "").equals("")) {
						int capacity = Integer.parseInt((String) request.getParameter(i + ""));
						if (capacity > 0) {
							toolEquipment = new ToolEquipment(equipmentID, i , capacity );
						}
						toolEquipments.add(toolEquipment);
					}
				}
				
				
				
				if(toolEquipments.size() > 0) {
					ToolEquipmentDao toolEquipmentDao = new ToolEquipmentDao();
					int update = toolEquipmentDao.updateToolEquipment(toolEquipments);
					int batchSize = toolEquipmentDao.getBatchSize(equipmentID);
					equipment = new Equipment(equipmentID, userID, batchSize);
					equipmentDao.updateEquipment(equipment);
					if (update > 0) {
						session.setAttribute("alertMessage", "Equipaggiamento modificato con successo");
						session.setAttribute("alertType", "success");
						response.sendRedirect("./equipment");
					} else {
						session.setAttribute("alertMessage", "Equipaggiamento non modificato");
						session.setAttribute("alertType", "error");
						response.sendRedirect("./equipment");
					}
				} else {
					session.setAttribute("alertMessage", "Equipaggiamento non modificato");
					session.setAttribute("alertType", "error");
					response.sendRedirect("./equipment");
				}
				
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
