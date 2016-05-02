package net.maatvirtue.usercore.service.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;

public abstract class ServiceException extends RestException
{
	public ServiceException()
	{
		//
	}

	public ServiceException(String message)
	{
		super(message);
	}
}
