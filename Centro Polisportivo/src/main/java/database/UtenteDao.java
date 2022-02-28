package database;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import model.Utente;


public class UtenteDao {


	private static final String INSERT_USERS_SQL = "INSERT INTO utenti" + " (username, password, nome, cognome, dataNascita, email, cellulare, ruolo) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String SELECT_USER_BY_USERNAME = "select * from utenti where username = ?";
	private static final String SELECT_USER_BY_UP = "select * from utenti where username = ? and password = ?";
	private static final String SELECT_USER_BY_ID = "select * from utenti where id = ?";
	private static final String SELECT_USERS_BY_RUOLO = "select * from utenti where ruolo = ?";
	private static final String SELECT_ALL_USERS = "select * from utenti";
	
	private static final String UPDATE_RUOLO_UTENTE = "update utenti set ruolo = ? where id = ?";
	
	
	public UtenteDao() {}
	
	protected static Connection getConnection() {
		Connection connection = null; 
		connection = MyConnection.getConnection(); 
		
		return connection;
		
	}
	
	//insert users
	public static void insertUser(Utente utente) {
		Connection conn = getConnection();
		try {
			PreparedStatement pstm = conn.prepareStatement(INSERT_USERS_SQL);
			pstm.setString(1, utente.getUsername());
			pstm.setString(2, utente.getPassword());
			pstm.setString(3, utente.getNome());
			pstm.setString(4, utente.getCognome());
			pstm.setString(5, utente.getDataNascita());
			pstm.setString(6, utente.getEmail());
			pstm.setString(7, utente.getCellulare());
			pstm.setString(8, utente.getRuolo());

			pstm.executeUpdate();
			System.out.println("Inserimento nel database...query effettuata:");
			System.out.println(pstm);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//select user by username
		public static Utente selectUser (String username) {
			Utente u = null;
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_USER_BY_USERNAME);
				pstm.setString(1, username);

				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					String password = rs.getString("password");
					String nome = rs.getString("nome");
					String cognome = rs.getString("cognome");
					String dataNascita = rs.getString("dataNascita");
					String email = rs.getString("email");
					String cellulare = rs.getString("cellulare");
					String ruolo = rs.getString("ruolo");
					u = new Utente(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return u;
			
			
		}
	
	//select user by username and password
	public static Utente selectUser (String username, String password) {
		Utente u = null;
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_USER_BY_UP);
			pstm.setString(1, username);
			pstm.setString(2, password);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String dataNascita = rs.getString("dataNascita");
				String email = rs.getString("email");
				String cellulare = rs.getString("cellulare");
				String ruolo = rs.getString("ruolo");
				u = new Utente(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
		
		
	}
	
	//select user by id
	public static Utente selectUser (int id) {
		Utente u = null;
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_USER_BY_ID);
			pstm.setInt(1, id);
	
			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String dataNascita = rs.getString("dataNascita");
				String email = rs.getString("email");
				String cellulare = rs.getString("cellulare");
				String ruolo = rs.getString("ruolo");
				u = new Utente(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
			
			
	}
	
	//select tutti gli utenti di quel ruolo
			public static List<Utente> selectUsers (String ruolo) {
				List<Utente> utenti = new ArrayList<Utente>();
				Connection conn = getConnection();
				PreparedStatement pstm;
				try {
					pstm = conn.prepareStatement(SELECT_USERS_BY_RUOLO);
					pstm.setString(1, ruolo);
					ResultSet rs = pstm.executeQuery();
					System.out.println("\nRecupero dal database...query effettuata:");
					System.out.println(pstm);
					while(rs.next()) {
						int id = rs.getInt("id");
						String username = rs.getString("username");
						String password = rs.getString("password");
						String nome = rs.getString("nome");
						String cognome = rs.getString("cognome");
						String dataNascita = rs.getString("dataNascita");
						String email = rs.getString("email");
						String cellulare = rs.getString("cellulare");
						
						utenti.add(new Utente(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (Utente u: utenti)
					System.out.println(u);
				return utenti;
				
				
			}
	
	//select all users
		public static List<Utente> selectUsers () {
			List<Utente> utenti = new ArrayList<Utente>();
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_ALL_USERS);

				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String nome = rs.getString("nome");
					String cognome = rs.getString("cognome");
					String dataNascita = rs.getString("dataNascita");
					String email = rs.getString("email");
					String cellulare = rs.getString("cellulare");
					String ruolo = rs.getString("ruolo");
					utenti.add(new Utente(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Utente u: utenti)
				System.out.println(u);
			return utenti;
			
			
		}
	
		//update ruolo utente
		public static void updateRuoloUtente (String ruolo, int id) {
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(UPDATE_RUOLO_UTENTE);
				pstm.setString(1, ruolo);
				pstm.setInt(2, id);
				
				pstm.executeUpdate();
				System.out.println("\nModifica effettuata:");
				System.out.println(pstm);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	
	
	public static void main(String[] args) {
		//Utente s = new Utente("un", "pass", "name", "surname", "birthdate", "mail", "telephone", "istruttore");
		//UtenteDao.insertUser(s);
		
		//Utente u = UtenteDao.selectUser("ciccio", "pluto");
		//System.out.println(u);
		//UtenteDao.selectUsers();
		
		
	
	}
}