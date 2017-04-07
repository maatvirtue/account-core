package net.nlacombe.userws.webservice.impl;

import net.nlacombe.userws.api.dto.EmailAddress;
import net.nlacombe.userws.api.exception.UsernameTakenRestException;
import net.nlacombe.userws.api.webservice.RegistrationWebService;
import net.nlacombe.userws.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;

@Service
@Transactional
public class RegistrationWebServiceImpl implements RegistrationWebService
{
	@Inject
	private RegistrationService registrationService;

	@Override
	public void register(@Valid EmailAddress emailAddress) throws UsernameTakenRestException
	{
		registrationService.registerWithPassword(emailAddress.getEmailAddress());
	}
}
