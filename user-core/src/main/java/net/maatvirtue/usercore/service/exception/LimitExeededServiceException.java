package net.maatvirtue.usercore.service.exception;

public class LimitExeededServiceException extends ServiceException
{
	private static final long serialVersionUID=704722928612264843L;

	public LimitExeededServiceException()
	{
		//Do nothing
	}
	
	public LimitExeededServiceException(String message)
	{
		super(message);
	}
	
	public LimitExeededServiceException(Throwable throwable)
	{
		super(throwable);
	}
}
