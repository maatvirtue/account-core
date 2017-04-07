package net.nlacombe.userws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="userStatus")
public class UserStatusEntity implements Serializable
{
	private static final long serialVersionUID=7533618212948263611L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userStatusId;
	
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
