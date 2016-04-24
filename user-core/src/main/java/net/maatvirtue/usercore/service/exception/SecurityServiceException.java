package net.maatvirtue.usercore.service.exception;

public class SecurityServiceException extends ServiceException
{
	public SecurityServiceException()
	{
		//Do nothing
	}

	public SecurityServiceException(String message)
	{
		super(message);
	}
	
	public SecurityServiceException(Throwable throwable)
	{
		super(throwable);
	}
}
