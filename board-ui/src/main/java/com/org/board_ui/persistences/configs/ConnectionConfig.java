package com.org.board_ui.persistences.configs;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionConfig {
	Connection getConnection() throws SQLException;
}