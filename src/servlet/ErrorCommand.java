package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

public class ErrorCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/error.jsp");
	}

}
