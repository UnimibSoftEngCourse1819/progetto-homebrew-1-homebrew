package user;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;


public class UserControllerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog"); 
	
    public UserControllerUpdate() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserDao userDao = UserDao.getInstance();
		
			String action = request.getParameter("action");
			String idStr = request.getParameter("id");
			User brewer = null;
			if("update".equals(action) && idStr != null) {
					int id = Integer.parseInt(idStr);
					brewer = userDao.selectUserById(id);
					request.setAttribute("brewer", brewer);
					
					String nextJSP = "/userUpdate.jsp";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
					dispatcher.include(request, response);
			}
		}catch(ServletException | IOException e){
			logger.log(Level.SEVERE, "doGet Servlet error", e);
		}
	}		
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		}catch(ServletException | IOException e){
			logger.log(Level.SEVERE, "doPost Servlet error", e);
		}
	}

}
