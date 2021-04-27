package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;

import utility.AppSession;
import utility.ExclusiveWriteLockManager;

public class OrderCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (AppSession.isAuthenticated()) {
			if (AppSession.getUser() != null && AppSession.hasRole(AppSession.REGISTERED_USER)) {
				forward("/order.jsp");
			} else if (AppSession.getAdminUser() != null && AppSession.hasRole(AppSession.ADMINISTRATOR)) {
				if (request.getParameter("submit") != null) {
					FrontCommand.startNewBusinessTransaction();
					Long orderId = Long.parseLong(request.getParameter("submit"));
					ExclusiveWriteLockManager lockManager = ExclusiveWriteLockManager.getInstance();
					try {
						if (lockManager.acquireLock(orderId, AppSession.getCurrentSession().toString())) {
							forward("/orders.jsp");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					forward("/orders.jsp");
				}
			}

		} else {
			if (AppSession.getUser() == null) {
				SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
			}
			forward("/error.jsp");
		}
	}

}
