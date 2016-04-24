package net.maatvirtue.usercore.jparepository;

import net.maatvirtue.usercore.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusJpaRepository extends JpaRepository<UserStatusEntity, Integer>
{
	UserStatusEntity findByName(String name);
}
