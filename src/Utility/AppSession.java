package utility;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import domain.User;

public class AppSession {

	public static final String ADMINISTRATOR = "Administrator";
	public static final String REGISTERED_USER = "Registered User";

	public static final String LOCK_REMOVER = "Lock Remover";
	public static final String APP_SESSION = "App Session";

	public static final Boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

	public static boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public static void init(User user, String attribute) {
		SecurityUtils.getSubject().getSession().setAttribute(attribute, user);
		SecurityUtils.getSubject().getSession().setTimeout(900000);
	}

	public static User getUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(REGISTERED_USER);
	}

	public static User getAdminUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(ADMINISTRATOR);
	}

	public static Session getSessionLock() {
		return (Session) SecurityUtils.getSubject().getSession().getAttribute(LOCK_REMOVER);
	}

	public static String getCurrentSession() {
		return (String) SecurityUtils.getSubject().getSession().getAttribute(APP_SESSION);
	}

	public static void setLockRemover(LockRemover lockRemover, String attribute) {
		SecurityUtils.getSubject().getSession().setAttribute(attribute, lockRemover);
	}

	public static void setSession(Serializable serializable, String attribute) {
		SecurityUtils.getSubject().getSession().setAttribute(attribute, serializable);
	}

}
