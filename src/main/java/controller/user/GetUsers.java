package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * nome: /nomeServlet
 * viene chiamata da una pagina tramite il riferimento ./nomeServlet
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			String rights = (String) session.getAttribute("rights");
			if (rights.equals("admin")) {
				String user = (String) session.getAttribute("user");

				ArrayList<String> users = new ArrayList<String>();
				users.add(user);
				users.add(rights);

				/*
				 * In getRequestDispatcher() inserire il path del file jsp a cui inviare i dati
				 * il path comincia SEMPRE con "/....."
				 * 
				 * setAttribute() aggiunge l'oggetto da inviare tramite ("nome", oggetto) nel
				 * jsp chiamare l'oggetto tramite "nome" ed accedere agli attributi con
				 * ${item.attributo} dove item è un elemento del ciclo foreach su "nome"
				 * 
				 * dispatcher.forward(request, response) formula fissa per inviare i dati
				 */
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListUser.jsp");
				request.setAttribute("users", users);
				dispatcher.forward(request, response);

			}
		}


	}

}