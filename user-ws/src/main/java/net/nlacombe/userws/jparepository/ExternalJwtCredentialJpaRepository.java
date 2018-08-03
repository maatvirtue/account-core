package net.nlacombe.userws.jparepository;

import net.nlacombe.userws.entity.ExternalJwtCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalJwtCredentialJpaRepository extends JpaRepository<ExternalJwtCredentialEntity, Integer>
{
	Optional<ExternalJwtCredentialEntity> findByIssuerAndSubject(String issuer, String subject);
}
