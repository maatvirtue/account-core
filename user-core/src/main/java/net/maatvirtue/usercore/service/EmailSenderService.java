package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.api.validation.Password;
import net.maatvirtue.usercore.api.validation.Username;
import net.maatvirtue.usercore.service.exception.MailServiceException;
import org.hibernate.validator.constraints.Email;

public interface EmailSenderService
{
	void sendRegistrationEmail(@Email String email, @Username String username, @Password String password) throws MailServiceException;
}
