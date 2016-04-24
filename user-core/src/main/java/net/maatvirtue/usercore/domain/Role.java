package net.maatvirtue.usercore.domain;

public enum Role
{
	;

	private String name;

	Role(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public static Role getByName(String name)
	{
		for(Role role: values())
			if(role.getName().equals(name))
				return role;

		throw new IllegalArgumentException("no role with name \""+name+"\"");
	}
}
