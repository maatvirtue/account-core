package net.nlacombe.userws.config;

import net.nlacombe.userws.constants.Constants;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseContextConfig
{
	@Bean
	public Flyway flyway(javax.sql.DataSource datasource)
	{
		Flyway flyway = new Flyway();
		flyway.setDataSource(datasource);
		flyway.setLocations("classpath:" + Constants.DB_MIGRATION_FOLDER);
		flyway.setSqlMigrationPrefix(Constants.DB_MIGRATION_FILE_PREFIX);
		flyway.setSqlMigrationSeparator(Constants.DB_MIGRATION_FILE_DESCRIPTION_SEPARATOR);
		flyway.migrate();

		return flyway;
	}
}
