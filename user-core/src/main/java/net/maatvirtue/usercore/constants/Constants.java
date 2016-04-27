package net.maatvirtue.usercore.constants;

public class Constants
{
	public static final String BASE_PACKAGE = "net.maatvirtue.usercore";
	public static final String ENTITY_PACKAGE = BASE_PACKAGE + ".entity";
	public static final String JPA_REPOSITORY_PACKAGE = BASE_PACKAGE + ".jparepository";
	public static final String SPRING_CONFIG_PACKAGE = BASE_PACKAGE + ".config.spring";

	//Web service
	public static final int WEB_SERVICE_MAJOR_VERSION = 1;

	//Database
	public static final int SALT_LEN = 20;
	public static final int DEFAULT_PASSWORD_LEN = 20;

	//Database Migration
	public static final String DB_MIGRATION_FOLDER = "db-migration";
	public static final String DB_MIGRATION_FILE_PREFIX = DB_MIGRATION_FOLDER + "_";
	public static final String DB_MIGRATION_FILE_DESCRIPTION_SEPARATOR = "_";

	//Other
	/**
	 * Security token lifespan in seconds. (0 == infinite)
	 */
	public static final int TOKEN_LIFESPAN = 5 * 60;

	/**
	 * Cleanup interval in seconds.
	 */
	public static final int CLEANUP_INTERVAL = 5 * 60;
}
