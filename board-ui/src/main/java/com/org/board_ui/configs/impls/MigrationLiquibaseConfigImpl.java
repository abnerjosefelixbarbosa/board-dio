package com.org.board_ui.configs.impls;

import java.sql.Connection;

import com.org.board_ui.configs.ConnectionConfig;
import com.org.board_ui.configs.MigrationConfig;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class MigrationLiquibaseConfigImpl implements MigrationConfig {
	public void executeMigration() {
		Connection connection = null;
		ConnectionConfig connectionConfig = new ConnectionPostgreSQLConfigImpl();
		
		try {
			connection = connectionConfig.getConnection();
			
			Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(connection));
			
			try (Liquibase liquibase = new Liquibase("/db/changelog/db.changelog-master.yml",
					new ClassLoaderResourceAccessor(), database)) {
				liquibase.update(new Contexts(), new LabelExpression());
			}
			
			System.out.println("Migrações executadas com sucesso!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		MigrationConfig config = new MigrationLiquibaseConfigImpl();
		config.executeMigration();
	}
}