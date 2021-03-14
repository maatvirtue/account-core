package net.nlacombe.userws.service.impl;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import net.nlacombe.authlib.jwt.JwtUser;
import net.nlacombe.authlib.jwt.JwtUtil;
import net.nlacombe.crypto.api.CryptoService;
import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

@Service
public class JwtServiceImpl implements JwtService {

    private static final URL JWK_SET_URL = toUrl("https://user-api.nlacombe.net/v1/jwks");

    private final CryptoService cryptoService;
    private final JwtUtil jwtUtil;
    private final KeyPair nlacombeNetKeyPair;

    public JwtServiceImpl(
        @Value("${jwt.signing-private-key.location}") String jwtSigningPrivateKeyLocation,
        @Value("${jwt.signing-private-key.password}") String jwtSigningPrivateKeyPassword,
        ResourceLoader resourceLoader,
        JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;

        cryptoService = CryptoService.getInstance();
        nlacombeNetKeyPair = getNlacombeNetKeyPair(resourceLoader, jwtSigningPrivateKeyLocation, jwtSigningPrivateKeyPassword);
    }

    @Override
    public String createJwtToken(User user) {
        var jwtUser = new JwtUser();
        jwtUser.setUserId(user.getUserId());
        jwtUser.setSubject(Integer.toString(user.getUserId()));

        return jwtUtil.createJwsToken(nlacombeNetKeyPair, JWK_SET_URL, jwtUser);
    }

    @Override
    public String getJwkSetJson() {
        var jwtSigningPublicKey = nlacombeNetKeyPair.getPublic();
        var rsaPublicKey = getRsaPublicKey(jwtSigningPublicKey);

        var jwkBuilder = new RSAKey.Builder(rsaPublicKey)
            .keyUse(KeyUse.SIGNATURE)
            .algorithm(toJwsAlgorithm(jwtUtil.getJwaSigningAlgorithmUsedFor(nlacombeNetKeyPair.getPrivate())))
            .keyID(cryptoService.getPublicKeyFingerPrintHexString(jwtSigningPublicKey));

        var key = jwkBuilder.build();
        var jwkSet = new JWKSet(key);

        return jwkSet.toString(true);
    }

    private JWSAlgorithm toJwsAlgorithm(String jwaAlgorithmName) {
        switch (jwaAlgorithmName) {
            case "RS256":
                return JWSAlgorithm.RS256;
            case "RS384":
                return JWSAlgorithm.RS384;
            case "RS512":
                return JWSAlgorithm.RS512;
            default:
                throw new RuntimeException("Unknown jwa algorithm: " + jwaAlgorithmName);
        }
    }

    private RSAPublicKey getRsaPublicKey(PublicKey jwtSigningPublicKey) {
        try {
            return (RSAPublicKey) jwtSigningPublicKey;
        } catch (ClassCastException e) {
            throw new RuntimeException("Error: jwt public signing key format conversion to jwk not implemented/supported", e);
        }
    }

    private KeyPair getNlacombeNetKeyPair(ResourceLoader resourceLoader, String jwtSigningPrivateKeyLocation, String jwtSigningPrivateKeyPassword) {
        try {
            var keypairInputStream = resourceLoader.getResource(jwtSigningPrivateKeyLocation).getInputStream();

            return cryptoService.readEncryptedKeyPairFromPem(keypairInputStream, jwtSigningPrivateKeyPassword);
        } catch (IOException e) {
            var message = "Error while getting jwt signing private key. jwtSigningPrivateKeyLocation: $jwtSigningPrivateKeyLocation"
                .replace("$jwtSigningPrivateKeyLocation", jwtSigningPrivateKeyLocation);
            throw new RuntimeException(message, e);
        }
    }

    private static URL toUrl(String urlText) {
        try {
            return new URI(urlText).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
