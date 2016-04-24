package net.maatvirtue.usercore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="role")
public class RoleEntity
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int roleId;

	private String name;

	@ManyToMany
	@JoinTable(name="role_permission", joinColumns={@JoinColumn(name="roleId")}, inverseJoinColumns={@JoinColumn(name="permissionId")})
	private List<PermissionEntity> permissions;

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<PermissionEntity> getPermissions()
	{
		return permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions)
	{
		this.permissions = permissions;
	}
}
