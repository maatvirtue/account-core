package net.nlacombe.userws.webservice.impl;

import net.nlacombe.authlib.jwt.JwtData;
import net.nlacombe.authlib.jwt.JwtGoogleUser;
import net.nlacombe.authlib.jwt.JwtUtil;
import net.nlacombe.authlib.jwt.SigningKeyNotFoundException;
import net.nlacombe.userws.api.dto.JwsToken;
import net.nlacombe.userws.api.dto.PasswordCredential;
import net.nlacombe.userws.api.exception.InvalidJwtRestException;
import net.nlacombe.userws.api.webservice.AuthenticationWebService;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.ExternalJwtCredentialService;
import net.nlacombe.userws.service.JwtService;
import net.nlacombe.userws.service.UserService;
import net.nlacombe.userws.service.security.PasswordService;
import net.nlacombe.wsutils.restexception.exception.InvalidInputRestException;
import net.nlacombe.wsutils.restexception.exception.NotFoundRestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationWebServiceImpl implements AuthenticationWebService
{
	private final PasswordService passwordService;
	private final JwtService jwtService;
	private final ExternalJwtCredentialService externalJwtCredentialService;
	private final UserService userService;
	private final JwtUtil jwtUtil;

	public AuthenticationWebServiceImpl(PasswordService passwordService, JwtService jwtService, ExternalJwtCredentialService externalJwtCredentialService, UserService userService, JwtUtil jwtUtil)
	{
		this.passwordService = passwordService;
		this.jwtService = jwtService;
		this.externalJwtCredentialService = externalJwtCredentialService;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public JwsToken authenticate(PasswordCredential passwordCredential) throws NotFoundRestException
	{
		User user = passwordService.authenticate(passwordCredential);

		return getJwsToken(user);
	}

	@Override
	public JwsToken authenticateWithExternalJwt(JwsToken externalJwsToken)
	{
        var jwtData = parseAndValidateJwt(externalJwsToken);
        User user;

		if (jwtData instanceof JwtGoogleUser)
			user = authenticateWithGoogleJwt((JwtGoogleUser) jwtData);
		else
			throw new InvalidInputRestException("JWT issuer not supported");

		return getJwsToken(user);
	}

    private JwtData parseAndValidateJwt(JwsToken externalJwsToken) {
        try {
            return jwtUtil.parseAndValidate(externalJwsToken.getToken());
        } catch (SigningKeyNotFoundException e) {
            throw new InvalidJwtRestException("Jwt signing key not found or not trusted.");
        }
    }

    private User authenticateWithGoogleJwt(JwtGoogleUser jwtGoogleUser)
	{
		User existingUser = externalJwtCredentialService.getUser(jwtGoogleUser.getIssuer(), jwtGoogleUser.getSubject());

		if (existingUser != null)
			return existingUser;

		if (!jwtGoogleUser.isEmailValidated())
			throw new InvalidInputRestException("Validate your email with google accounts before signing in here.");

		User newUser = userService.createNewUser(jwtGoogleUser.getEmail());

		externalJwtCredentialService.createExternalJwtCredential(jwtGoogleUser.getIssuer(), jwtGoogleUser.getSubject(), newUser);

		return newUser;
	}

	private JwsToken getJwsToken(User user)
	{
		if (user == null)
			throw new NotFoundRestException();

		return new JwsToken(jwtService.createJwtToken(user));
	}
}
