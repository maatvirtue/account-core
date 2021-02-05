package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.Permission;
import net.nlacombe.userws.entity.PermissionEntity;
import net.nlacombe.userws.jparepository.PermissionJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class PermissionEntityMapper extends AbstractEntityMapper<Permission, PermissionEntity>
{
	private final PermissionJpaRepository permissionJpaRepository;

    public PermissionEntityMapper(PermissionJpaRepository permissionJpaRepository) {
        this.permissionJpaRepository = permissionJpaRepository;
    }

    @Override
	public Permission mapToDomainType(PermissionEntity permissionEntity, MapperCache cache)
	{
		return Permission.getByName(permissionEntity.getName());
	}

	@Override
	public PermissionEntity mapToEntityType(Permission permission, MapperCache cache)
	{
		return permissionJpaRepository.findByName(permission.getName());
	}
}
