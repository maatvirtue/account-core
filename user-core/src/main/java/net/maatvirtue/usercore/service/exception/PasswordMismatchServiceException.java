package net.maatvirtue.usercore.service.exception;

public class PasswordMismatchServiceException extends SecurityServiceException
{
	private static final long serialVersionUID=-6981786881625693454L;

	public PasswordMismatchServiceException()
	{
		//Do nothing
	}
	
	public PasswordMismatchServiceException(String message)
	{
		super(message);
	}
}
