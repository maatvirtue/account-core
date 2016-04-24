package net.maatvirtue.usercore.domain;

public enum Permission
{
	;

	private String name;

	Permission(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public static Permission getByName(String name)
	{
		for(Permission permission: values())
			if(permission.getName().equals(name))
				return permission;

		throw new IllegalArgumentException("no permission with name \""+name+"\"");
	}
}
