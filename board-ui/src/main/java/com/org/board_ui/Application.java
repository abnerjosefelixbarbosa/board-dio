package com.org.board_ui;

import java.sql.Connection;

import com.org.board_ui.config.IConnection;
import com.org.board_ui.config.impl.ConnectionMySQLImpl;

public class Application {
	public static void main(String[] args) {
		IConnection connection = new ConnectionMySQLImpl();
		try {
			Connection conn = connection.getConnection();
			System.out.println("Conectado");
			connection.disconnect(conn);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}