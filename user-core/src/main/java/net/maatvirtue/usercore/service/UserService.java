package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.UserStatus;
import net.maatvirtue.usercore.service.exception.UsernameTakenServiceException;
import org.hibernate.validator.constraints.Email;

public interface UserService
{
	boolean usernameTaken(String username);
	User getUserById(int userId);
	User getUserByUsernameAndStatus(String username, UserStatus status);
	User createNewUser(@Email String emailText) throws UsernameTakenServiceException;
	User saveUser(User user);
	void deleteUser(int userId);
}