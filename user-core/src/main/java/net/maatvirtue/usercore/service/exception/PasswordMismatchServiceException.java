package net.maatvirtue.usercore.service.exception;

public class PasswordMismatchServiceException extends SecurityServiceException
{
	public PasswordMismatchServiceException()
	{
		//Do nothing
	}
	
	public PasswordMismatchServiceException(String message)
	{
		super(message);
	}
}
