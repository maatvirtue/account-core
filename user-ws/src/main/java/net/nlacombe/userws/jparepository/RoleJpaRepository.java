package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer>
{
	RoleEntity findByName(String name);
}
