package net.maatvirtue.usercore.domain;

public class Email
{
	private int emailId;
	private String email;
	private boolean confirmed;
	private boolean primary;
	private User user;

	public Email()
	{
		//Do nothing
	}

	public Email(String email, boolean primary)
	{
		this(email, primary, null);
	}

	public Email(String email, boolean primary, User user)
	{
		this(email, primary, false, user);
	}

	public Email(String email, boolean primary, boolean confirmed, User user)
	{
		this.email = email;
		this.primary = primary;
		this.confirmed = confirmed;

		setUser(user);
	}

	public int getEmailId()
	{
		return emailId;
	}

	public void setEmailId(int emailId)
	{
		this.emailId = emailId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public boolean isConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(boolean confirmed)
	{
		this.confirmed = confirmed;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;

		if(user!=null)
			user.addEmail(this);
	}

	public boolean isPrimary()
	{
		return primary;
	}

	public void setPrimary(boolean primary)
	{
		this.primary = primary;
	}
}
