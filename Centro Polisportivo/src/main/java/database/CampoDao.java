package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Campo;

public class CampoDao {

	private static final String INSERT_CAMPO = "INSERT INTO campetti" + " (sport, prezzo, oraInizioDisp, oraFineDisp) VALUES "
			+ " (?, ?, ?, ?);";
	private static final String SELECT_CAMPO_BY_ID = "select * from campetti where id = ?";
	private static final String SELECT_CAMPI_BY_SPORT = "select * from campetti where sport = ?";
	private static final String SELECT_ALL_CAMPI = "select * from campetti";
	
	public CampoDao() {}
	
	
	protected static Connection getConnection() {
		Connection connection = null; 
		connection = MyConnection.getConnection(); 
		
		return connection;
		
	}
	
	public static void insertCampo(Campo c) {
		Connection conn = getConnection();
		try {
			PreparedStatement pstm = conn.prepareStatement(INSERT_CAMPO);
			pstm.setString(1, c.getSport());
			pstm.setInt(2, c.getPrezzo());
			pstm.setInt(3, c.getOraInizioDisponibilità());
			pstm.setInt(4, c.getOraFineDisponibilità());
			pstm.executeUpdate();
			System.out.println("Inserimento nel database...query effettuata:");
			System.out.println(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//select campo by id
	public static Campo selectCampo (int id) {
		Campo c = null;
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_CAMPO_BY_ID);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				String sport = rs.getString("sport");
				int prezzo = rs.getInt("prezzo");
				int oraInizioDisp = rs.getInt("oraInizioDisp");
				int oraFineDisp = rs.getInt("oraFineDisp");
				c = new Campo(id, sport, prezzo, oraInizioDisp, oraFineDisp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return c;
	}
		
	
	
	//select campi by sport
	public static List<Campo> selectCampo (String sport) {
		List<Campo> campi = new ArrayList<Campo>();
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_CAMPI_BY_SPORT);
			pstm.setString(1, sport);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				int id = rs.getInt("id");
				int prezzo = rs.getInt("prezzo");int oraInizioDisp = rs.getInt("oraInizioDisp");
				int oraFineDisp = rs.getInt("oraFineDisp");
				Campo c = new Campo(id, sport, prezzo, oraInizioDisp, oraFineDisp);
				campi.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		for (Campo c: campi)
			System.out.println(c);
		
		return campi;
	}
	
	
	public static List<Campo> selectTuttiCampi () {
		List<Campo> campi = new ArrayList<Campo>();
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_ALL_CAMPI);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				int id = rs.getInt("id");
				String sport = rs.getString("sport");
				int prezzo = rs.getInt("prezzo");
				int oraInizioDisp = rs.getInt("oraInizioDisp");
				int oraFineDisp = rs.getInt("oraFineDisp");
				Campo c = new Campo(id, sport, prezzo, oraInizioDisp, oraFineDisp);
				campi.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		for (Campo c: campi)
			System.out.println(c);
		
		return campi;
	}
	
	
	public static void main(String[] args) {
		Campo c = new Campo("calcio", 50, 15, 24);
		CampoDao.insertCampo(c);
		//System.out.println(CampoDao.selectCampo(2));
		//CampoDao.selectCampo("calcio");
		//CampoDao.selectTuttiCampi();
	}
}