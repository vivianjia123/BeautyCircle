package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

public class SearchCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {

		forward("/search.jsp");
	}

}
