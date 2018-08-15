package net.nlacombe.userws.service.impl;

import net.nlacombe.authlib.jwt.JwtUser;
import net.nlacombe.authlib.jwt.JwtUtil;
import net.nlacombe.crypto.service.CryptoService;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.PrivateKey;

@Service
public class JwtServiceImpl implements JwtService
{
	private CryptoService cryptoService;
	private String nlacombeNetV2PrivateKeyPassword;
	private PrivateKey nlacombeNetPrivateKey;
	private JwtUtil jwtUtil;

	@Inject
	public JwtServiceImpl(@Value("${security.privateKey.nlacombeNetV2.password}") String nlacombeNetV2PrivateKeyPassword,
						  JwtUtil jwtUtil)
	{
		this.nlacombeNetV2PrivateKeyPassword = nlacombeNetV2PrivateKeyPassword;
		this.jwtUtil = jwtUtil;

		cryptoService = CryptoService.getInstance();
	}

	@Override
	public String createJwtToken(User user)
	{
		JwtUser jwtUser = new JwtUser();
		jwtUser.setUserId(user.getUserId());
		jwtUser.setSubject(Integer.toString(user.getUserId()));

		return jwtUtil.createJwsToken(getNlacombeNetPrivateKey(), jwtUser);
	}

	private PrivateKey getNlacombeNetPrivateKey()
	{
		if (nlacombeNetPrivateKey == null) {
			InputStream keypairInputStream = getClass().getResourceAsStream("/nlacombe-net_v2.pem");
			KeyPair keyPair = cryptoService.readEncryptedKeyPairFromPem(keypairInputStream, nlacombeNetV2PrivateKeyPassword);
			this.nlacombeNetPrivateKey = keyPair.getPrivate();
		}

		return nlacombeNetPrivateKey;
	}
}
