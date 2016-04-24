package net.maatvirtue.usercore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="passwordCredential")
public class PasswordCredentialEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int passwordCredentialId;
	
	private byte[] passwordHash;
	private String salt;
	
	@OneToOne
	@JoinColumn(name="userId")
	private UserEntity user;
	
	public byte[] getPasswordHash()
	{
		return passwordHash;
	}
	
	public void setPasswordHash(byte[] passwordHash)
	{
		this.passwordHash=passwordHash;
	}
	
	public String getSalt()
	{
		return salt;
	}
	
	public void setSalt(String salt)
	{
		this.salt=salt;
	}
	
	public UserEntity getUser()
	{
		return user;
	}
	
	public void setUser(UserEntity user)
	{
		this.user=user;
	}

	public int getPasswordCredentialId()
	{
		return passwordCredentialId;
	}

	public void setPasswordCredentialId(int passwordCredentialId)
	{
		this.passwordCredentialId = passwordCredentialId;
	}
}
