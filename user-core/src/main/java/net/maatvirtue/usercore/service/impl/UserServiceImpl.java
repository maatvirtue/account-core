package net.maatvirtue.usercore.service.impl;

import net.maatvirtue.usercore.domain.Email;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.UserStatus;
import net.maatvirtue.usercore.repository.EmailRepository;
import net.maatvirtue.usercore.repository.UserRepository;
import net.maatvirtue.usercore.service.UserService;
import net.maatvirtue.usercore.service.exception.UsernameTakenServiceException;
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

	public User createNewUser(@org.hibernate.validator.constraints.Email String emailText) throws UsernameTakenServiceException
	{
		if(usernameTaken(emailText))
			throw new UsernameTakenServiceException("Username already used by another user: \""+emailText+"\"");

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
