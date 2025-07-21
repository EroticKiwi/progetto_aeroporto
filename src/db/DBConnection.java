package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static DBConnection instance;
	
	public Connection connection;
	public Statement statement;
	public ResultSet rs;
		
	private DBConnection() throws SQLException {
		try {
			Connect();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static DBConnection getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBConnection();
		}
		
		return instance;
	}
	
	public void Connect() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/aeroporto";
		String username = "postgres";
		String password = "1234"; // 1234
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connessione avvenuta!");
		} catch (SQLException e) {
			throw e;
			//e.printStackTrace();
		}
	}
	
	public void Disconnect() {
		
		if(connection == null) {
			return;
		}
		
		try {
			connection.close();
			System.out.println("Connessione chiusa!");
		} catch(SQLException e) {
			e.getMessage();
		}
	}
}
