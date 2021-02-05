package net.nlacombe.userws.repository;

import net.nlacombe.userws.domain.security.StoredPasswordCredential;
import net.nlacombe.userws.entity.PasswordCredentialEntity;
import net.nlacombe.userws.jparepository.PasswordCredentialJpaRepository;
import net.nlacombe.userws.mapper.StoredPasswordCredentialEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class StoredPasswordRepository
{
	private final PasswordCredentialJpaRepository passwordCredentialJpaRepository;
	private final StoredPasswordCredentialEntityMapper storedPasswordCredentialEntityMapper;

    public StoredPasswordRepository(PasswordCredentialJpaRepository passwordCredentialJpaRepository, StoredPasswordCredentialEntityMapper storedPasswordCredentialEntityMapper) {
        this.passwordCredentialJpaRepository = passwordCredentialJpaRepository;
        this.storedPasswordCredentialEntityMapper = storedPasswordCredentialEntityMapper;
    }

    public StoredPasswordCredential findByUsername(String username)
	{
		PasswordCredentialEntity passwordCredentialEntity = passwordCredentialJpaRepository.findByUsername(username);

		return storedPasswordCredentialEntityMapper.mapToDomainType(passwordCredentialEntity);
	}

	public StoredPasswordCredential save(StoredPasswordCredential storedPasswordCredential)
	{
		PasswordCredentialEntity passwordCredentialEntity = storedPasswordCredentialEntityMapper.mapToEntityType(storedPasswordCredential);
		passwordCredentialEntity = passwordCredentialJpaRepository.save(passwordCredentialEntity);
		return  storedPasswordCredentialEntityMapper.mapToDomainType(passwordCredentialEntity);
	}
}
