package net.maatvirtue.usercore.jparepository;

import net.maatvirtue.usercore.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, Integer>
{
	PermissionEntity findByName(String name);
}
