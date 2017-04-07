package net.nlacombe.userws.service;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.exception.MailServiceException;
import org.hibernate.validator.constraints.Email;

public interface RegistrationService
{
	User registerWithPassword(@Email String email) throws MailServiceException, UsernameTakenRestException;
}
