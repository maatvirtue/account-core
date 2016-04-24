package net.maatvirtue.usercore.repository;

import net.maatvirtue.usercore.domain.ConfirmationEmail;
import net.maatvirtue.usercore.domain.Email;
import net.maatvirtue.usercore.entity.ConfirmationEmailEntity;
import net.maatvirtue.usercore.entity.EmailEntity;
import net.maatvirtue.usercore.jparepository.ConfirmationEmailJpaRepository;
import net.maatvirtue.usercore.mapper.ConfirmationEmailEntityMapper;
import net.maatvirtue.usercore.mapper.EmailEntityMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class ConfirmationEmailRepository
{
	@Inject
	private ConfirmationEmailJpaRepository confirmationEmailJpaRepository;

	@Inject
	private ConfirmationEmailEntityMapper confirmationEmailEntityMapper;

	@Inject
	private EmailEntityMapper emailEntityMapper;

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
