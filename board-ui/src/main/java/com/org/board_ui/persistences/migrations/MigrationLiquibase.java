package com.org.board_ui.persistences.migrations;

import java.sql.Connection;

import com.org.board_ui.persistences.configs.connections.ConnectionPostgreSQL;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class MigrationLiquibase {
	public void executeMigration() {
		Connection connection = null; 
		
		try {
			connection = new ConnectionPostgreSQL().getConnection();
			
			Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(connection));
			
			try (Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.xml",
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
}