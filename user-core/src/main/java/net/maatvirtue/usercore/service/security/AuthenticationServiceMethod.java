package net.maatvirtue.usercore.service.security;

import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.security.Credential;

public interface AuthenticationServiceMethod
{
	/**
	 * Returns the user linked with the specified credentials.
	 * If the credentials are not valid, this method must return null.
	 */
	User authenticate(Credential credential);
}
