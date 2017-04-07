package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailJpaRepository extends JpaRepository<EmailEntity, Integer>
{
	//
}
