package net.nlacombe.userws.webservice.impl;

import net.nlacombe.userws.api.webservice.JwkWebService;
import net.nlacombe.userws.service.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultJwkWebService implements JwkWebService {

    private final JwtService jwtService;

    public DefaultJwkWebService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String getJwkSetJson() {
        return jwtService.getJwkSetJson();
    }
}
