
import java.util.ArrayList;
import java.util.Iterator;

import controller.user.UserDAO;
import model.user.UserBrewer;

public class Main {
	public static void main(String[] args) throws Exception {
		UserDAO dao = new UserDAO();
		ArrayList<UserBrewer> users = dao.findAllUsers();
		Iterator<UserBrewer> usersIt = users.iterator();
		while (usersIt.hasNext()) {
			System.out.println(usersIt.next());
		}
		
	}

}
