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
import model.equipment.ToolEquipment;
import model.equipment.ToolEquipmentDao;
import model.user.User;

@WebServlet("/updateEquipment")
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
				EquipmentDao equipmentDao = new EquipmentDao();
				Equipment equipment = equipmentDao.selectEquipmentByUser(userID);
				int equipmentID = equipment.getEquipmentID();
				ArrayList<ToolEquipment> toolEquipment = new ArrayList<>();
				
				if (request.getParameter("boilingCauldron") != null) {
					int boilingCauldron = Integer.parseInt((String) request.getParameter("boilingCauldron"));
					ToolEquipment equipmentBoilingCauldron = new ToolEquipment(equipmentID, 1 ,boilingCauldron );
					toolEquipment.add(equipmentBoilingCauldron);
				}
				
				if (request.getParameter("mashingCauldron") != null) {
					int mashingCauldron = Integer.parseInt((String) request.getParameter("mashingCauldron"));
					ToolEquipment equipmentMashingCauldron = new ToolEquipment(equipmentID, 2 ,mashingCauldron );
					toolEquipment.add(equipmentMashingCauldron);
				}
				
				if (request.getParameter("hotLiquidsCauldron") != null) {
					int hotLiquidsCauldron = Integer.parseInt((String) request.getParameter("hotLiquidsCauldron"));
					ToolEquipment equipmentHotLiquidsCauldron = new ToolEquipment(equipmentID, 3 ,hotLiquidsCauldron );
					toolEquipment.add(equipmentHotLiquidsCauldron);
				}
				
				if (request.getParameter("kettle") != null) {
					int kettle = Integer.parseInt((String) request.getParameter("kettle"));
					ToolEquipment equipmentKettle = new ToolEquipment(equipmentID, 4 ,kettle );
					toolEquipment.add(equipmentKettle);
				}
				
				if (request.getParameter("fermenter") != null) {
					int fermenter = Integer.parseInt((String) request.getParameter("fermenter"));
					ToolEquipment equipmentFermenter = new ToolEquipment(equipmentID, 5 ,fermenter );
					toolEquipment.add(equipmentFermenter);
				}
				
				if (request.getParameter("filter") != null) {
					int filter = Integer.parseInt((String) request.getParameter("filter"));
					ToolEquipment equipmentFilter = new ToolEquipment(equipmentID, 6 ,filter );
					toolEquipment.add(equipmentFilter);
				}
				
				if (request.getParameter("weightScale") != null) {
					int weightScale = Integer.parseInt((String) request.getParameter("weightScale"));
					ToolEquipment equipmentWeightScale = new ToolEquipment(equipmentID, 7 ,weightScale );
					toolEquipment.add(equipmentWeightScale);
				}
				
				if (request.getParameter("thermometers") != null) {
					int thermometers = Integer.parseInt((String) request.getParameter("thermometers"));
					ToolEquipment equipmentThermometers = new ToolEquipment(equipmentID, 8 ,thermometers );
					toolEquipment.add(equipmentThermometers);
				}
				
				if (request.getParameter("densimetro") != null) {
					int densimetro = Integer.parseInt((String) request.getParameter("densimetro"));
					ToolEquipment equipmentDensimetro = new ToolEquipment(equipmentID, 9 ,densimetro );
					toolEquipment.add(equipmentDensimetro);
				}
				
				if (request.getParameter("ladles") != null) {
					int ladles = Integer.parseInt((String) request.getParameter("ladles"));
					ToolEquipment equipmentLadles = new ToolEquipment(equipmentID, 10 ,ladles );
					toolEquipment.add(equipmentLadles);
				}
				
				if (request.getParameter("tube") != null) {
					int tube = Integer.parseInt((String) request.getParameter("tube"));
					ToolEquipment equipmentTube = new ToolEquipment(equipmentID, 11 ,tube );
					toolEquipment.add(equipmentTube);
				}
				
				if(toolEquipment.size() > 0) {
					ToolEquipmentDao toolEquipmentDao = new ToolEquipmentDao();
					int update = toolEquipmentDao.updateToolEquipment(toolEquipment);
					int batchSize = toolEquipmentDao.getBatchSize(equipmentID);
					equipment = new Equipment(equipmentID, userID, batchSize);
					equipmentDao.updateEquipment(equipment);
					if (update > 0) {
						request.setAttribute("success", true);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("failure", true);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					request.setAttribute("failure", true);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
					dispatcher.forward(request, response);
				}
				
			}

		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}
}
