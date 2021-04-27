package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import domain.Administrator;
import domain.RegisteredUser;
import domain.User;
import service.AdministratorService;
import service.RegisteredUserService;

public class NewUsersCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (request.getParameter("register") != null) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String firstname = request.getParameter("fname");
			String lastname = request.getParameter("lname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");

			if (request.getParameter("usertype").equals("RegisteredUser")) {
				String shippingAddress = request.getParameter("shippingaddress");
				RegisteredUserService registeredUserService = new RegisteredUserService();
				RegisteredUser temporaryUser = new RegisteredUser(User.universalId(), firstname, lastname, email,
						address, username, password, shippingAddress);
				User.increaseUniversalId();
				registeredUserService.createUser(temporaryUser);
			} else if (request.getParameter("usertype").equals("Administrator")) {
				Administrator newAdmin = new Administrator(User.universalId(), firstname, lastname, email, address,
						username, password);
				User.increaseUniversalId();
				AdministratorService administratorService = new AdministratorService();
				administratorService.createAdmin(newAdmin);
			}
		}
		forward("/newusers.jsp");
	}

}
