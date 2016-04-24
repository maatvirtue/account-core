package net.maatvirtue.usercore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class EmailEntity
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int emailId;

	private String email;
	private boolean confirmed;

	@Column(name="\"primary\"")
	private boolean primary;

	@ManyToOne
	@JoinColumn(name="userId")
	private UserEntity user;

	public UserEntity getUser()
	{
		return user;
	}

	public void setUser(UserEntity user)
	{
		this.user = user;
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

	public int getEmailId()
	{
		return emailId;
	}

	public void setEmailId(int emailId)
	{
		this.emailId = emailId;
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
