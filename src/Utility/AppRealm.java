package utility;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import domain.Administrator;
import domain.RegisteredUser;
import domain.User;
import service.UserService;

public class AppRealm extends JdbcRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();
		UserService userService = new UserService();
		final User user = new User(username);
		final User foundUser = userService.findUserByuserName(user);

		if (foundUser == null) {
			return null;
		}

		return new SimpleAuthenticationInfo(foundUser.getId(), foundUser.getPassword(), foundUser.getFirstName());

	}

	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new HashSet<>();

		if (principals.isEmpty()) {
			return null;
		}

		long userid = (Long) principals.getPrimaryPrincipal();
		UserService userService = new UserService();
		User user = new User(userid);
		final User foundUser = userService.findUser(user);

		if (foundUser.getUserType().equals(RegisteredUser.class.toString())) {
			roles.add(AppSession.REGISTERED_USER);
		} else if (foundUser.getUserType().equals(Administrator.class.toString())) {
			roles.add(AppSession.ADMINISTRATOR);
		}
		return new SimpleAuthorizationInfo(roles);

	}

}
