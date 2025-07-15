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
		
	private DBConnection() {
		Connect();
	}
	
	public static DBConnection getInstance() {
		if(instance == null) {
			instance = new DBConnection();
		}
		
		return instance;
	}
	
	public void Connect() {
		String url = "jdbc:postgresql://localhost:5432/aeroporto";
		String username = "postgres";
		String password = "1234"; // 1234
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			System.out.println("Connessione avvenuta!");
		} catch (SQLException e) {
			System.out.println("errore");
			e.getMessage();
			e.printStackTrace();
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
