package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.ConfirmationEmailEntity;
import net.nlacombe.userws.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationEmailJpaRepository extends JpaRepository<ConfirmationEmailEntity, Integer>
{
	void deleteByEmail(EmailEntity emailEntity);

	List<ConfirmationEmailEntity> findByConfirmationCode(String confirmationCode);

	ConfirmationEmailEntity findByEmail(EmailEntity email);
}
