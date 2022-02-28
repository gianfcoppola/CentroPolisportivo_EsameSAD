package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Campo;
import model.Corso;
import model.Prenotazione;
import model.Utente;

public class PrenotazioneDao {

	private static final String INSERT_NEW_PRENOTAZIONE = "INSERT INTO prenotazioni" + " (utente, corso, campo, data, oraInizio, oraFine) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";
	
	private static final String SELECT_PRENOTAZIONE_BY_ID = "select * from prenotazioni where id = ?";
	private static final String SELECT_PRENOTAZIONI_BY_UTENTE = "select * from prenotazioni where utente = ?";
	private static final String SELECT_PRENOTAZIONI_BY_CORSO = "select * from prenotazioni where corso = ?";
	private static final String SELECT_PRENOTAZIONI_DISPONIBILI_GIORNO = "select * from prenotazioni where data = ? and (campo = ? or campo = ?) ";
	private static final String SELECT_PRENOTAZIONI_DATA_ORA = "SELECT * FROM `prenotazioni` WHERE campo = ? and data = ? and oraInizio = ? and oraFine = ?";
	private static final String SELECT_ALL_PRENOTAZIONI = "select * from prenotazioni";
	
	private static final String DELETE_PRENOTAZIONE_BY_ID = "DELETE FROM PRENOTAZIONI WHERE id = ?";
	private static final String DELETE_PRENOTAZIONI_BY_CORSO = "DELETE FROM PRENOTAZIONI where corso = ?";
	
	public PrenotazioneDao() {}
	
	protected static Connection getConnection() {
		Connection connection = null; 
		connection = MyConnection.getConnection(); 
		
		return connection;
		
	}
	
	
	//insert prenotazione
	public static boolean insertPrenotazione(Prenotazione p) {
		Connection conn = getConnection();
		boolean ok = false;
		try {
			PreparedStatement pstm = conn.prepareStatement(INSERT_NEW_PRENOTAZIONE);
			if(p.getUtente()!=null)
				pstm.setInt(1, p.getUtente().getId());
			else
				pstm.setInt(1, p.getCorso().getId());
			pstm.setInt(2, p.getCorso().getId());
			pstm.setInt(3, p.getCampo().getId());
			pstm.setString(4, p.getData());
			pstm.setInt(5, p.getOraInizio());
			pstm.setInt(6, p.getOraFine());

			pstm.executeUpdate();
			System.out.println("Inserimento nel database...query effettuata:");
			System.out.println(pstm);
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}
	
	//select prenotazione by id
	public static Prenotazione selectPrenotazione (int id) {
		Prenotazione p = null;
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_PRENOTAZIONE_BY_ID);
			pstm.setInt(1, id);
	
			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				Utente utente = UtenteDao.selectUser(rs.getInt("utente"));
				Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
				Campo campo = CampoDao.selectCampo(rs.getInt("campo"));
				String data = rs.getString("data");
				int oraInizio = rs.getInt("oraInizio");
				int oraFine = rs.getInt("oraFine");
				p = new Prenotazione(id, utente, corso, campo, data, oraInizio, oraFine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return p;
	}
	
	//select prenotazione by utente - storico prenotazioni dell'utente
		public static List<Prenotazione> selectPrenotazioniUtente (int utente) {
			List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_PRENOTAZIONI_BY_UTENTE);
				pstm.setInt(1, utente);
		
				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					Utente u = UtenteDao.selectUser(utente);
					Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
					Campo campo = CampoDao.selectCampo(rs.getInt("campo"));
					String data = rs.getString("data");
					int oraInizio = rs.getInt("oraInizio");
					int oraFine = rs.getInt("oraFine");
					Prenotazione p = new Prenotazione(id, u, corso, campo, data, oraInizio, oraFine);
					prenotazioni.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return prenotazioni;
		}
		
		
		//select prenotazione by data e ora
		public static boolean selectPrenotazione (int campo, String data, int oraInizio, int oraFine) {
			Connection conn = getConnection();
			PreparedStatement pstm;
			boolean ok = false;
			try {
				pstm = conn.prepareStatement(SELECT_PRENOTAZIONI_DATA_ORA);
				pstm.setInt(1, campo);
				pstm.setString(2, data);
				pstm.setInt(3, oraInizio);
				pstm.setInt(4, oraFine);
		
				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					ok = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return ok;
		}
		
		//select prenotazione by corso 
				public static List<Prenotazione> selectPrenotazioniCorso (int idCorso) throws SQLException {
					List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
					Connection conn = getConnection();
					PreparedStatement pstm;
					try {
						pstm = conn.prepareStatement(SELECT_PRENOTAZIONI_BY_CORSO);
						pstm.setInt(1, idCorso);
				
						ResultSet rs = pstm.executeQuery();
						System.out.println("\nRecupero dal database...query effettuata:");
						System.out.println(pstm);
						while(rs.next()) {
							int id = rs.getInt("id");
							int utente = rs.getInt("utente");
							Utente u = UtenteDao.selectUser(utente);
							Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
							Campo campo = CampoDao.selectCampo(rs.getInt("campo"));
							String data = rs.getString("data");
							int oraInizio = rs.getInt("oraInizio");
							int oraFine = rs.getInt("oraFine");
							Prenotazione p = new Prenotazione(id, u, corso, campo, data, oraInizio, oraFine);
							prenotazioni.add(p);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally {
						if(conn != null)
							conn.close();
					}
							
					return prenotazioni;
				}
	
		//select prenotazione disponibili
		public static List<Prenotazione> selectPrenotazioniDisponibili (String data, String sport) {
			List<Prenotazione> prenotazioniDisponibili = new ArrayList<Prenotazione>();
			List<Prenotazione> prenotazioniEffettuate = new ArrayList<Prenotazione>();
			Connection conn = getConnection();
			PreparedStatement pstm;
			int[] id_campi = new int[2];
			/*
			DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = data.format(DATEFORMATTER);
			*/
			try {
				
				List<Campo> campi = CampoDao.selectCampo(sport);
				
				
				for(int i=0; i<campi.size(); i++) {
					id_campi[i] = campi.get(i).getId();
				}
				
				pstm = conn.prepareStatement(SELECT_PRENOTAZIONI_DISPONIBILI_GIORNO);
				
				pstm.setString(1, data);
				pstm.setInt(2, id_campi[0]);
				pstm.setInt(3, id_campi[1]);

				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					Utente u = UtenteDao.selectUser(rs.getString("utente"));
					Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
					Campo campo = CampoDao.selectCampo(rs.getInt("campo"));
					String dataPrenotazione = rs.getString("data");
					int oraInizio = rs.getInt("oraInizio");
					int oraFine = rs.getInt("oraFine");
					Prenotazione p = new Prenotazione(id, u, corso, campo, dataPrenotazione, oraInizio, oraFine);
					prenotazioniEffettuate.add(p);
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			int [] oreCampoCalcio = {15, 16, 17, 18, 19, 20, 21, 22, 23, 24}; 
			int [] oreCampoTennis_Paddel = {15, 16, 17, 18, 19, 20, 21};
			
			Campo c = CampoDao.selectCampo(id_campi[0]);
			String tipoCampo = c.getSport();
			
			System.out.println("PRENOTAZIONI EFFETTUATE");
			for (Prenotazione p: prenotazioniEffettuate)
				System.out.println(p);
			
			
			//setto tutti i campi come disponibili
			//mostro tutto
			if(tipoCampo.equals("calcio")) {
				for(int j=0; j<oreCampoCalcio.length-1; j++) {

					Campo campo1 = CampoDao.selectCampo(1);
					Prenotazione p1 = new Prenotazione(new Corso(), campo1, data, oreCampoCalcio[j], oreCampoCalcio[j+1]);
					Campo campo3 = CampoDao.selectCampo(2);
					Prenotazione p2 = new Prenotazione(new Corso(), campo3, data, oreCampoCalcio[j], oreCampoCalcio[j+1]);

					prenotazioniDisponibili.add(p1);
					prenotazioniDisponibili.add(p2);
				}	
			}

			else if(tipoCampo.equals("tennis")) {
				for(int j=0; j<oreCampoTennis_Paddel.length-1; j++) {

					Campo campo1 = CampoDao.selectCampo(5);
					Prenotazione p1 = new Prenotazione(new Corso(), campo1, data, oreCampoTennis_Paddel[j], oreCampoTennis_Paddel[j+1]);
					Campo campo3 = CampoDao.selectCampo(6);
					Prenotazione p2 = new Prenotazione(new Corso(), campo3, data, oreCampoTennis_Paddel[j], oreCampoTennis_Paddel[j+1]);

					prenotazioniDisponibili.add(p1);
					prenotazioniDisponibili.add(p2);
				}	
			}

			else if(tipoCampo.equals("paddel")) {
				for(int j=0; j<oreCampoTennis_Paddel.length-1; j++) {

					Campo campo1 = CampoDao.selectCampo(3);
					Prenotazione p1 = new Prenotazione(new Corso(), campo1, data, oreCampoTennis_Paddel[j], oreCampoTennis_Paddel[j+1]);
					Campo campo3 = CampoDao.selectCampo(4);
					Prenotazione p2 = new Prenotazione(new Corso(), campo3, data, oreCampoTennis_Paddel[j], oreCampoTennis_Paddel[j+1]);

					prenotazioniDisponibili.add(p1);
					prenotazioniDisponibili.add(p2);
				}	
			}
			
			//tolgo dalle prenotazioni libere quelle effettuate...
			for(int i=0; i<prenotazioniEffettuate.size(); i++) {
				
					for(int indexToRemove = 0; indexToRemove<prenotazioniDisponibili.size(); indexToRemove++) {
						if(prenotazioniEffettuate.get(i).getOraInizio() == prenotazioniDisponibili.get(indexToRemove).getOraInizio()
								&& prenotazioniEffettuate.get(i).getCampo().getId() == prenotazioniDisponibili.get(indexToRemove).getCampo().getId()) {
							prenotazioniDisponibili.remove(indexToRemove);
						}
					}
				
				
				
			}
			
			
				
			
				
			
			

			return prenotazioniDisponibili;
			//return prenotazioniEffettuate;
		}
		
		
	//select tutte le prenotazioni
	public static List<Prenotazione> selectPrenotazioni () throws SQLException {
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_ALL_PRENOTAZIONI);
		
			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				int id = rs.getInt("id");
				Utente utente = UtenteDao.selectUser(rs.getInt("utente"));
				Corso corso = CorsoDao.selectCorso(rs.getInt("corso"));
				Campo campo = CampoDao.selectCampo(rs.getInt("campo"));
				String data = rs.getString("data");
				int oraInizio = rs.getInt("oraInizio");
				int oraFine = rs.getInt("oraFine");
				Prenotazione p = new Prenotazione(id, utente, corso, campo, data, oraInizio, oraFine);
				prenotazioni.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null)
				conn.close();
		}
		
		for (Prenotazione p: prenotazioni)
			System.out.println(p);
		
		return prenotazioni;
		
	}
	
	
	//delete prenotazione
			public static boolean deletePrenotazione(int id) {
				Connection conn = getConnection();
				PreparedStatement pstm;
				boolean ok = false;
				try {
					pstm = conn.prepareStatement(DELETE_PRENOTAZIONE_BY_ID);
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
			
			//delete prenotazioni by corso
					public static boolean deletePrenotazioniCorso(int idCorso) {
						Connection conn = getConnection();
						PreparedStatement pstm;
						boolean ok = false;
						try {
							pstm = conn.prepareStatement(DELETE_PRENOTAZIONI_BY_CORSO);
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
	
	
	public static void main(String[] args) {
		/*Utente u = UtenteDao.selectUser(2);
		Campo c = CampoDao.selectCampo(2);
		Prenotazione p = new Prenotazione(u, c, "domani", "ore 8");
		PrenotazioneDao.insertPrenotazione(p);
		*/
		//Prenotazione p = new Prenotazione()
		/*
		LocalDate oggi = LocalDate.now();
		System.out.println(oggi);
		
		List<Prenotazione> prenotazioniDisponibili = PrenotazioneDao.selectPrenotazioniDisponibili(oggi, "paddel");
		System.out.println("\nELENCO PRENOTAZIoNI DISPONIBILI");
		
		for (Prenotazione p: prenotazioniDisponibili) {
			System.out.println(p);
		}
		*/
		
		/*
		String s1 = "9 - giuseppe russo (paddel)";
		String s2 = "6 - andrea cappiello (tennis)";
		int indicePS = s1.indexOf("(");
		int indicePD = s1.indexOf(")");
		String sport1 = s1.substring(indicePS+1, indicePD);
		int indicePS2 = s2.indexOf("(");
		int indicePD2 = s2.indexOf(")");
		String sport2 = s2.substring(indicePS2+1, indicePD2);
		System.out.println(sport1 + " " + sport2);
		*/
		
		List<Prenotazione> listaPrenotazioni = PrenotazioneDao.selectPrenotazioniDisponibili("11-02-2022", "paddel");
		for(Prenotazione p: listaPrenotazioni)
			System.out.println(p);
	}
}