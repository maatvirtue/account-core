package net.maatvirtue.usercore.service.security;

import net.maatvirtue.usercore.domain.User;

public interface PasswordService extends AuthenticationServiceMethod
{
	String createNewPasswordCredential(User user);
	String updateUserWithNewRandomPassword(User user);
}
