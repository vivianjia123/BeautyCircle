package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

public class RegisterCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/register.jsp");
	}

}
