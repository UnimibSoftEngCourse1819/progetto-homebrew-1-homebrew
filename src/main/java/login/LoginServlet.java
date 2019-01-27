package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	//private final String username = "admin";
    //private final String password = "password";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    	
    	Login login = new Login();

        // get request parameters for username and password
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        
        String rights = login.getRights(username, password);

        if (!rights.equals("")) {
            //get the old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession session = request.getSession(true);
            
            
            //setting session to expiry in 5 mins
            session.setMaxInactiveInterval(5*60);
            session.setAttribute("rights", rights);
            session.setAttribute("user", username);
            String name  = login.getName();

            
            //Cookie message = new Cookie("message", "Welcome");
            //Cookie rightsCookie = new Cookie("rights", rights);

            //response.addCookie(message);
            //response.addCookie(rightsCookie);
            login.close();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home/homepage.jsp");
			request.setAttribute("userID", name);
			dispatcher.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(request, response);
        }
    }
} 