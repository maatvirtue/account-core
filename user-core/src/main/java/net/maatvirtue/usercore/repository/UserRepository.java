package net.maatvirtue.usercore.repository;

import net.maatvirtue.usercore.domain.User;
import net.maatvirtue.usercore.domain.UserStatus;
import net.maatvirtue.usercore.entity.UserEntity;
import net.maatvirtue.usercore.entity.UserStatusEntity;
import net.maatvirtue.usercore.jparepository.UserJpaRepository;
import net.maatvirtue.usercore.mapper.UserEntityMapper;
import net.maatvirtue.usercore.mapper.UserStatusEntityMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class UserRepository
{
	@Inject
	private UserJpaRepository userJpaRepository;

	@Inject
	private UserEntityMapper userEntityMapper;

	@Inject
	private UserStatusEntityMapper userStatusEntityMapper;

	public User getUserById(int userId)
	{
		UserEntity userTable = userJpaRepository.findOne(userId);
		User user = userEntityMapper.mapToDomainType(userTable);

		return user;
	}

	public User saveUser(User user)
	{
		UserEntity userEntity = userEntityMapper.mapToEntityType(user);
		userEntity =  userJpaRepository.save(userEntity);
		return userEntityMapper.mapToDomainType(userEntity);
	}

	public void deleteUser(int userId)
	{
		userJpaRepository.delete(userId);
	}

	public boolean userWithUsernameAndStatusExist(String username, UserStatus status)
	{
		return getUserByUsernameAndStatus(username, status)!=null;
	}

	public User getUserByUsernameAndStatus(String username, UserStatus status)
	{
		UserStatusEntity statusEntity = userStatusEntityMapper.mapToEntityType(status);

		UserEntity userEntity = userJpaRepository.findByUsernameAndStatus(username, statusEntity);

		return userEntityMapper.mapToDomainType(userEntity);
	}
}
