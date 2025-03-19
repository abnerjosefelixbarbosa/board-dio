package com.org.board_ui.configs;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionConfig {
	Connection getConnection() throws SQLException;
	void disconnect(Connection connection) throws SQLException;
}