package net.nlacombe.userws.service.impl;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.domain.UserStatus;
import net.nlacombe.userws.service.ConfirmationEmailService;
import net.nlacombe.userws.service.EmailSenderService;
import net.nlacombe.userws.service.RegistrationService;
import net.nlacombe.userws.service.UserService;
import net.nlacombe.userws.service.exception.MailServiceException;
import net.nlacombe.userws.service.security.PasswordService;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService
{
	@Inject
	private UserService userService;

	@Inject
	private PasswordService passwordService;

	@Inject
	private EmailSenderService emailSenderService;

	@Inject
	private ConfirmationEmailService confirmationEmailService;

	public User registerWithPassword(@Email String email) throws MailServiceException, UsernameTakenRestException
	{
		if(userService.usernameTaken(email))
			throw new UsernameTakenRestException("Username already used by another user: \""+email+"\"");

		User user = userService.getUserByUsernameAndStatus(email, UserStatus.PENDING_ACTIVATION);
		String password;

		if(user!=null)
		{
			password = passwordService.updateUserWithNewRandomPassword(user);
		}
		else
		{
			user = userService.createNewUser(email);
			password = passwordService.createNewPasswordCredential(user);
		}

		confirmationEmailService.saveConfirmationEmail(user.getPrimaryEmail(), password);

		emailSenderService.sendRegistrationEmail(email, email, password);

		return user;
	}
}
