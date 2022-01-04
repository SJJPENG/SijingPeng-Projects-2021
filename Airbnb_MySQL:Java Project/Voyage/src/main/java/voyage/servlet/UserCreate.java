package voyage.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import voyage.dal.UsersDao;
import voyage.model.Users;

@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else {
			try {
				Users user = usersDao.getUsersByUserName(userName);
				if (user != null) {
					messages.put("success", "Username " + userName + " already exists.");
				} else {
					// Create the Users.
					String email = req.getParameter("email");
					String password = req.getParameter("password");
					String firstName = req.getParameter("firstname");
					String lastName = req.getParameter("lastname");
					if (email == null || email.trim().isEmpty()) {
						messages.put("success", "Please enter an email.");
					} else if (password == null || password.trim().isEmpty()) {
						messages.put("success", "Please enter a password.");
					}else {
						user = new Users(userName, email, password, firstName, lastName);
						user = usersDao.create(user);
						messages.put("success", "Successfully created " + userName);
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

		}

		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
}
