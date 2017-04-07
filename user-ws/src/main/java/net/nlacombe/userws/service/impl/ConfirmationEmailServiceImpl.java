package net.nlacombe.userws.service.impl;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.ConfirmationEmail;
import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.repository.ConfirmationEmailRepository;
import net.nlacombe.userws.repository.EmailRepository;
import net.nlacombe.userws.service.ActivationService;
import net.nlacombe.userws.service.ConfirmationEmailService;
import net.nlacombe.userws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class ConfirmationEmailServiceImpl implements ConfirmationEmailService
{
	private static final Logger logger = LoggerFactory.getLogger(ConfirmationEmailServiceImpl.class);

	@Inject
	private EmailRepository emailRepository;

	@Inject
	private ConfirmationEmailRepository confirmationEmailRepository;

	@Inject
	private ActivationService activationService;

	@Inject
	private UserService userService;

	public void confirmEmail(User user, String confirmationCode)
	{
		Email email = findEmail(user, confirmationCode);

		if (email != null)
			confirmEmail(email);
	}

	private Email findEmail(User user, String confirmationCode)
	{
		List<ConfirmationEmail> confirmationEmails = confirmationEmailRepository.findByConfirmationCode(confirmationCode);

		for (ConfirmationEmail confirmationEmail : confirmationEmails)
		{
			Email email = confirmationEmail.getEmail();

			if (email != null && email.getUser().equals(user))
				return email;
		}

		return null;
	}

	public void confirmEmail(Email email)
	{
		if (email == null)
			throw new IllegalArgumentException("email cannot be null");

		email.setConfirmed(true);
		emailRepository.saveEmail(email);

		confirmationEmailRepository.deleteByEmail(email);

		if (email.isPrimary())
		{
			User user = email.getUser();

			try
			{
				activationService.activateUser(user);
			}
			catch (UsernameTakenRestException exception)
			{
				logger.warn("Failed activating userId " + user.getUserId() + ", username already taken. Deleting user.");
				userService.deleteUser(user.getUserId());
			}
		}
	}

	public ConfirmationEmail saveConfirmationEmail(Email email, String newConfirmationCode)
	{
		ConfirmationEmail confirmationEmail = confirmationEmailRepository.findByEmail(email);

		if (confirmationEmail == null)
			confirmationEmail = new ConfirmationEmail(email);

		confirmationEmail.setConfirmationCode(newConfirmationCode);

		return confirmationEmailRepository.save(confirmationEmail);
	}
}
