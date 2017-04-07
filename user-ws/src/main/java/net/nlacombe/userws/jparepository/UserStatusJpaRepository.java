package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusJpaRepository extends JpaRepository<UserStatusEntity, Integer>
{
	UserStatusEntity findByName(String name);
}
