package controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import model.equipment.EquipmentDao;
import model.pantry.PantryDao;
//import model.equipment.EquipmentDao;
//import model.pantry.PantryDao;
import model.user.User;
import model.user.UserDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recipeEdit.jsp");
			request.setAttribute("page", "recipe_edit");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String date = request.getParameter("dateOfBirth");
		String password = request.getParameter("new_password");
		String checkPassword = request.getParameter("confirm_password");
		String email = request.getParameter("email");
		UserDao userDao = new UserDao();

		if (password.equals(checkPassword) && userDao.usableEmail(email)) {

			SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
			byte[] passwordToHash = digestSHA3.digest(password.getBytes());
			String hash = Hex.toHexString(passwordToHash);
			Date dateOfBirth;
			User user;

			try {

				dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				user = new User(name, surname, dateOfBirth, email, hash);
				userDao.createUser(user);

				User registeredUser = userDao.selectUserByEmail(email);

				int registeredUserId = registeredUser.getId();

				EquipmentDao equipDao = new EquipmentDao();
				equipDao.createEquipment(registeredUserId);

				PantryDao pantryDao = new PantryDao();
				pantryDao.createPantry(registeredUserId);

			} catch (ParseException e) {
				logger.log(Level.SEVERE, "Parser error", e);
			}

			try {
				HttpSession session = request.getSession(true);
				session.setAttribute("alertMessage", "Iscrizione avvenuta con successo");
				session.setAttribute("alertType", "success");
				response.sendRedirect("./home");
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Servlet error", e);
			}

		} else {
			try {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
				request.setAttribute("page", "register");
				request.setAttribute("errorEmail", true);
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				logger.log(Level.SEVERE, "Servlet error", e);
			}
		}

	}

}