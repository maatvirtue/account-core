package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.security.StoredPasswordCredential;
import net.nlacombe.userws.entity.PasswordCredentialEntity;
import org.springframework.stereotype.Component;

@Component
public class StoredPasswordCredentialEntityMapper extends AbstractEntityMapper<StoredPasswordCredential, PasswordCredentialEntity>
{
	private final UserEntityMapper userEntityMapper;

    public StoredPasswordCredentialEntityMapper(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    @Override
	public StoredPasswordCredential mapToDomainType(PasswordCredentialEntity passwordCredentialEntity, MapperCache cache)
	{
		if(cache.contains(passwordCredentialEntity))
			return (StoredPasswordCredential)cache.get(passwordCredentialEntity);

		StoredPasswordCredential storedPasswordCredential = new StoredPasswordCredential();

		cache.put(passwordCredentialEntity, storedPasswordCredential);

		storedPasswordCredential.setId(passwordCredentialEntity.getPasswordCredentialId());
		storedPasswordCredential.setPasswordHash(passwordCredentialEntity.getPasswordHash());
		storedPasswordCredential.setSalt(passwordCredentialEntity.getSalt());
		storedPasswordCredential.setUser(userEntityMapper.mapToDomainType(passwordCredentialEntity.getUser(), cache));

		return storedPasswordCredential;
	}

	@Override
	public PasswordCredentialEntity mapToEntityType(StoredPasswordCredential storedPasswordCredential, MapperCache cache)
	{
		if(cache.contains(storedPasswordCredential))
			return (PasswordCredentialEntity)cache.get(storedPasswordCredential);

		PasswordCredentialEntity passwordCredentialEntity = new PasswordCredentialEntity();

		cache.put(storedPasswordCredential, passwordCredentialEntity);

		passwordCredentialEntity.setPasswordCredentialId(storedPasswordCredential.getId());
		passwordCredentialEntity.setPasswordHash(storedPasswordCredential.getPasswordHash());
		passwordCredentialEntity.setSalt(storedPasswordCredential.getSalt());
		passwordCredentialEntity.setUser(userEntityMapper.mapToEntityType(storedPasswordCredential.getUser(), cache));

		return passwordCredentialEntity;
	}
}
