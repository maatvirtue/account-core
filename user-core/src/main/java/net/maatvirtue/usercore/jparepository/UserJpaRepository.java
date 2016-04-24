package net.maatvirtue.usercore.jparepository;

import net.maatvirtue.usercore.entity.UserEntity;
import net.maatvirtue.usercore.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer>
{
	UserEntity findByUsernameAndStatus(String username, UserStatusEntity status);
}
