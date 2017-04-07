package net.nlacombe.userws.service.impl;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.domain.UserStatus;
import net.nlacombe.userws.repository.EmailRepository;
import net.nlacombe.userws.repository.UserRepository;
import net.nlacombe.userws.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Inject
	private UserRepository userRepository;

	@Inject
	private EmailRepository emailRepository;

	public boolean usernameTaken(String username)
	{
		return userRepository.userWithUsernameAndStatusExist(username, UserStatus.ACTIVE);
	}

	public User getUserById(int userId)
	{
		return userRepository.getUserById(userId);
	}

	@Override
	public User getUserByUsernameAndStatus(String username, UserStatus status)
	{
		return userRepository.getUserByUsernameAndStatus(username, status);
	}

	public User createNewUser(@org.hibernate.validator.constraints.Email String emailText) throws UsernameTakenRestException
	{
		if(usernameTaken(emailText))
			throw new UsernameTakenRestException("Username already used by another user: \""+emailText+"\"");

		User user = new User(UserStatus.PENDING_ACTIVATION, emailText);
		user = userRepository.saveUser(user);

		Email email = new Email(emailText, true);
		user.addEmail(email);

		Email savedEmail = emailRepository.saveEmail(email);
		email.setEmailId(savedEmail.getEmailId());

		return user;
	}

	public User saveUser(User user)
	{
		return userRepository.saveUser(user);
	}

	@Override
	public void deleteUser(int userId)
	{
		userRepository.deleteUser(userId);
	}
}
