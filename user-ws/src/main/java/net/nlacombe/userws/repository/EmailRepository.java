package net.nlacombe.userws.repository;

import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.entity.EmailEntity;
import net.nlacombe.userws.jparepository.EmailJpaRepository;
import net.nlacombe.userws.mapper.EmailEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class EmailRepository
{
	private final EmailJpaRepository emailJpaRepository;
	private final EmailEntityMapper emailEntityMapper;

    public EmailRepository(EmailJpaRepository emailJpaRepository, EmailEntityMapper emailEntityMapper) {
        this.emailJpaRepository = emailJpaRepository;
        this.emailEntityMapper = emailEntityMapper;
    }

    public Email saveEmail(Email email)
	{
		EmailEntity emailEntity = emailEntityMapper.mapToEntityType(email);
		emailEntity = emailJpaRepository.save(emailEntity);
		return emailEntityMapper.mapToDomainType(emailEntity);
	}
}
