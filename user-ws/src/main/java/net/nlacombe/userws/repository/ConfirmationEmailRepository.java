package net.nlacombe.userws.repository;

import net.nlacombe.userws.domain.ConfirmationEmail;
import net.nlacombe.userws.domain.Email;
import net.nlacombe.userws.entity.ConfirmationEmailEntity;
import net.nlacombe.userws.entity.EmailEntity;
import net.nlacombe.userws.jparepository.ConfirmationEmailJpaRepository;
import net.nlacombe.userws.mapper.ConfirmationEmailEntityMapper;
import net.nlacombe.userws.mapper.EmailEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfirmationEmailRepository
{
    private final ConfirmationEmailJpaRepository confirmationEmailJpaRepository;
    private final ConfirmationEmailEntityMapper confirmationEmailEntityMapper;
    private final EmailEntityMapper emailEntityMapper;

    public ConfirmationEmailRepository(ConfirmationEmailJpaRepository confirmationEmailJpaRepository, ConfirmationEmailEntityMapper confirmationEmailEntityMapper, EmailEntityMapper emailEntityMapper) {
        this.confirmationEmailJpaRepository = confirmationEmailJpaRepository;
        this.confirmationEmailEntityMapper = confirmationEmailEntityMapper;
        this.emailEntityMapper = emailEntityMapper;
    }

	public List<ConfirmationEmail> findByConfirmationCode(String confirmationCode)
	{
		List<ConfirmationEmailEntity> confirmationEmailEntities = confirmationEmailJpaRepository.findByConfirmationCode(confirmationCode);

		return confirmationEmailEntityMapper.mapListToDomainType(confirmationEmailEntities);
	}

	public ConfirmationEmail findByEmail(Email email)
	{
		EmailEntity emailEntity = emailEntityMapper.mapToEntityType(email);

		ConfirmationEmailEntity confirmationEmailEntity = confirmationEmailJpaRepository.findByEmail(emailEntity);

		return confirmationEmailEntityMapper.mapToDomainType(confirmationEmailEntity);
	}

	public void deleteByEmail(Email email)
	{
		EmailEntity emailEntity = emailEntityMapper.mapToEntityType(email);

		confirmationEmailJpaRepository.deleteByEmail(emailEntity);
	}

	public ConfirmationEmail save(ConfirmationEmail confirmationEmail)
	{
		ConfirmationEmailEntity confirmationEmailEntity = confirmationEmailEntityMapper.mapToEntityType(confirmationEmail);
		confirmationEmailEntity = confirmationEmailJpaRepository.save(confirmationEmailEntity);
		return confirmationEmailEntityMapper.mapToDomainType(confirmationEmailEntity);
	}
}
