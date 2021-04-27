package service;

import java.util.List;

import domain.RegisteredUser;
import mapper.RegisteredUserMapper;
import utility.UnitOfWork;

public class RegisteredUserService {

	private RegisteredUserMapper userMapper = new RegisteredUserMapper();

	public void createUser(RegisteredUser user) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(user);
		UnitOfWork.getCurrent().commit();
	}

	public void updateUser(RegisteredUser user) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDirty(user);
		UnitOfWork.getCurrent().commit();
	}

	public void deleteUser(RegisteredUser user) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDeleted(user);
		UnitOfWork.getCurrent().commit();
	}

	public RegisteredUser findUser(RegisteredUser user) {
		return RegisteredUserMapper.findwithId(user);
	}

	public RegisteredUser findUserByEmail(RegisteredUser user) {
		return userMapper.findwithEmail(user);
	}

	public RegisteredUser findUserByuserName(RegisteredUser user) {
		return RegisteredUserMapper.findwithUsername(user);
	}

	public List<RegisteredUser> findAllUsers() {
		return userMapper.findAllUsers();
	}

}
