package net.nlacombe.userws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="externalJwtCredential")
public class ExternalJwtCredentialEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int externalJwtCredentialId;
	
	private String issuer;
	private String subject;
	
	@OneToOne
	@JoinColumn(name="userId")
	private UserEntity user;

	public int getExternalJwtCredentialId()
	{
		return externalJwtCredentialId;
	}

	public void setExternalJwtCredentialId(int externalJwtCredentialId)
	{
		this.externalJwtCredentialId = externalJwtCredentialId;
	}

	public String getIssuer()
	{
		return issuer;
	}

	public void setIssuer(String issuer)
	{
		this.issuer = issuer;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public UserEntity getUser()
	{
		return user;
	}

	public void setUser(UserEntity user)
	{
		this.user = user;
	}
}
