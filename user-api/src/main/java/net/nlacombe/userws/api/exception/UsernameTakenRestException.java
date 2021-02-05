package net.nlacombe.userws.api.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "username-already-taken")
public class UsernameTakenRestException extends RestException {
    public UsernameTakenRestException() {
        super(409);
    }

    public UsernameTakenRestException(String message) {
        super(409, message);
    }
}
