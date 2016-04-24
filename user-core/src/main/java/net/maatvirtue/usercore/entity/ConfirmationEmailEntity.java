package net.maatvirtue.usercore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="confirmationEmail")
public class ConfirmationEmailEntity
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int confirmationEmailId;

	@ManyToOne
	@JoinColumn(name="emailId")
	private EmailEntity email;

	private String confirmationCode;

	public int getConfirmationEmailId()
	{
		return confirmationEmailId;
	}

	public void setConfirmationEmailId(int confirmationEmailId)
	{
		this.confirmationEmailId = confirmationEmailId;
	}

	public EmailEntity getEmail()
	{
		return email;
	}

	public void setEmail(EmailEntity email)
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
