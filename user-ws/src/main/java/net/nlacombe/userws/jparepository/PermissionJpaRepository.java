package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, Integer>
{
	PermissionEntity findByName(String name);
}
