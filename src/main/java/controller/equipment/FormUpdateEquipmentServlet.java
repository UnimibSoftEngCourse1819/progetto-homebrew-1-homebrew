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

import model.tool.Tool;
import model.tool.ToolDao;

@WebServlet("/formUpdateEquipment")
public class FormUpdateEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				List<Tool> tool = new ArrayList<>();
				ToolDao toolDao = new ToolDao();
				tool = toolDao.findAllTool();

				request.setAttribute("tool", tool);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/equipment.jsp");
				dispatcher.forward(request, response);
				
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}
}
