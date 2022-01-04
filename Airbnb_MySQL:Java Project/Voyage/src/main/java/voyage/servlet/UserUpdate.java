package voyage.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.dal.UsersDao;
import voyage.model.Users;

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserName.");
		} else {
			try {
				Users user = usersDao.getUsersByUserName(userName);
				if (user == null) {
					messages.put("success", "UserName does not exist.");
				} else {
					String oldPassword = req.getParameter("oldpassword");
					if (oldPassword == null || oldPassword.trim().isEmpty()) {
						messages.put("success", "Please enter a password.");
					} else if (!oldPassword.equals(user.getPassword())) {
						messages.put("success", "Username and password do not match.");

					} else {
						String newPassword = req.getParameter("newpassword");
						if (newPassword == null || newPassword.trim().isEmpty()) {
							messages.put("success", "Please enter a valid new Password.");
						} else {
							user = usersDao.updatePassword(user, newPassword);
							messages.put("success", "Successfully updated password.");
						}
					}

				}
				req.setAttribute("User", user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
}
