package net.maatvirtue.usercore.jparepository;

import net.maatvirtue.usercore.entity.PasswordCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordCredentialJpaRepository extends JpaRepository<PasswordCredentialEntity, Integer>
{
	@Query("select pct from PasswordCredentialEntity pct where pct.user.username=:username")
	PasswordCredentialEntity findByUsername(@Param("username") String username);
}
