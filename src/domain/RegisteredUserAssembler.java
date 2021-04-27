package domain;

public class RegisteredUserAssembler {

	public static RegisteredUserDTO writeDTO(RegisteredUser user) {
		RegisteredUserDTO result = new RegisteredUserDTO();
		result.setUsername(user.getUsername());
		result.setPassword(user.getPassword());
		result.setShippingAddress(user.getAddress());
		result.setId(user.getId());

		return result;
	}

}
