package net.maatvirtue.usercore.webservice.impl;

import net.maatvirtue.authlib.jwt.JwsToken;
import net.maatvirtue.usercore.api.dto.PasswordCredential;
import net.maatvirtue.usercore.api.webservice.AuthenticationWebService;
import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.service.JwtService;
import net.maatvirtue.usercore.service.security.PasswordService;
import net.maatvirtue.wsutils.restexception.exception.NotFoundRestException;
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
