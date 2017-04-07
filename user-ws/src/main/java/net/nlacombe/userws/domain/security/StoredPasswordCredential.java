package net.nlacombe.userws.domain.security;

import net.nlacombe.userws.domain.User;

public class StoredPasswordCredential
{
	private int id;
	private byte[] passwordHash;
	private String salt;
	private User user;

	public byte[] getPasswordHash()
	{
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash)
	{
		this.passwordHash = passwordHash;
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
