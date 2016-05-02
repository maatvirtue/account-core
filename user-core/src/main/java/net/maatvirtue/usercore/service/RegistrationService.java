package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.api.exception.UsernameTakenRestException;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.service.exception.MailServiceException;
import org.hibernate.validator.constraints.Email;

public interface RegistrationService
{
	User registerWithPassword(@Email String email) throws MailServiceException, UsernameTakenRestException;
}
