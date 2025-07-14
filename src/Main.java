import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import exceptions.TrovaException;
import model.GestioneDati;

public class Main {

	public static void main(String[] args) {
		
		String query = "SELECT * FROM Cliente";
		ResultSet rs = null;
		
		try {
			rs = GestioneDati.getInstance().trovaEntita(query);
		} catch (TrovaException e) {
			e.printStackTrace();
		}
		
		if(rs == null) {
			System.out.println("Vuoto!");
		}
		
		try {
			while(rs.next()) {
				System.out.println(rs.getInt("id") + "," + rs.getString("nome") + "," + rs.getString("cognome"));
			}
			rs.getStatement().close();
		} catch(SQLException e) {
			
		}
				
	}

}
