package net.nlacombe.userws.mapper;

import net.nlacombe.userws.domain.Permission;
import net.nlacombe.userws.entity.PermissionEntity;
import net.nlacombe.userws.jparepository.PermissionJpaRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PermissionEntityMapper extends AbstractEntityMapper<Permission, PermissionEntity>
{
	@Inject
	private PermissionJpaRepository permissionJpaRepository;

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
