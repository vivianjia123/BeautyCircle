package domain;

public class AdminAssembler {

	public AdministratorDTO writeDTO(Administrator admin) {
		AdministratorDTO adminDTO = new AdministratorDTO();
		adminDTO.setAdminLogin(admin.getAdminLogin());
		adminDTO.setAdminpassword(admin.getAdminpassword());
		return adminDTO;

	}

}
