package net.maatvirtue.usercore.service.security;

import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.security.PasswordCredential;

public interface PasswordService
{
	/**
	 * @return null if cannot positively authenticat user.
	 */
	User authenticate(PasswordCredential passwordCredential);

	String createNewPasswordCredential(User user);

	String updateUserWithNewRandomPassword(User user);
}
