package net.maatvirtue.usercore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="user")
public class UserEntity implements Serializable
{
	private static final long serialVersionUID=-994466999176147953L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	private String username;
	private String firstName;
	private String lastName;
	private String avatarUrl;
	
	@ManyToOne
	@JoinColumn(name="userStatusId")
	private UserStatusEntity status;

	@OneToMany(mappedBy = "user")
	private List<EmailEntity> emails;
	
	public UserEntity()
	{
		//Do nothing
	}
	
	public int getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId=userId;
	}
	
	public String getAvatarUrl()
	{
		return this.avatarUrl;
	}
	
	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl=avatarUrl;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public UserStatusEntity getStatus()
	{
		return status;
	}
	
	public void setStatus(UserStatusEntity status)
	{
		this.status=status;
	}

	public List<EmailEntity> getEmails()
	{
		return emails;
	}

	public void setEmails(List<EmailEntity> emails)
	{
		this.emails = emails;
	}
}
