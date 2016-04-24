package net.maatvirtue.usercore.service.exception;

public class MailServiceException extends ServiceException
{
	private static final long serialVersionUID=-8959685427881051526L;

	public MailServiceException()
	{
		//Do nothing
	}
	
	public MailServiceException(String message)
	{
		super(message);
	}
	
	public MailServiceException(Throwable throwable)
	{
		super(throwable);
	}
}
