package domain;

public class UserAssembler {

	public UserDTO writeDTO(User user) {
		UserDTO result = new UserDTO();

		result.setAddress(user.getAddress());
		result.setEmailAddress(user.getEmailAddress());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setId(user.getId());
		return result;
	}

}
