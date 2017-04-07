package net.nlacombe.userws.api.dto;

import org.hibernate.validator.constraints.Email;

public class EmailAddress
{
	@Email
	private String emailAddress;

	public EmailAddress()
	{
		//
	}

	public EmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}
}
