package net.nlacombe.userws.service.impl;

import net.nlacombe.authlib.jwt.JwtUser;
import net.nlacombe.authlib.jwt.JwtUtil;
import net.nlacombe.crypto.service.CryptoService;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.PrivateKey;

@Service
public class JwtServiceImpl implements JwtService {

    private final CryptoService cryptoService;
    private final JwtUtil jwtUtil;
    private final PrivateKey nlacombeNetPrivateKey;

    public JwtServiceImpl(
        @Value("${jwt.signing-private-key.location}") String jwtSigningPrivateKeyLocation,
        @Value("${security.privateKey.nlacombeNetV2.password}") String nlacombeNetV2PrivateKeyPassword,
        ResourceLoader resourceLoader,
        JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;

        cryptoService = CryptoService.getInstance();
        nlacombeNetPrivateKey = getNlacombeNetPrivateKey(resourceLoader, jwtSigningPrivateKeyLocation, nlacombeNetV2PrivateKeyPassword);
    }

    @Override
    public String createJwtToken(User user) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getUserId());
        jwtUser.setSubject(Integer.toString(user.getUserId()));

        return jwtUtil.createJwsToken(nlacombeNetPrivateKey, jwtUser);
    }

    private PrivateKey getNlacombeNetPrivateKey(ResourceLoader resourceLoader, String jwtSigningPrivateKeyLocation, String nlacombeNetV2PrivateKeyPassword) {
        try {
            var keypairInputStream = resourceLoader.getResource(jwtSigningPrivateKeyLocation).getInputStream();
            var keyPair = cryptoService.readEncryptedKeyPairFromPem(keypairInputStream, nlacombeNetV2PrivateKeyPassword);

            return keyPair.getPrivate();
        } catch (IOException e) {
            var message = "Error while getting jwt signing private key. jwtSigningPrivateKeyLocation: $jwtSigningPrivateKeyLocation"
                .replace("$jwtSigningPrivateKeyLocation", jwtSigningPrivateKeyLocation);
            throw new RuntimeException(message);
        }
    }
}
