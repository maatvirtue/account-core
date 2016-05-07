package net.maatvirtue.usercore.service;

import net.maatvirtue.authlib.jwt.JwsToken;
import net.maatvirtue.authlib.jwt.JwtUser;
import net.maatvirtue.usercore.domain.User;

public interface JwtService
{
	JwsToken createJwtToken(String jwtSigningSecretKey, User user);

	JwsToken createJwtToken(String jwtSigningSecretKey, JwtUser jwtUser);
}
