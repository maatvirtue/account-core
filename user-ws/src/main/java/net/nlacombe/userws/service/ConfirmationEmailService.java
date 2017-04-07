package net.nlacombe.userws.service;

import net.nlacombe.userws.domain.ConfirmationEmail;
import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.domain.User;

public interface ConfirmationEmailService
{
	void confirmEmail(User user, String confirmationCode);
	void confirmEmail(Email email);
	ConfirmationEmail saveConfirmationEmail(Email email, String newConfirmationCode);
}
