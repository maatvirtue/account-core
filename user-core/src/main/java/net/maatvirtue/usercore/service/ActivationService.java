package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.service.exception.UsernameTakenServiceException;

public interface ActivationService
{
	void activateUser(User user) throws UsernameTakenServiceException;
}
