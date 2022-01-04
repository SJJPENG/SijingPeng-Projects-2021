package voyage.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.dal.UsersDao;
import voyage.model.Users;

@WebServlet("/findusers")
public class FindUsers extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Users> users = new ArrayList<>();

		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid username.");
		} else {
			try {
				Users user = usersDao.getUsersByUserName(userName);

				if (user == null) {
					messages.put("success", "UserName does not exist.");
				} else {
					String password = req.getParameter("password");
					if (password == null || password.trim().isEmpty()) {
						messages.put("success", "Please enter a password.");
					} else if (!user.getPassword().equals(password)) {
						messages.put("success", "Username and password do not match.");
					} else {
						users.add(user);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("users", users);
		req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}
}
