package net.maatvirtue.usercore.service.impl;

import net.maatvirtue.usercore.api.exception.UsernameTakenRestException;
import net.maatvirtue.usercore.domain.ConfirmationEmail;
import net.maatvirtue.usercore.domain.Email;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.repository.ConfirmationEmailRepository;
import net.maatvirtue.usercore.repository.EmailRepository;
import net.maatvirtue.usercore.service.ActivationService;
import net.maatvirtue.usercore.service.ConfirmationEmailService;
import net.maatvirtue.usercore.service.UserService;
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

		if(email!=null)
			confirmEmail(email);
	}

	private Email findEmail(User user, String confirmationCode)
	{
		List<ConfirmationEmail> confirmationEmails = confirmationEmailRepository.findByConfirmationCode(confirmationCode);
		Email email;

		for(ConfirmationEmail confirmationEmail: confirmationEmails)
		{
			email = confirmationEmail.getEmail();

			if(email!=null && email.getUser().equals(user))
			{
				return  email;
			}
		}

		return null;
	}

	public void confirmEmail(Email email)
	{
		if(email==null)
			throw new IllegalArgumentException("email cannot be null");

		email.setConfirmed(true);
		emailRepository.saveEmail(email);

		confirmationEmailRepository.deleteByEmail(email);

		if(email.isPrimary())
		{
			User user = email.getUser();

			try
			{
				activationService.activateUser(user);
			}
			catch(UsernameTakenRestException exception)
			{
				logger.warn("Failed activating userId "+user.getUserId()+", username already taken. Deleting user.");
				userService.deleteUser(user.getUserId());
			}
		}
	}

	public ConfirmationEmail saveConfirmationEmail(Email email, String newConfirmationCode)
	{
		ConfirmationEmail confirmationEmail = confirmationEmailRepository.findByEmail(email);

		if(confirmationEmail==null)
			confirmationEmail = new ConfirmationEmail(email);

		confirmationEmail.setConfirmationCode(newConfirmationCode);

		return confirmationEmailRepository.save(confirmationEmail);
	}
}
