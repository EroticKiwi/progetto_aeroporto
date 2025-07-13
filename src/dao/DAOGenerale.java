package dao;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface DAOGenerale{
	
	public void inserisciEntita(String query);
	public void modificaEntita(String query);
	public void eliminaEntita(String query);
	public ResultSet trovaEntita(String query);
	public ArrayList<ResultSet> trovaTutteEntita(String query);
}