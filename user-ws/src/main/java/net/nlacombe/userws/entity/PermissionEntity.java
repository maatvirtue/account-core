package net.nlacombe.userws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class PermissionEntity
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int permissionId;

	private String name;

	public int getPermissionId()
	{
		return permissionId;
	}

	public void setPermissionId(int permissionId)
	{
		this.permissionId = permissionId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
