package com.org.board_ui.configs.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.org.board_ui.configs.ConnectionConfig;

public class ConnectionMySQLConfigImpl  implements ConnectionConfig {
	private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

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