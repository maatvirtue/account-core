package net.maatvirtue.usercore.jparepository;

import net.maatvirtue.usercore.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer>
{
	RoleEntity findByName(String name);
}
