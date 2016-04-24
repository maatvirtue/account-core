package net.maatvirtue.usercore.domain;

public enum UserStatus
{
	PENDING_ACTIVATION("pendingActivation"),
	ACTIVE("active");
	
	private String code;
	
	UserStatus(String code)
	{
		this.code=code;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public static UserStatus getByCode(String code)
	{
		for(UserStatus userStatus: values())
			if(userStatus.getCode().equals(code))
				return userStatus;
		
		throw new IllegalArgumentException("No "+UserStatus.class.getSimpleName()+" for code \""+code+"\"");
	}
}
