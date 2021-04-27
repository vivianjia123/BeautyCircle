package service;

import java.util.List;

import domain.Administrator;
import mapper.AdministratorMapper;
import utility.UnitOfWork;

public class AdministratorService {

	private AdministratorMapper administratorMapper = new AdministratorMapper();

	public void createAdmin(Administrator user) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(user);
		UnitOfWork.getCurrent().commit();
	}

	public void updateAdmin(Administrator user) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDirty(user);
		UnitOfWork.getCurrent().commit();
	}

	public void deleteAdmin(Administrator user) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDeleted(user);
		UnitOfWork.getCurrent().commit();
	}

	public Administrator findUser(Administrator user) {
		return AdministratorMapper.findwithId(user);
	}

	public Administrator findUserByEmail(Administrator user) {
		return administratorMapper.findwithEmail(user);
	}

	public Administrator findUserByuserName(Administrator user) {
		return AdministratorMapper.findwithUsername(user);
	}

	public List<Administrator> findAllUsers() {
		return administratorMapper.findAllUsers();
	}

}
