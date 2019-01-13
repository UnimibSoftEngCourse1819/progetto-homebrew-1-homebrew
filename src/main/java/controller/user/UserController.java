package controller.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.user.Brewer;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserDao userDao = UserDao.getInstance();
		
			String action = request.getParameter("action");
			String idStr = request.getParameter("id");
			 if("delete".equals(action)) {
				if(idStr != null) {
					int id = Integer.parseInt(idStr);
					userDao.deleteUser(id);				
				}
			} 
			 ArrayList<Brewer> brewers = userDao.findAllUsers();
			 request.setAttribute("brewers", brewers);
			 
			 String nextJSP = "/usersList.jsp";
			 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			 dispatcher.forward(request, response);
		}catch(ServletException | IOException e){
			System.out.println("doGet Servlet error");
		}
			
	}

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
			System.out.println("doPost Servlet error");
		} catch (ParseException e) {
			System.out.println("Parsing error Date");
		}
	}

}
