package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;

import utility.AppSession;
import utility.ExclusiveWriteLockManager;

public class ProductCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (AppSession.isAuthenticated()) {
			if (AppSession.getUser() != null && AppSession.hasRole(AppSession.REGISTERED_USER)) {
				forward("/product.jsp");
			} else if (AppSession.getAdminUser() != null && AppSession.hasRole(AppSession.ADMINISTRATOR)) {

				if (request.getParameter("submit") != null) {
					FrontCommand.startNewBusinessTransaction();
					Long productId = Long.parseLong(request.getParameter("productid"));
					ExclusiveWriteLockManager lockManager = ExclusiveWriteLockManager.getInstance();
					try {
						if (lockManager.acquireLock(productId, AppSession.getCurrentSession().toString())) {
							forward("/products.jsp");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					forward("/products.jsp");
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
