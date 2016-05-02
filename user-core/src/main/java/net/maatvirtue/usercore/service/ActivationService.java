package net.maatvirtue.usercore.service;

import net.maatvirtue.usercore.api.exception.UsernameTakenRestException;
import net.maatvirtue.usercore.domain.User;

public interface ActivationService
{
	void activateUser(User user) throws UsernameTakenRestException;
}
