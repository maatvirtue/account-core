package net.nlacombe.userws.service;

import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.domain.User;

public interface ActivationService
{
	void activateUser(User user) throws UsernameTakenRestException;
}
