package net.nlacombe.userws.api.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;
import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

import javax.ws.rs.core.Response;

@RestExceptionMapping(value = "username-already-taken", status = Response.Status.CONFLICT)
public class UsernameTakenRestException extends RestException
{
	public UsernameTakenRestException()
	{
		//Do nothing
	}

	public UsernameTakenRestException(String message)
	{
		super(message);
	}
}
