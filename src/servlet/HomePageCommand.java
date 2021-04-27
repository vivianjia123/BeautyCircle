package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import utility.AppSession;

public class HomePageCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMINISTRATOR)) {
			forward("/staff-profile.jsp");
		} else {
			forward("/home.jsp");
		}

	}

}
