package net.maatvirtue.usercore.mapper;

import net.maatvirtue.usercore.domain.UserStatus;
import net.maatvirtue.usercore.entity.UserStatusEntity;
import net.maatvirtue.usercore.jparepository.UserStatusJpaRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class UserStatusEntityMapper extends AbstractEntityMapper<UserStatus, UserStatusEntity>
{
	@Inject
	private UserStatusJpaRepository userStatusJpaRepository;

	@Override
	public UserStatus mapToDomainType(UserStatusEntity userStatusEntity, MapperCache cache)
	{
		return UserStatus.getByCode(userStatusEntity.getName());
	}

	@Override
	public UserStatusEntity mapToEntityType(UserStatus userStatus, MapperCache cache)
	{
		return userStatusJpaRepository.findByName(userStatus.getCode());
	}
}
