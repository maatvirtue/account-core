package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.entity.UserEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class UserEntityMapper extends AbstractEntityMapper<User, UserEntity>
{
	@Inject
	private UserStatusEntityMapper userStatusEntityMapper;

	@Inject
	private EmailEntityMapper emailEntityMapper;

	@Override
	public User mapToDomainType(UserEntity userEntity, MapperCache cache)
	{
		if(cache.contains(userEntity))
			return (User)cache.get(userEntity);

		User user = new User();

		cache.put(userEntity, user);

		user.setUserId(userEntity.getUserId());
		user.setUsername(userEntity.getUsername());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setAvatarUrl(userEntity.getAvatarUrl());
		user.setEmails(emailEntityMapper.mapListToDomainType(userEntity.getEmails(), cache));
		user.setStatus(userStatusEntityMapper.mapToDomainType(userEntity.getStatus(), cache));

		return user;
	}

	@Override
	public UserEntity mapToEntityType(User user, MapperCache cache)
	{
		if(cache.contains(user))
			return (UserEntity)cache.get(user);

		UserEntity userEntity = new UserEntity();

		cache.put(user, userEntity);

		userEntity.setUserId(user.getUserId());
		userEntity.setUsername(user.getUsername());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setAvatarUrl(user.getAvatarUrl());
		userEntity.setEmails(emailEntityMapper.mapListToEntityType(user.getEmails(), cache));
		userEntity.setStatus(userStatusEntityMapper.mapToEntityType(user.getStatus(), cache));

		return userEntity;
	}
}
