package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.api.exception.UsernameTakenRestException;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.UserStatus;
import org.hibernate.validator.constraints.Email;

public interface UserService
{
	boolean usernameTaken(String username);
	User getUserById(int userId);
	User getUserByUsernameAndStatus(String username, UserStatus status);
	User createNewUser(@Email String emailText) throws UsernameTakenRestException;
	User saveUser(User user);
	void deleteUser(int userId);
}
