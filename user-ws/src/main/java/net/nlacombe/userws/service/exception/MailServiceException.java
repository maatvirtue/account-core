package net.nlacombe.userws.service.exception;

import net.maatvirtue.wsutils.restexception.api.RestException;

public class MailServiceException extends RestException
{
	public MailServiceException()
	{
		//Do nothing
	}
	
	public MailServiceException(Throwable throwable)
	{
		super(throwable);
	}
}
