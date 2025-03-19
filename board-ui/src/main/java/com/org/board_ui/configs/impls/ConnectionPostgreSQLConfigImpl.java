package com.org.board_ui.configs.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.org.board_ui.configs.ConnectionConfig;

public class ConnectionPostgreSQLConfigImpl implements ConnectionConfig {
	private static final String URL = "jdbc:postgresql://localhost:5432/board_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
	
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		conn.setAutoCommit(false);
		return conn;
	}

	public void disconnect(Connection connection) throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
			System.out.println("Conexão fechada.");
		}
	}
}