package net.nlacombe.userws.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.nlacombe.authlib.jwt.JwsToken;
import net.nlacombe.authlib.jwt.JwtClaim;
import net.nlacombe.authlib.jwt.JwtUser;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.JwtService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService
{
	private static final Integer JWT_TTL_HOURS = 2;
	private static final SignatureAlgorithm JWT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
	private static final String ISSUER = "user-api.nlacombe.net";

	@Override
	public JwsToken createJwtToken(String jwtSigningSecretKey, User user)
	{
		JwtUser jwtUser = new JwtUser();
		jwtUser.setIssuedAt(new Date());
		jwtUser.setExpiration(Date.from(OffsetDateTime.now().plusHours(JWT_TTL_HOURS).toInstant()));
		jwtUser.setIssuer(ISSUER);
		jwtUser.setUserId(user.getUserId());
		jwtUser.setSubject(Integer.toString(user.getUserId()));

		return createJwtToken(jwtSigningSecretKey, jwtUser);
	}

	@Override
	public JwsToken createJwtToken(String jwtSigningSecretKey, JwtUser jwtUser)
	{
		String token = Jwts.builder()
				.setIssuer(jwtUser.getIssuer())
				.setSubject(jwtUser.getSubject())
				.setExpiration(jwtUser.getExpiration())
				.setIssuedAt(jwtUser.getIssuedAt())
				.claim(JwtClaim.USER_ID.getName(), jwtUser.getUserId())
				.signWith(JWT_SIGNATURE_ALGORITHM, jwtSigningSecretKey)
				.compact();

		return new JwsToken(token);
	}
}
