package net.maatvirtue.accountcore.config.spring;

import net.maatvirtue.accountcore.constants.Constants;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = Constants.JPA_REPOSITORY_PACKAGE)
@EnableTransactionManagement
public class DatabaseContextConfig
{
	@Value("${db.driverClass}")
	private String dbDriverClass;

	@Value("${db.jdbcUrl}")
	private String dbJdbcUrl;

	@Value("${db.username}")
	private String dbUsername;

	@Value("${db.password}")
	private String dbPassword;

	@Value("${db.dialect}")
	private String dbDialect;

	@Value("${db.showSql}")
	private boolean dbShowSql;

	@Bean
	public DataSource datasource()
	{
		DataSource datasource = new DataSource();
		datasource.setDriverClassName(dbDriverClass);
		datasource.setUrl(dbJdbcUrl);
		datasource.setUsername(dbUsername);
		datasource.setPassword(dbPassword);
		datasource.setValidationQuery("select 1");
		datasource.setTestOnBorrow(true);
		datasource.setTestWhileIdle(true);
		datasource.setRemoveAbandoned(true);
		datasource.setDefaultAutoCommit(false);
		datasource.setCommitOnReturn(false);

		return datasource;
	}

	@Bean
	public Flyway flyway(javax.sql.DataSource datasource)
	{
		//Perform DB migration

		Flyway flyway = new Flyway();
		flyway.setDataSource(datasource);
		flyway.setLocations("classpath:" + Constants.DB_MIGRATION_FOLDER);
		flyway.setSqlMigrationPrefix(Constants.DB_MIGRATION_FILE_PREFIX);
		flyway.setSqlMigrationSeparator(Constants.DB_MIGRATION_FILE_DESCRIPTION_SEPARATOR);
		flyway.migrate();

		return flyway;
	}

	/**
	 * Parameter flyway exists just to ensure flyway executes before hibernate.
	 */
	@Bean
	public HibernateJpaVendorAdapter vendorAdapter(Flyway flyway)
	{
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(dbDialect);
		vendorAdapter.setShowSql(dbShowSql);

		return vendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource, HibernateJpaVendorAdapter vendorAdapter)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setPackagesToScan(Constants.ENTITY_PACKAGE);
		entityManagerFactory.setDataSource(datasource);
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}
}
