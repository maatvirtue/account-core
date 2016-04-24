package net.maatvirtue.usercore.mapper;

import java.util.HashMap;
import java.util.Map;

public class MapperCache
{
	private Map<Object, Object> objectCache;

	public MapperCache()
	{
		objectCache = new HashMap<>();
	}

	protected void resetCache()
	{
		objectCache.clear();
	}

	protected void put(Object sourceObject, Object mappedObject)
	{
		objectCache.put(sourceObject, mappedObject);
	}

	protected boolean contains(Object sourceObject)
	{
		return objectCache.containsKey(sourceObject);
	}

	protected Object get(Object sourceObject)
	{
		return objectCache.get(sourceObject);
	}
}
