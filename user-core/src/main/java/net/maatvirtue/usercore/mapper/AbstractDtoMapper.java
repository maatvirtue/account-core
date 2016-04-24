package net.maatvirtue.usercore.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDtoMapper<DomainType, DtoType> implements DtoMapper<DomainType, DtoType>
{
	public List<DomainType> mapListToDomainType(List<DtoType> dtos, MapperCache cache)
	{
		if(dtos==null)
			return null;

		List<DomainType> domainObjects = new ArrayList<>();

		for(DtoType dto: dtos)
			domainObjects.add(mapToDomainType(dto, cache));

		return domainObjects;
	}

	public List<DomainType> mapListToDomainType(List<DtoType> dtos)
	{
		if(dtos==null)
			return null;

		List<DomainType> domainObjects = new ArrayList<>();

		for(DtoType dto: dtos)
			domainObjects.add(mapToDomainType(dto));

		return domainObjects;
	}

	@Override
	public DomainType mapToDomainType(DtoType dto)
	{
		if(dto==null)
			return null;

		return mapToDomainType(dto, new MapperCache());
	}

	@Override
	public DtoType mapToDtoType(DomainType domain)
	{
		if(domain==null)
			return null;

		return mapToDtoType(domain, new MapperCache());
	}

	public List<DtoType> mapListToDtoType(List<DomainType> domainObjects, MapperCache cache)
	{
		if(domainObjects==null)
			return null;

		List<DtoType> dtos = new ArrayList<>();

		for(DomainType domainObject: domainObjects)
			dtos.add(mapToDtoType(domainObject, cache));

		return dtos;
	}

	public List<DtoType> mapListToDtoType(List<DomainType> domainObjects)
	{
		if(domainObjects==null)
			return null;

		List<DtoType> dtos = new ArrayList<>();

		for(DomainType domainObject: domainObjects)
			dtos.add(mapToDtoType(domainObject));

		return dtos;
	}
}
