package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;

import utility.AppSession;

public class CartCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
			forward("/cart.jsp");
		} else {
			if (AppSession.getUser() == null) {
				SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
			}
			forward("/error.jsp");
		}

	}

}
