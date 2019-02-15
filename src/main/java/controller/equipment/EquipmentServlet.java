package controller.equipment;

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

import model.equipment.Equipment;
import model.equipment.EquipmentDao;
import model.equipment.ToolEquipment;
import model.equipment.ToolEquipmentDao;
import model.tool.Tool;
import model.tool.ToolDao;
import model.user.User;

@WebServlet("/equipment")
public class EquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				ToolEquipmentDao equipmentDao = new ToolEquipmentDao();
				List<Tool> equipment = equipmentDao.userToolEquipment(userID);
				
				ToolDao toolDao = new ToolDao();
				List<Tool> tools = toolDao.findAllTool();

				request.setAttribute("tools", tools);

				request.setAttribute("equipment", equipment);
				request.setAttribute("page", "equipment");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("./");
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			int userID = user.getUserID();
			EquipmentDao equipmentDao = new EquipmentDao();
			Equipment equipment = equipmentDao.findEquipmentByUser(userID);
			int equipmentID = equipment.getEquipmentID();
			ArrayList<ToolEquipment> toolEquipments = new ArrayList<>();

			ToolDao toolDao = new ToolDao();
			List<Tool> tools = toolDao.findAllTool();
			Iterator<Tool> iterator = tools.iterator();
			int capacity = 0;
			while (iterator.hasNext()) {
				Tool tool = iterator.next();
				String id = tool.getToolID() + "";
				capacity = 0;
				if (request.getParameter(id) != null && !request.getParameter(id).equals("")) {
					try {
						capacity = Integer.parseInt((String) request.getParameter(id));
					} catch (NumberFormatException e) {
						logger.log(Level.SEVERE, "Parser error", e);
					}
				}
				if (capacity > 0) {
					ToolEquipment toolEquipment = new ToolEquipment(equipmentID, tool.getToolID(), capacity);
					toolEquipments.add(toolEquipment);
				}
			}

			ToolEquipmentDao toolEquipmentDao = new ToolEquipmentDao();
			int update = toolEquipmentDao.updateToolEquipment(toolEquipments);
			int batchSize = toolEquipmentDao.getBatchSize(equipmentID);
			equipment = new Equipment(equipmentID, userID, batchSize);
			equipmentDao.updateEquipment(equipment);
			if (update > 0) {
				session.setAttribute("alertMessage", "Equipaggiamento modificato con successo");
				session.setAttribute("alertType", "success");
			} else {
				session.setAttribute("alertMessage", "Equipaggiamento non modificato");
				session.setAttribute("alertType", "error");
			}
		}
		try {
			response.sendRedirect("./equipment");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
