package net.maatvirtue.usercore.jparepository;

import net.maatvirtue.usercore.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailJpaRepository extends JpaRepository<EmailEntity, Integer>
{
	//
}
