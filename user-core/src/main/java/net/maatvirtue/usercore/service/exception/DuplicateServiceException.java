package net.maatvirtue.usercore.service.exception;

public class DuplicateServiceException extends ServiceException
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
