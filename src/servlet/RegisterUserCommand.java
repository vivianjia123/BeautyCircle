package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import domain.RegisteredUser;
import domain.User;
import service.RegisteredUserService;
import service.UserService;
import utility.AppSession;

public class RegisterUserCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		long id = User.universalId();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String shippingAddress = request.getParameter("shippingaddress");
		String address = request.getParameter("address");

		RegisteredUserService registeredUserService = new RegisteredUserService();
		RegisteredUser temporaryUser = new RegisteredUser(id, firstname, lastname, email, address, username, password,
				shippingAddress);
		registeredUserService.createUser(temporaryUser);

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		UserService userService = new UserService();
		User foundUser = userService.findUserByuserName(username);

		try {
			currentUser.login(token);
			AppSession.init(foundUser, AppSession.REGISTERED_USER);

		} catch (UnknownAccountException | IncorrectCredentialsException e) {

		}

		forward("/profile.jsp");
	}

}
