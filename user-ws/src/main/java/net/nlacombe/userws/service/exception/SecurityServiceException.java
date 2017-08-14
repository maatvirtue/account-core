package net.nlacombe.userws.service.exception;


import net.nlacombe.wsutils.restexception.api.RestException;

public class SecurityServiceException extends RestException
{
	public SecurityServiceException()
	{
	}

	public SecurityServiceException(String message)
	{
		super(message);
	}
}
