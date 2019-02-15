package controller.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import model.user.User;
import model.user.UserDao;

@WebServlet("/account")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger("MyLog");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				UserDao userDao = new UserDao();
				User getUser = userDao.selectUserById(userID);

				request.setAttribute("user", getUser);
				request.setAttribute("page", "account");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/account.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServletException | IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int userID = user.getUserID();
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				String date = request.getParameter("dateOfBirth");
				String oldPassword = request.getParameter("old_password");
				String password = request.getParameter("new_password");
				String checkPassword = request.getParameter("confirm_password");
				String email = request.getParameter("email");
				UserDao userDao = new UserDao();

				SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
				byte[] passwordToHash = digestSHA3.digest(oldPassword.getBytes());
				String hash = Hex.toHexString(passwordToHash);

				if (user.getHash().equals(hash)) {
					if (password.equals(checkPassword)
							&& (userDao.usableEmail(email) || email.equals(user.getEmail()))) {

						digestSHA3 = new SHA3.Digest512();
						passwordToHash = digestSHA3.digest(password.getBytes());
						hash = Hex.toHexString(passwordToHash);
						Date dateOfBirth;
						User updateUser;

						dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(date);
						updateUser = new User(-1, name, surname, dateOfBirth, email, hash);

						userDao.updateUser(userID, updateUser);
						session.setAttribute("alertMessage", "Dati anagrafici modificati con successo");
						session.setAttribute("alertType", "success");
						response.sendRedirect("./account");

					} else {

						session.setAttribute("alertMessage", "Email o password non corretti");
						session.setAttribute("alertType", "error");
						response.sendRedirect("./account");
					}
				} else {

					session.setAttribute("alertMessage", "Password attuale errata");
					session.setAttribute("alertType", "error");
					response.sendRedirect("./account");
				}
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Servlet error", e);
		} catch (ParseException e) {
			logger.log(Level.SEVERE, "Parser error", e);
		}

	}
}
