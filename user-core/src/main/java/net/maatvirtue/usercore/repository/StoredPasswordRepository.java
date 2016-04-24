package net.maatvirtue.usercore.repository;

import net.maatvirtue.usercore.domain.security.StoredPasswordCredential;
import net.maatvirtue.usercore.entity.PasswordCredentialEntity;
import net.maatvirtue.usercore.jparepository.PasswordCredentialJpaRepository;
import net.maatvirtue.usercore.mapper.StoredPasswordCredentialEntityMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class StoredPasswordRepository
{
	@Inject
	private PasswordCredentialJpaRepository passwordCredentialJpaRepository;

	@Inject
	private StoredPasswordCredentialEntityMapper storedPasswordCredentialEntityMapper;

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
