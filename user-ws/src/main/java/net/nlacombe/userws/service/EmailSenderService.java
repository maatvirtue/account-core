package net.nlacombe.userws.service;

import net.nlacombe.userws.api.validation.Password;
import net.nlacombe.userws.api.validation.Username;
import net.nlacombe.userws.service.exception.MailServiceException;
import org.hibernate.validator.constraints.Email;

public interface EmailSenderService
{
	void sendRegistrationEmail(@Email String email, @Username String username, @Password String password) throws MailServiceException;
}
