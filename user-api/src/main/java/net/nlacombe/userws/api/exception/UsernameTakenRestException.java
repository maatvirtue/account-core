package net.nlacombe.userws.api.exception;

import net.nlacombe.wsutils.restexception.api.RestException;
import net.nlacombe.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping(errorCode = "username-already-taken", status = 409)
public class UsernameTakenRestException extends RestException
{
	public UsernameTakenRestException()
	{
	}

	public UsernameTakenRestException(String message)
	{
		super(message);
	}
}
