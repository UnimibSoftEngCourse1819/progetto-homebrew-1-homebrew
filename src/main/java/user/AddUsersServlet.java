package user;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

@WebServlet("/AddUser")
public class AddUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String date = request.getParameter("dateOfBirth");
		System.out.println(date);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] passwordToHash = digestSHA3.digest(password.getBytes());
		String hash = Hex.toHexString(passwordToHash);
		Date dateOfBirth;
		User user;
		try {
			dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			user = new User(name, surname, dateOfBirth, email, hash, "brewer");
			UserDao adder = new UserDao();
			adder.createUser(user);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
			dispatcher.forward(request, response);



		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}