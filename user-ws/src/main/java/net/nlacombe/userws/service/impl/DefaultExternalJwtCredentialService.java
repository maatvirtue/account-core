package net.nlacombe.userws.service.impl;

import net.nlacombe.userws.domain.User;
import net.nlacombe.userws.entity.ExternalJwtCredentialEntity;
import net.nlacombe.userws.jparepository.ExternalJwtCredentialJpaRepository;
import net.nlacombe.userws.mapper.UserEntityMapper;
import net.nlacombe.userws.service.ExternalJwtCredentialService;
import org.springframework.stereotype.Service;

@Service
public class DefaultExternalJwtCredentialService implements ExternalJwtCredentialService
{
	private final ExternalJwtCredentialJpaRepository externalJwtCredentialJpaRepository;
	private final UserEntityMapper userEntityMapper;

	public DefaultExternalJwtCredentialService(ExternalJwtCredentialJpaRepository externalJwtCredentialJpaRepository, UserEntityMapper userEntityMapper)
	{
		this.externalJwtCredentialJpaRepository = externalJwtCredentialJpaRepository;
		this.userEntityMapper = userEntityMapper;
	}

	@Override
	public User getUser(String issuer, String subject)
	{
		return externalJwtCredentialJpaRepository.findByIssuerAndSubject(issuer, subject)
				.map(externalJwtCredentialEntity -> userEntityMapper.mapToDomainType(externalJwtCredentialEntity.getUser()))
				.orElse(null);
	}

	@Override
	public void createExternalJwtCredential(String issuer, String subject, User user)
	{
		ExternalJwtCredentialEntity externalJwtCredentialEntity = new ExternalJwtCredentialEntity();
		externalJwtCredentialEntity.setIssuer(issuer);
		externalJwtCredentialEntity.setSubject(subject);
		externalJwtCredentialEntity.setUser(userEntityMapper.mapToEntityType(user));

		externalJwtCredentialJpaRepository.save(externalJwtCredentialEntity);
	}
}
