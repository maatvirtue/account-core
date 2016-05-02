package net.maatvirtue.usercore.service.security;

import net.maatvirtue.usercore.api.dto.PasswordCredential;
import net.maatvirtue.usercore.domain.User;

public interface PasswordService
{
	/**
	 * @return null if cannot positively authenticat user.
	 */
	User authenticate(PasswordCredential passwordCredential);

	String createNewPasswordCredential(User user);

	String updateUserWithNewRandomPassword(User user);
}
