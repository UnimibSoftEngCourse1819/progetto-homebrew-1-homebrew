package controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import dao.UserDao;
import model.user.Brewer;

public class UserControllerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserControllerUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();
		
		String action = request.getParameter("action");
		String idStr = request.getParameter("id");
		Brewer brewer = null;
		try {
			if("update".equals(action)) {
				if(idStr != null) {
					int id = Integer.parseInt(idStr);
					brewer = userDao.selectUserById(id);
					request.setAttribute("brewer", brewer);
					
					String nextJSP = "/userUpdate.jsp";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
					dispatcher.include(request, response);
				}
			}
		}catch(ServletException | IOException e){
			System.out.println("doGet Servlet error");
		}
	}		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
