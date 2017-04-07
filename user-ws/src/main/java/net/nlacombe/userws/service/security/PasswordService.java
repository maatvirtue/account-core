package net.nlacombe.userws.service.security;

import net.nlacombe.userws.api.dto.PasswordCredential;
import net.nlacombe.userws.domain.User;

public interface PasswordService
{
	/**
	 * @return null if cannot positively authenticat user.
	 */
	User authenticate(PasswordCredential passwordCredential);

	String createNewPasswordCredential(User user);

	String updateUserWithNewRandomPassword(User user);
}
