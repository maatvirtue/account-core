package net.maatvirtue.usercore.mapper;

import net.maatvirtue.usercore.domain.Role;
import net.maatvirtue.usercore.entity.RoleEntity;
import net.maatvirtue.usercore.jparepository.RoleJpaRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RoleEntityMapper extends AbstractEntityMapper<Role, RoleEntity>
{
	@Inject
	private RoleJpaRepository roleJpaRepository;

	@Override
	public Role mapToDomainType(RoleEntity roleEntity, MapperCache cache)
	{
		return Role.getByName(roleEntity.getName());
	}

	@Override
	public RoleEntity mapToEntityType(Role role, MapperCache cache)
	{
		return roleJpaRepository.findByName(role.getName());
	}
}
