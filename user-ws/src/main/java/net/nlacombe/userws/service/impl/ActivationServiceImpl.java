package net.nlacombe.userws.service.impl;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.domain.UserStatus;
import net.nlacombe.userws.service.ActivationService;
import net.nlacombe.userws.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService
{
	@Inject
	private UserService userService;

	public void activateUser(User user) throws UsernameTakenRestException
	{
		if (user.getStatus() != UserStatus.PENDING_ACTIVATION)
			return;

		String username = user.getUsername();

		if(userService.usernameTaken(username))
			throw new UsernameTakenRestException("Username already used by another user: \""+username+"\"");

		user.setStatus(UserStatus.ACTIVE);

		userService.saveUser(user);
	}
}
