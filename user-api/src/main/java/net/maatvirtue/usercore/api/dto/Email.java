package net.maatvirtue.usercore.api.dto;

public class Email
{
	@org.hibernate.validator.constraints.Email
	private String email;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
