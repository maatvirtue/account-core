package net.maatvirtue.usercore.mapper;

import net.maatvirtue.usercore.domain.ConfirmationEmail;
import net.maatvirtue.usercore.entity.ConfirmationEmailEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ConfirmationEmailEntityMapper extends AbstractEntityMapper<ConfirmationEmail, ConfirmationEmailEntity>
{
	@Inject
	private EmailEntityMapper emailEntityMapper;

	@Override
	public ConfirmationEmail mapToDomainType(ConfirmationEmailEntity confirmationEmailEntity, MapperCache cache)
	{
		if(cache.contains(confirmationEmailEntity))
			return (ConfirmationEmail)cache.get(confirmationEmailEntity);

		ConfirmationEmail confirmationEmail = new ConfirmationEmail();

		cache.put(confirmationEmailEntity, confirmationEmail);

		confirmationEmail.setConfirmationEmailId(confirmationEmailEntity.getConfirmationEmailId());
		confirmationEmail.setConfirmationCode(confirmationEmailEntity.getConfirmationCode());
		confirmationEmail.setEmail(emailEntityMapper.mapToDomainType(confirmationEmailEntity.getEmail(), cache));

		return confirmationEmail;
	}

	@Override
	public ConfirmationEmailEntity mapToEntityType(ConfirmationEmail confirmationEmail, MapperCache cache)
	{
		if(cache.contains(confirmationEmail))
			return (ConfirmationEmailEntity)cache.get(confirmationEmail);

		ConfirmationEmailEntity confirmationEmailEntity = new ConfirmationEmailEntity();

		cache.put(confirmationEmail, confirmationEmailEntity);

		confirmationEmailEntity.setConfirmationEmailId(confirmationEmail.getConfirmationEmailId());
		confirmationEmailEntity.setConfirmationCode(confirmationEmail.getConfirmationCode());
		confirmationEmailEntity.setEmail(emailEntityMapper.mapToEntityType(confirmationEmail.getEmail(), cache));

		return confirmationEmailEntity;
	}
}
