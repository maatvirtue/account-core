package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.Role;
import net.nlacombe.userws.entity.RoleEntity;
import net.nlacombe.userws.jparepository.RoleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapper extends AbstractEntityMapper<Role, RoleEntity>
{
	private final RoleJpaRepository roleJpaRepository;

    public RoleEntityMapper(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

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
