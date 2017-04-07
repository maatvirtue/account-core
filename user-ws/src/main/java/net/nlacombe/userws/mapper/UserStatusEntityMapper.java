package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.UserStatus;
import net.nlacombe.userws.entity.UserStatusEntity;
import net.nlacombe.userws.jparepository.UserStatusJpaRepository;
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
