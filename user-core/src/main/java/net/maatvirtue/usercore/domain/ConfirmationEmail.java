package net.maatvirtue.usercore.domain;

public class ConfirmationEmail
{
	private int confirmationEmailId;
	private Email email;
	private String confirmationCode;

	public ConfirmationEmail()
	{
		//Do nothing
	}

	public ConfirmationEmail(Email email)
	{
		this(email, null);
	}

	public ConfirmationEmail(Email email, String confirmationCode)
	{
		this.email = email;
		this.confirmationCode = confirmationCode;
	}

	public int getConfirmationEmailId()
	{
		return confirmationEmailId;
	}

	public void setConfirmationEmailId(int confirmationEmailId)
	{
		this.confirmationEmailId = confirmationEmailId;
	}

	public Email getEmail()
	{
		return email;
	}

	public void setEmail(Email email)
	{
		this.email = email;
	}

	public String getConfirmationCode()
	{
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode)
	{
		this.confirmationCode = confirmationCode;
	}
}
