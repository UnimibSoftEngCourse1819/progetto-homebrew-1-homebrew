package controller.user;
/*
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog"); 
	
    public UserController() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserDao userDao = UserDao.getInstance();
		
			String action = request.getParameter("action");
			String idStr = request.getParameter("id");
			 if("delete".equals(action) && idStr != null) {
				int id = Integer.parseInt(idStr);
				userDao.deleteUser(id);				
			} 
			 List<Brewer> brewers = userDao.findAllUsers();
			 request.setAttribute("brewers", brewers);
			 
			 String nextJSP = "/usersList.jsp";
			 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			 dispatcher.forward(request, response);
		}catch(ServletException | IOException e){
			logger.log(Level.SEVERE, "doGet Servlet error", e);
		}
			
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserDao userDao = UserDao.getInstance();
			
			String action = request.getParameter("action");
			String idStr = request.getParameter("id");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateOfBirth = dateformat.parse(request.getParameter("dateOfBirth"));
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			String kind = request.getParameter("kind");
			Brewer brewer = null;
			
			if("update".equals(action) && idStr != null) {
				int id = Integer.parseInt(idStr);
				brewer = new Brewer(id, name, surname, dateOfBirth ,mail, password, kind);
				userDao.updateUser(id, brewer);				
			} else if("create".equals(action)){
				brewer = new Brewer(-1, name, surname, dateOfBirth ,mail, password, "Brewer");
				userDao.createUser(brewer);
			}
			doGet(request, response);
		}catch(ServletException | IOException e){
			logger.log(Level.SEVERE, "doPost Servlet error", e);
		} catch (ParseException e) {
			logger.log(Level.SEVERE, "Parsing error Date", e);
		}
	}

}
*/
