package net.nlacombe.userws.service;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.domain.UserStatus;
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
