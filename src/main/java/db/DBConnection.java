package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection instance;
	
	public Connection connection; // oggetto di tipo Connection per connettersi al database
	public ResultSet rs; // oggetto di tipo ResultSet conserva i risultati delle query "SELECT"
		
	private DBConnection() throws SQLException {
		try {
			Connect();
		} catch (SQLException e) {
			throw e; // viene propagata a chiunque tenti di ottenere un istanza DBConnection
		}
	}
	
	public static DBConnection getInstance() throws SQLException { // Restituisce un istanza DBConnection
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
