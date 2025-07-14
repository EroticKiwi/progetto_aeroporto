package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOGenerale;
import db.DBConnection;

import exceptions.*;

/*
 * NOTA SUI PREPARED STATEMENT:
 * Un PreparedStatement è un oggetto di cui necessitiamo per poter fare una query sul database dopo esserci
 * connessi. Questo tipo di statement in particolare evita il rischio di SQL Injection.
 * Un PS si prepara dall'oggetto Connection del DB, e poi si chiama il suo metodo "execute()" o "executeQuery()".
 * Lancia una SQLException se la query non è andata a buon fine.
 * 
 * NOTA SULLA CHIUSURA DEGLI STATEMENT E DEI RESULT SET:
 * La chiusura degli statement (e di conseguenza dei result set legati a quegli statement) avverrà nel metodo
 * del controller che chiama GestioneDati, nel caso di TrovaEntita(). Questo perchè chiudere lo statement E POI
 * ritornare un ResultSet derivante da quello statement potrerebbe il ResultSet ad essere chiuso a sua volta, restituendo
 * al controller un RS inutilizzabile.
 * */

public class GestioneDati implements DAOGenerale{
	
	private static GestioneDati instance;
	
	private GestioneDati() { }
	
	public static GestioneDati getInstance() {
		if(instance == null) {
			instance = new GestioneDati();
		}
		
		return instance;
	}

	public void inserisciEntita(String query) throws InserisciException{
		
		PreparedStatement statement = null;
		try {
			 statement = DBConnection.getInstance().connection.prepareStatement(query);
			 statement.execute(query);
		} catch (SQLException e) {
			throw new InserisciException();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void eliminaEntita(String query) throws EliminaException{
		
		PreparedStatement statement = null;
		try {
			 statement = DBConnection.getInstance().connection.prepareStatement(query);
			 statement.execute(query);
		} catch (SQLException e) {
			throw new EliminaException();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet trovaEntita(String query) throws TrovaException{
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = DBConnection.getInstance().connection.prepareStatement(query);
			rs = statement.executeQuery();
		} catch(SQLException e) {
			throw new TrovaException();
		}
		
		return rs;
	}

}
