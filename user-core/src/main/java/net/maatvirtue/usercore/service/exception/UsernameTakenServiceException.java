package net.maatvirtue.usercore.service.exception;

public class UsernameTakenServiceException extends DuplicateServiceException
{
	private static final long serialVersionUID=5646821216059839124L;

	public UsernameTakenServiceException()
	{
		//Do nothing
	}
	
	public UsernameTakenServiceException(String message)
	{
		super(message);
	}
	
	public UsernameTakenServiceException(Throwable throwable)
	{
		super(throwable);
	}
}
