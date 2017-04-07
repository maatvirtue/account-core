package net.nlacombe.userws.repository;

import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.entity.EmailEntity;
import net.nlacombe.userws.jparepository.EmailJpaRepository;
import net.nlacombe.userws.mapper.EmailEntityMapper;
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
