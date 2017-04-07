package net.nlacombe.userws.mapper;

public interface DtoMapper<DomainType, DtoType>
{
	DomainType mapToDomainType(DtoType dto);
	DomainType mapToDomainType(DtoType dto, MapperCache cache);
	DtoType mapToDtoType(DomainType domain);
	DtoType mapToDtoType(DomainType domain, MapperCache cache);
}
