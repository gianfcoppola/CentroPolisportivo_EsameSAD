package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Istruttore;
import model.Utente;

public class IstruttoreDao {

	private static final String INSERT_NEW_ISTRUTTORE = "INSERT INTO istruttori" + " (id, sport, descrizione) VALUES "
			+ " (?, ?, ?);";
	
	private static final String SELECT_ISTR_BY_ID = "select * from istruttori where id = ?";
	private static final String SELECT_ISTR_BY_SPORT = "select * from istruttori where sport = ?";
	private static final String SELECT_ALL_ISTR = "select * from istruttori";
	private static final String SELECT_ISTR_BY_USERNAME = "select * from utenti where username = ?";
	
	private static final String UPDATE_ISTRUTTORE = "UPDATE istruttori SET sport = ?, descrizione = ? WHERE id = ?";

	private static final String DELETE_ISTRUTTORE = "DELETE FROM istruttori WHERE id = ?";
	
	public IstruttoreDao() {
		// TODO Auto-generated constructor stub
	}
	
	protected static Connection getConnection() {
		Connection connection = null; 
		connection = MyConnection.getConnection(); 
		
		return connection;
		
	}
	
	//insert users
	public static void insertIstr(Istruttore istruttore) {
		Connection conn = getConnection();
		try {
			//creo un nuovo istruttore, che sarà anche un nuovo utente dell'applicazione, per cui devo inserirlo anche nella tabella utenti...
			String username = istruttore.getUsername();
			String password = istruttore.getPassword();
			String nome = istruttore.getNome();
			String cognome = istruttore.getCognome();
			String dataNascita = istruttore.getDataNascita();
			String email = istruttore.getEmail();
			String cellulare = istruttore.getCellulare();
			String ruolo = istruttore.getRuolo();
			Utente u = new Utente(username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
			//UtenteDao.insertUser(u);
			u = UtenteDao.selectUser(username, password);
			
			PreparedStatement pstm = conn.prepareStatement(INSERT_NEW_ISTRUTTORE);
			pstm.setInt(1, u.getId());
			pstm.setString(2, istruttore.getSport());
			pstm.setString(3, istruttore.getDescrizione());

			pstm.executeUpdate();
			System.out.println("Inserimento nel database...query effettuata:");
			System.out.println(pstm);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//select istruttore by id
	public static Istruttore selectIstruttore (int id) {
		Istruttore i = null;
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_ISTR_BY_ID);
			pstm.setInt(1, id);
	
			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				Utente u = UtenteDao.selectUser(id);
				String sport = rs.getString("sport");
				String descrizione = rs.getString("descrizione");
				
				String username = u.getUsername();
				String password = u.getPassword();
				String nome = u.getNome();
				String cognome = u.getCognome();
				String dataNascita = u.getDataNascita();
				String email = u.getEmail();
				String cellulare = u.getCellulare();
				String ruolo = u.getRuolo();
				i = new Istruttore(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo, sport, descrizione);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;	
	}
	
	//select user by username
			public static Istruttore selectIstruttore (String username) {
				
				Istruttore i = null;
				Connection conn = getConnection();
				PreparedStatement pstm;
				try {
					pstm = conn.prepareStatement(SELECT_ISTR_BY_USERNAME);
					pstm.setString(1, username);

					ResultSet rs = pstm.executeQuery();
					System.out.println("\nRecupero dal database...query effettuata:");
					System.out.println(pstm);
					while(rs.next()) {
						int id = rs.getInt("id");
					
						
						i = IstruttoreDao.selectIstruttore(id);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return i;
				
				
			}
	
	
	//select istruttori by sport
		public static List<Istruttore> selectIstruttori (String sport) {
			List<Istruttore> istruttori = new ArrayList<Istruttore>();
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_ISTR_BY_SPORT);
				pstm.setString(1, sport);
		
				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					Utente u = UtenteDao.selectUser(id);
					String descrizione = rs.getString("descrizione");
					
					String username = u.getUsername();
					String password = u.getPassword();
					String nome = u.getNome();
					String cognome = u.getCognome();
					String dataNascita = u.getDataNascita();
					String email = u.getEmail();
					String cellulare = u.getCellulare();
					String ruolo = u.getRuolo();
					Istruttore i = new Istruttore(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo, sport, descrizione);
					istruttori.add(i);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return istruttori;	
		}
	
	
		//select all istruttori
				public static List<Istruttore> selectIstruttori () {
					List<Istruttore> istruttori = new ArrayList<Istruttore>();
					Connection conn = getConnection();
					PreparedStatement pstm;
					try {
						pstm = conn.prepareStatement(SELECT_ALL_ISTR);
				
						ResultSet rs = pstm.executeQuery();
						System.out.println("\nRecupero dal database...query effettuata:");
						System.out.println(pstm);
						while(rs.next()) {
							int id = rs.getInt("id");
							Utente u = UtenteDao.selectUser(id);
							String sport = rs.getString("sport");
							String descrizione = rs.getString("descrizione");
							
							String username = u.getUsername();
							String password = u.getPassword();
							String nome = u.getNome();
							String cognome = u.getCognome();
							String dataNascita = u.getDataNascita();
							String email = u.getEmail();
							String cellulare = u.getCellulare();
							String ruolo = u.getRuolo();
							Istruttore i = new Istruttore(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo, sport, descrizione);
							istruttori.add(i);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return istruttori;	
				}
	

				//update dati istruttore
				public static boolean updateIstruttore (String sport, String descrizione, int id) {
					Connection conn = getConnection();
					PreparedStatement pstm;
					boolean ok = false;
					try {
						pstm = conn.prepareStatement(UPDATE_ISTRUTTORE);
						pstm.setString(1, sport);
						pstm.setString(2, descrizione);
						pstm.setInt(3, id);

						pstm.executeUpdate();
						System.out.println("\nModifica effettuata:");
						System.out.println(pstm);
						ok = true;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return ok;


				}
				
				

				//delete istruttore
				public static boolean deleteIstruttore(int id) {
					Connection conn = getConnection();
					PreparedStatement pstm;
					boolean ok = false;
					try {
						pstm = conn.prepareStatement(DELETE_ISTRUTTORE);
						pstm.setInt(1, id);
						pstm.executeUpdate();
						System.out.println("\nQuery effettuata:");
						System.out.println(pstm);
						
						UtenteDao.updateRuoloUtente("user", id);
						
						ok = true;

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ok;

				}
				
		public static void main(String[] args) {
			Istruttore i = new Istruttore("ciccio", "pluto", "giuseppe", "russo", "20-01-1992", "email", "2424235", "istruttore", "paddel", "istruttore di paddel da 10 anni" );
			IstruttoreDao.insertIstr(i);
		}
}