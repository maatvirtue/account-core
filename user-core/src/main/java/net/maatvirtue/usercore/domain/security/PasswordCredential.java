package net.maatvirtue.usercore.domain.security;

import net.maatvirtue.usercore.api.validation.Password;
import net.maatvirtue.usercore.api.validation.Username;

public class PasswordCredential
{
	@Username
	private String username;

	@Password
	private String password;

	public PasswordCredential()
	{
		//Do nothing
	}

	public PasswordCredential(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
}
