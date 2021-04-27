package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet("/FrontServlet")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FrontCommand command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.process();

	}

	@SuppressWarnings("unchecked")
	private FrontCommand getCommand(HttpServletRequest request) {
		try {
			return (FrontCommand) getCommandClass(request).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private Class getCommandClass(HttpServletRequest request) throws ClassNotFoundException {
		Class result;
		String className;
		if (request.getParameter("command") == null) {
			className = getServletConfig().getInitParameter("command");
		} else {
			className = request.getParameter("command");
		}
		final String commandClassName = "servlet." + className + "Command";
		System.out.println(request.getParameter("command"));

		try {
			result = Class.forName(commandClassName);
		} catch (ClassNotFoundException e) {
			result = Class.forName("servlet.ErrorCommand");
		}

		if (SecurityUtils.getSubject().getSession().getTimeout() == 0) {
			result = Class.forName("servlet.LogoutCommand");
		}
		return result;

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
