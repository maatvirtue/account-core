package net.maatvirtue.usercore.service.exception;

import net.maatvirtue.wsutils.restexception.api.RestExceptionMapping;

@RestExceptionMapping("access-denied")
public class AccessDeniedServiceException extends SecurityServiceException
{
	public AccessDeniedServiceException(String message)
	{
		super(message);
	}
}
