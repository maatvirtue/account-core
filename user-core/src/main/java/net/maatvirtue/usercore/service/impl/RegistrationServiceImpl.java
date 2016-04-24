package net.maatvirtue.usercore.service.impl;

import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.UserStatus;
import net.maatvirtue.usercore.service.ConfirmationEmailService;
import net.maatvirtue.usercore.service.EmailSenderService;
import net.maatvirtue.usercore.service.RegistrationService;
import net.maatvirtue.usercore.service.UserService;
import net.maatvirtue.usercore.service.exception.MailServiceException;
import net.maatvirtue.usercore.service.exception.UsernameTakenServiceException;
import net.maatvirtue.usercore.service.security.PasswordService;
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

	public User registerWithPassword(@Email String email) throws MailServiceException, UsernameTakenServiceException
	{
		if(userService.usernameTaken(email))
			throw new UsernameTakenServiceException("Username already used by another user: \""+email+"\"");

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
