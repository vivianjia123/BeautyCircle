package service;

import java.util.List;

import domain.User;
import mapper.UserMapper;

public class UserService {
	private UserMapper userMapper = new UserMapper();

	public User findUser(User user) {
		return userMapper.findwithId(user);
	}

	public User findUserByEmail(User user) {
		return userMapper.findwithEmail(user);
	}

	public User findUserByuserName(User user) {
		return userMapper.findwithUsername(user);
	}

	public User findUserByuserName(String username) {
		return userMapper.findwithUsername(username);
	}

	public List<User> findUsers() {
		return userMapper.findAllUsers();
	}

}
