package net.nlacombe.userws.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityMapper<DomainType, EntityType> implements EntityMapper<DomainType, EntityType>
{
	public List<DomainType> mapListToDomainType(List<EntityType> entities)
	{
		if(entities==null)
			return null;

		List<DomainType> domainObjects = new ArrayList<>(entities.size());

		for(EntityType entity: entities)
			domainObjects.add(mapToDomainType(entity));

		return domainObjects;
	}

	public List<DomainType> mapListToDomainType(List<EntityType> entities, MapperCache cache)
	{
		if(entities==null)
			return null;

		List<DomainType> domainObjects = new ArrayList<>(entities.size());

		for(EntityType entity: entities)
			domainObjects.add(mapToDomainType(entity, cache));

		return domainObjects;
	}

	@Override
	public DomainType mapToDomainType(EntityType entity)
	{
		if(entity==null)
			return null;

		return mapToDomainType(entity, new MapperCache());
	}

	@Override
	public EntityType mapToEntityType(DomainType domain)
	{
		if(domain==null)
			return null;

		return mapToEntityType(domain, new MapperCache());
	}

	public List<EntityType> mapListToEntityType(List<DomainType> domainObjects)
	{
		if(domainObjects==null)
			return null;

		List<EntityType> entities = new ArrayList<>(domainObjects.size());

		for(DomainType domainObject: domainObjects)
			entities.add(mapToEntityType(domainObject));

		return entities;
	}

	public List<EntityType> mapListToEntityType(List<DomainType> domainObjects, MapperCache cache)
	{
		if(domainObjects==null)
			return null;

		List<EntityType> entities = new ArrayList<>(domainObjects.size());

		for(DomainType domainObject: domainObjects)
			entities.add(mapToEntityType(domainObject, cache));

		return entities;
	}
}
