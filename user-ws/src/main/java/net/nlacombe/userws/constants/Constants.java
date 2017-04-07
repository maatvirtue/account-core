package net.nlacombe.userws.constants;

public class Constants
{
	//Database
	public static final int SALT_LEN = 20;
	public static final int DEFAULT_PASSWORD_LEN = 20;

	//Database Migration
	public static final String DB_MIGRATION_FOLDER = "db-migration";
	public static final String DB_MIGRATION_FILE_PREFIX = DB_MIGRATION_FOLDER + "_";
	public static final String DB_MIGRATION_FILE_DESCRIPTION_SEPARATOR = "_";
}
