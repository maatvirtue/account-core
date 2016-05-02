package net.maatvirtue.usercore.service.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;

public class DuplicateServiceException extends RestException
{
	public DuplicateServiceException()
	{
		//
	}
	
	public DuplicateServiceException(String message)
	{
		super(message);
	}
}
