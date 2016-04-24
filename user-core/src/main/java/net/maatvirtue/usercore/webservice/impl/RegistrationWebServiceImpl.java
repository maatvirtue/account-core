package net.maatvirtue.usercore.webservice.impl;

import net.maatvirtue.usercore.api.dto.Email;
import net.maatvirtue.usercore.api.webservice.RegistrationWebService;
import net.maatvirtue.usercore.service.RegistrationService;
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
	public void register(@Valid Email email)
	{
		registrationService.registerWithPassword(email.getEmail());
	}
}
