package com.org.board_ui.config.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.org.board_ui.config.IConnection;

public class ConnectionMySQLImpl  implements IConnection {
	private static final String URL = "jdbc:mysql://metro.proxy.rlwy.net:13738/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "vUvWZsvShgSOQWHYZbPztfsPSujJpgpp";

	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		conn.setAutoCommit(false);
		executeSQLScript(conn);
		return conn;	
	}

	@Override
	public void disconnect(Connection connection) throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
			System.out.println("Conexão fechada.");
		}
	}
	
	private void executeSQLScript(Connection connection) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("script.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Statement stmt = connection.createStatement()) {

            if (inputStream == null) {
                System.err.println("Arquivo script.sql não encontrado!");
                return;
            }

            StringBuilder sql = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                sql.append(linha).append("\n");
            }
        
            stmt.execute(sql.toString());
            // String sqlDelete  = "DROP TABLE IF EXISTS usuarios";
            // stmt.executeUpdate(sqlDelete);
            System.out.println("Script SQL executado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao executar script SQL.");
        }
    }

}