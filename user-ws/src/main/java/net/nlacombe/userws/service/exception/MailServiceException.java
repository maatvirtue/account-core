package net.nlacombe.userws.service.exception;

import net.nlacombe.wsutils.restexception.api.RestException;

public class MailServiceException extends RestException
{
	public MailServiceException()
	{
	}

	public MailServiceException(Throwable throwable)
	{
		super(throwable);
	}
}
