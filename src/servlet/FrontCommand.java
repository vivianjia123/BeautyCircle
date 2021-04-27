package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

import utility.AppSession;
import utility.ExclusiveWriteLockManager;
import utility.LockRemover;

public abstract class FrontCommand {
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		this.context = context;
		this.request = request;
		this.response = response;
	}

	abstract public void process() throws ServletException, IOException;

	protected void forward(String target) throws ServletException, IOException {
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}

	protected void redirect(String target) throws ServletException, IOException {
		response.sendRedirect(target);
	}

	public static void startNewBusinessTransaction() {

		if (AppSession.getCurrentSession() != null) {
			try {
				ExclusiveWriteLockManager.getInstance().releaseAllLocks(AppSession.getCurrentSession());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		AppSession.setSession(SecurityUtils.getSubject().getSession().getId(), AppSession.APP_SESSION);
		AppSession.setLockRemover(new LockRemover(SecurityUtils.getSubject().getSession().getId().toString()),
				AppSession.LOCK_REMOVER);

	}

	protected void continueBusinessTransaction() {
		AppSession.setSession(SecurityUtils.getSubject().getSession().getId(), AppSession.APP_SESSION);
	}

}
