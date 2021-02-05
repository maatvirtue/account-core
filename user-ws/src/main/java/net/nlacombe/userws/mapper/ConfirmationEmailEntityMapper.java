package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.ConfirmationEmail;
import net.nlacombe.userws.entity.ConfirmationEmailEntity;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationEmailEntityMapper extends AbstractEntityMapper<ConfirmationEmail, ConfirmationEmailEntity>
{
	private final EmailEntityMapper emailEntityMapper;

    public ConfirmationEmailEntityMapper(EmailEntityMapper emailEntityMapper) {
        this.emailEntityMapper = emailEntityMapper;
    }

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
