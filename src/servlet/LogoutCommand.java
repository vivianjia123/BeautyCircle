package servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class LogoutCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		SecurityUtils.getSecurityManager().logout(currentUser);
		forward("/logout.jsp");
	}

}
