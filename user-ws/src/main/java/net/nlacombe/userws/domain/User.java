package net.nlacombe.userws.domain;

import java.util.LinkedList;
import java.util.List;

public class User
{
	private int userId;

	private String username;
	private String firstName;
	private String lastName;
	private String avatarUrl;
	private UserStatus status;
	private List<Email> emails;

	public User()
	{
		//Do nothing
	}

	public User(UserStatus status, String username)
	{
		this.status = status;
		this.username = username;
	}

	public Email getPrimaryEmail()
	{
		if(emails==null)
			return null;

		for(Email email: emails)
			if(email.isPrimary())
				return email;

		return null;
	}

	public void addEmail(Email email)
	{
		if(email==null)
			throw new IllegalArgumentException("email cannot be null");

		if(emails==null)
			emails = new LinkedList<>();

		if(emails.contains(email))
			return;

		emails.add(email);
		email.setUser(this);
	}

	@Override
	public boolean equals(Object object)
	{
		if(object==this)
			return true;

		if(object==null)
			return false;

		if(!(object instanceof User))
			return false;

		User user = (User) object;

		return user.getUserId() == userId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAvatarUrl()
	{
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}

	public UserStatus getStatus()
	{
		return status;
	}

	public void setStatus(UserStatus status)
	{
		this.status = status;
	}

	public List<Email> getEmails()
	{
		return emails;
	}

	public void setEmails(List<Email> emails)
	{
		this.emails = emails;
	}
}
