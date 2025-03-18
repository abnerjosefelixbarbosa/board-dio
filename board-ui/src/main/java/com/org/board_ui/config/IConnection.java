package com.org.board_ui.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnection {
	Connection getConnection() throws SQLException;
	void disconnect(Connection connection) throws SQLException;
}