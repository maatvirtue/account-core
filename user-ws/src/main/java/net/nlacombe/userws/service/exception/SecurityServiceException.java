package net.nlacombe.userws.service.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;

public class SecurityServiceException extends RestException
{
	public SecurityServiceException()
	{
		//Do nothing
	}

	public SecurityServiceException(String message)
	{
		super(message);
	}
}
