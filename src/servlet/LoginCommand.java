package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

public class LoginCommand extends FrontCommand {
	@Override
	public void process() throws ServletException, IOException {
		forward("/login.jsp");
	}
}
