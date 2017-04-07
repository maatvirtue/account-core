package net.nlacombe.userws.webservice.impl;

import net.maatvirtue.authlib.jwt.JwsToken;
import net.maatvirtue.wsutils.restexception.exception.NotFoundRestException;
import net.nlacombe.userws.api.dto.PasswordCredential;
import net.nlacombe.userws.api.webservice.AuthenticationWebService;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.JwtService;
import net.nlacombe.userws.service.security.PasswordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class AuthenticationWebServiceImpl implements AuthenticationWebService
{
	@Inject
	private PasswordService passwordService;

	@Inject
	private JwtService jwtService;

	@Value("${security.jwtSigningSecretKey}")
	private String jwtSigningSecretKey;

	@Override
	public JwsToken authenticate(PasswordCredential passwordCredential) throws NotFoundRestException
	{
		User user = passwordService.authenticate(passwordCredential);

		if(user == null)
			throw new NotFoundRestException();

		return jwtService.createJwtToken(jwtSigningSecretKey, user);
	}
}
