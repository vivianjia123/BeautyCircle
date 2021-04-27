package Facade;

import domain.AdminAssembler;
import domain.Administrator;
import domain.AdministratorDTO;
import mapper.AdministratorMapper;

public class AdminServiceBean {

	public AdministratorDTO getAdmin(long id) {
		Administrator admin = new Administrator();
		admin.setUserId(id);
		return new AdminAssembler().writeDTO(AdministratorMapper.findwithId(admin));
	}

	public String getAdminString(long id) {
		return getAdmin(id).toString();
	}

}