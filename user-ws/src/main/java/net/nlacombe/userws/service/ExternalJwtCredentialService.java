package net.nlacombe.userws.service;

import net.nlacombe.userws.domain.User;

public interface ExternalJwtCredentialService
{
	User getUser(String issuer, String subject);

	void createExternalJwtCredential(String issuer, String subject, User user);
}
