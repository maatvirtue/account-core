package net.nlacombe.userws.api.dto;

public class JwsToken
{
	private String token;

	public JwsToken()
	{
	}

	public JwsToken(String token)
	{
		this.token = token;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}
