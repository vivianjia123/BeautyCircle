package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import domain.Administrator;
import domain.RegisteredUser;
import domain.User;
import service.UserService;
import utility.AppSession;
import utility.ExclusiveWriteLockManager;

public class ProfileCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (AppSession.isAuthenticated()) {
			if (AppSession.hasRole(AppSession.REGISTERED_USER)) {
				forward("/profile.jsp");
			} else if (AppSession.hasRole(AppSession.ADMINISTRATOR)) {
				if (request.getParameter("submit") != null) {
					FrontCommand.startNewBusinessTransaction();
					Long adminId = AppSession.getAdminUser().getId();
					ExclusiveWriteLockManager lockManager = ExclusiveWriteLockManager.getInstance();
					try {
						if (lockManager.acquireLock(adminId, AppSession.getCurrentSession().toString())) {
							forward("/staff-profile.jsp");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					forward("/staff-profile.jsp");
				}

			}

		} else {
			String username = request.getParameter("userName");
			String password = request.getParameter("passWord");
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject currentUser = SecurityUtils.getSubject();
			UserService userService = new UserService();
			User foundUser = userService.findUserByuserName(username);
			try {
				currentUser.login(token);
				if (foundUser.getUserType().equals(RegisteredUser.class.toString())) {
					AppSession.init(foundUser, AppSession.REGISTERED_USER);
					forward("/profile.jsp");
				} else if (foundUser.getUserType().equals(Administrator.class.toString())) {
					AppSession.init(foundUser, AppSession.ADMINISTRATOR);
					forward("/staff-profile.jsp");
				}

			} catch (UnknownAccountException | IncorrectCredentialsException e) {
				forward("/login-error.jsp");
			}

		}

	}

}
