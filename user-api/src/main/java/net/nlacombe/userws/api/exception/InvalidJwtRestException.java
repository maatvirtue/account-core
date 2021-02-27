package net.nlacombe.userws.api.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "invalid-jwt")
public class InvalidJwtRestException extends RestException {
    private static final int HTTP_STATUS = 400;

    public InvalidJwtRestException() {
        super(HTTP_STATUS);
    }

    public InvalidJwtRestException(String message) {
        super(HTTP_STATUS, message);
    }
}
