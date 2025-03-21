package com.org.board_ui.persistences.configs.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgreSQL {
	private static final String URL = "jdbc:postgresql://localhost:5432/board_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
	
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		conn.setAutoCommit(false);
		return conn;
	}
}