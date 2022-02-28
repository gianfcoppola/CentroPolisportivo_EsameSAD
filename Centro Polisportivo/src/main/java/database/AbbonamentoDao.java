package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Abbonamento;
import model.Corso;
import model.Utente;

public class AbbonamentoDao {

	private static final String INSERT_NEW_ABBONAMENTO = "INSERT INTO abbonamenti" + " (utente, corso, inizioAbbonamento, fineAbbonamento, prezzoTotale) VALUES "
			+ " (?, ?, ?, ?, ?);";
	
	private static final String SELECT_ABBONAMENTO_BY_ID = "select * from abbonamenti where id = ?";
	private static final String SELECT_ABBONAMENTO_BY_UTENTE = "select * from abbonamenti where utente = ?";
	private static final String SELECT_ALL_ABBONAMENTI = "select * from abbonamenti";
	
	private static final String DELETE_ABBONAMENTO_BY_ID = "DELETE FROM abbonamenti WHERE id = ?";
	private static final String DELETE_ABBONAMENTI_BY_CORSO = "DELETE FROM abbonamenti where corso = ?";
	
	public AbbonamentoDao() {
		// TODO Auto-generated constructor stub
	}

	protected static Connection getConnection() {
		Connection connection = null; 
		connection = MyConnection.getConnection(); 
		
		return connection;
		
	}
	
	
	//insert abbonamento
		public static boolean insertAbbonamento(Abbonamento a) {
			Connection conn = getConnection();
			boolean ok = false;
			try {
				PreparedStatement pstm = conn.prepareStatement(INSERT_NEW_ABBONAMENTO);
				pstm.setInt(1, a.getUtente().getId());
				pstm.setInt(2, a.getCorso().getId());
				pstm.setString(3, a.getInizioAbbonamento());
				pstm.setString(4, a.getFineAbbonamento());
				pstm.setInt(5, a.getPrezzoTotale());

				pstm.executeUpdate();
				System.out.println("Inserimento nel database...query effettuata:");
				System.out.println(pstm);
				
				CorsoDao.updateCorso(a.getCorso().getPostiDisponibili(), a.getCorso().getId());
				ok = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		
			return ok;
		}
		
		//select abbonamento by id
		public static Abbonamento selectAbbonamento (int id) {
			Abbonamento a = null;
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_ABBONAMENTO_BY_ID);
				pstm.setInt(1, id);
		
				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					Utente utente = UtenteDao.selectUser(rs.getInt("utente"));
					Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
					String inizioAbbonamento = rs.getString("inizioAbbonamento");
					String fineAbbonamento = rs.getString("fineAbbonamento");
					int prezzoTotale = rs.getInt("prezzoTotale");
					a = new Abbonamento (id, utente, corso, inizioAbbonamento, fineAbbonamento, prezzoTotale);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return a;
		}
		
		
		//select abbonamento by utente
				public static List<Abbonamento> selectAbbonamentiUtente (int idUtente) {
					List<Abbonamento> listaAbbonamenti = new ArrayList<Abbonamento>();
					Connection conn = getConnection();
					PreparedStatement pstm;
					try {
						pstm = conn.prepareStatement(SELECT_ABBONAMENTO_BY_UTENTE);
						pstm.setInt(1, idUtente);
				
						ResultSet rs = pstm.executeQuery();
						System.out.println("\nRecupero dal database...query effettuata:");
						System.out.println(pstm);
						while(rs.next()) {
							int id = rs.getInt("id");
							Utente utente = UtenteDao.selectUser(rs.getInt("utente"));
							Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
							String inizioAbbonamento = rs.getString("inizioAbbonamento");
							String fineAbbonamento = rs.getString("fineAbbonamento");
							int prezzoTotale = rs.getInt("prezzoTotale");
							Abbonamento a = new Abbonamento (id, utente, corso, inizioAbbonamento, fineAbbonamento, prezzoTotale);
							listaAbbonamenti.add(a);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					return listaAbbonamenti;
				}
		
		//select all abbonamenti
		public static List<Abbonamento> selectAbbonamenti () {
			List<Abbonamento> abbonamenti = new ArrayList<Abbonamento>();
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_ALL_ABBONAMENTI);
		
				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					Utente utente = UtenteDao.selectUser(rs.getInt("utente"));
					Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
					String inizioAbbonamento = rs.getString("inizioAbbonamento");
					String fineAbbonamento = rs.getString("fineAbbonamento");
					int prezzoTotale = rs.getInt("prezzoTotale");
					Abbonamento a = new Abbonamento (id, utente, corso, inizioAbbonamento, fineAbbonamento, prezzoTotale);
					abbonamenti.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return abbonamenti;
		}
	
		
		//delete abbonamento
		public static boolean deleteAbbonamento(int id) {
			Connection conn = getConnection();
			PreparedStatement pstm;
			boolean ok = false;
			try {
				pstm = conn.prepareStatement(DELETE_ABBONAMENTO_BY_ID);
				pstm.setInt(1, id);
				pstm.executeUpdate();
				System.out.println("\nQuery effettuata:");
				System.out.println(pstm);
				
				
				ok = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ok;

		}
		
		//delete abbonamenti by corso
				public static boolean deleteAbbonamentiCorso(int idCorso) {
					Connection conn = getConnection();
					PreparedStatement pstm;
					boolean ok = false;
					try {
						pstm = conn.prepareStatement(DELETE_ABBONAMENTI_BY_CORSO);
						pstm.setInt(1, idCorso);
						pstm.executeUpdate();
						System.out.println("\nQuery effettuata:");
						System.out.println(pstm);
						
						
						ok = true;

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ok;

				}
}