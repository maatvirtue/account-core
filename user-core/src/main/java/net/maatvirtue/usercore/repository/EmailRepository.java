package net.maatvirtue.usercore.repository;

import net.maatvirtue.usercore.domain.Email;
import net.maatvirtue.usercore.entity.EmailEntity;
import net.maatvirtue.usercore.jparepository.EmailJpaRepository;
import net.maatvirtue.usercore.mapper.EmailEntityMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class EmailRepository
{
	@Inject
	private EmailJpaRepository emailJpaRepository;

	@Inject
	private EmailEntityMapper emailEntityMapper;

	public Email saveEmail(Email email)
	{
		EmailEntity emailEntity = emailEntityMapper.mapToEntityType(email);
		emailEntity = emailJpaRepository.save(emailEntity);
		return emailEntityMapper.mapToDomainType(emailEntity);
	}
}
