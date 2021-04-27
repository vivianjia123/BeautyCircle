package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import utility.AppSession;
import utility.ExclusiveWriteLockManager;

public class UserProfilesCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (AppSession.isAuthenticated() && AppSession.getAdminUser() != null
				&& AppSession.hasRole(AppSession.ADMINISTRATOR)) {
			if (request.getParameter("admin") != null || request.getParameter("registeruser") != null) {
				FrontCommand.startNewBusinessTransaction();
				Long orderId = Long.parseLong(request.getParameter("userid"));
				ExclusiveWriteLockManager lockManager = ExclusiveWriteLockManager.getInstance();
				try {
					if (lockManager.acquireLock(orderId, AppSession.getCurrentSession().toString())) {
						forward("/users.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				forward("/users.jsp");
			}

		}

	}

}
