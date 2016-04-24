package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.domain.ConfirmationEmail;
import net.maatvirtue.usercore.domain.Email;
import net.maatvirtue.usercore.domain.User;

public interface ConfirmationEmailService
{
	void confirmEmail(User user, String confirmationCode);
	void confirmEmail(Email email);
	ConfirmationEmail saveConfirmationEmail(Email email, String newConfirmationCode);
}
