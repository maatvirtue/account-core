package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.UserEntity;
import net.nlacombe.userws.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer>
{
	UserEntity findByUsernameAndStatus(String username, UserStatusEntity status);
}
