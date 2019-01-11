package controller.user;

import java.io.IOException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import model.user.UserAdmin;
import model.user.UserBrewer;

@WebServlet("/homebrew/users")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private ArrayList<UserBrewer> usersB;
	//private ArrayList<UserAdmin> usersA;
	private UserDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// Inizializzo la lista
		//this.usersB = null;
		//this.usersA = new ArrayList<>();
		this.dao = new UserDAO();
		

		this.usersB = dao.findAllUsers();
		//this.usersB = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Controllo che se è presente il parametro 'id' nella query string
		// (es.:http://localhost:8080/es3/users?id=1)
		if (null != request.getParameter("id")) {
			// Se è presente, passo il controllo al metodo showUser che si occuperà di
			// mostrare il profilo utente
			showUser(request, response);
		} else {
			// Utilizzo il request dispatcher per definire a quale risorsa verrà poi
			// inoltrata
			// la richiesta. In questo caso una pagina JSP che visulizzerà la lista utenti
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/usersList.jsp");

			// Imposto un attributo con scope di richiesta: in questo caso sto passando una
			// lista utenti

			
			request.setAttribute("users", this.usersB);

			// Effettuo il forward (inoltro) della richiesta alla risorsa impostata sopra
			// La pagina JSP verrà automaticamente "compilata" in una servlet e la sua
			// risposta verrà inoltrata al client
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recupero i parametri inviato tramite la form
		// N.B.: qui si potrebbe fare la validazione ad esempio verificando il corretto
		// formato email,
		// stringhe non vuote, etc...

		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String type = request.getParameter("email");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth = null;
		try {
			dateOfBirth = formatter.parse(request.getParameter("dateOfBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}


		// Creo il nuovo utente e lo aggiungo alla lista
		UserBrewer user = new UserBrewer(id, firstName, lastName, dateOfBirth, email, password, type);
		this.usersB.add(user);
		// Utilizzo il request dispatcher per definire a quale risorsa verrà poi
		// inoltrata
		// la richiesta. In questo caso una pagina JSP che visualizzerà la lista utenti
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/usersList.jsp");

		// Imposto un attributo con scope di richiesta: in questo caso sto passando
		// la lista utenti aggiornata
		request.setAttribute("users", this.usersB);

		// Effettuo il forward (inoltro) della richiesta alla risorsa impostata sopra
		// La pagina JSP verrà automaticamente "compilata" in una servlet e la sua
		// risposta verrà inoltrata al client
		dispatcher.forward(request, response);
	}

	private void showUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recupero l'id dell'utente dalla query string
		// (es.:http://localhost:8080/es3/users?id=1)
		// e lo trasformo in intero
		// N.B.: tutti i parametri sono sempre stringhe
		int userId = Integer.parseInt(request.getParameter("id"));

		// Il try-catch permette di catturare l'eccezione IndexOutOfBoundsException
		// nel caso in cui l'id richiesto non fosse presente nella lista
		// N.B.: stiamo utilizzando come id dell'utente il suo corrispondente
		// indice nella lista
		try {
			// Recupero l'utente dalla lista
			UserBrewer user = this.usersB.get(userId);

			// Utilizzo il request dispatcher per definire a quale risorsa verrà poi
			// inoltrata
			// la richiesta. In questo caso una pagina JSP che visulizzerà il profilo utente
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/usersList.jsp");

			// Imposto un attributo con scope di richiesta: in questo caso sto passando
			// l'utente
			request.setAttribute("user", user);

			// Effettuo il forward (inoltro) della richiesta alla risorsa impostata sopra
			// La pagina JSP verrà automaticamente "compilata" in una servlet e la sua
			// risposta verrà inoltrata al client
			dispatcher.forward(request, response);
		} catch (IndexOutOfBoundsException e) {
			// Nel caso in cui l'id non esista imposto come codice di stato 404 NOT FOUND
			response.setStatus(404);

			// Utilizzo il request dispatcher per definire a quale risorsa verrà poi
			// inoltrata
			// la richiesta. In questo caso una pagina JSP che visualizzerà un messaggio di
			// errore

			// RequestDispatcher dispatcher =
			// getServletContext().getRequestDispatcher("/views/userNotFound.jsp");

			// Effettuo il forward (inoltro) della richiesta alla risorsa impostata sopra
			// La pagina JSP verrà automaticamente "compilata" in una servlet e la sua
			// risposta verrà inoltrata al client

			// dispatcher.forward(request, response);
		}
	}

}
