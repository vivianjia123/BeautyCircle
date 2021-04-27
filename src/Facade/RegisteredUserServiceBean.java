package Facade;

import domain.RegisteredUser;
import domain.RegisteredUserAssembler;
import domain.RegisteredUserDTO;
import mapper.RegisteredUserMapper;

public class RegisteredUserServiceBean {

	public RegisteredUserDTO getRegisteredUser(long id) {
		RegisteredUser user = new RegisteredUser();
		user.setId(id);
		return RegisteredUserAssembler.writeDTO(RegisteredUserMapper.findwithId(user));
	}

	public String getRegisteredUserString(long id) {
		return getRegisteredUser(id).toString();
	}

}
