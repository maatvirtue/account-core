package net.maatvirtue.usercore.mapper;

import net.maatvirtue.usercore.domain.Permission;
import net.maatvirtue.usercore.entity.PermissionEntity;
import net.maatvirtue.usercore.jparepository.PermissionJpaRepository;
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
