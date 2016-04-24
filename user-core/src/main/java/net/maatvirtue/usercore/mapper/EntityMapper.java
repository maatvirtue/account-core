package net.maatvirtue.usercore.mapper;

public interface EntityMapper<DomainType, EntityType>
{
	DomainType mapToDomainType(EntityType entity);
	DomainType mapToDomainType(EntityType entity, MapperCache cache);
	EntityType mapToEntityType(DomainType domain);
	EntityType mapToEntityType(DomainType domain, MapperCache cache);
}
