import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataController;
import db.DBConnection;
import exceptions.TrovaException;
import model.DataModel;

public class Main {

	public static void main(String[] args) {
		
		DataController.getInstance().trovaAeroporto(1);

	}

}
