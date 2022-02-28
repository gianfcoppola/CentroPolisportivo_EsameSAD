package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Corso;
import model.Istruttore;


public class CorsoDao {

	private static final String INSERT_NEW_CORSO = "INSERT INTO corsi" + " (sport, istruttore, descrizione, numeroLezioniSettimanali, "
			+ "lezioneLunedi, lezioneMartedi, lezioneMercoledi, lezioneGiovedi, lezioneVenerdi, orarioLezioneLunedi, "
			+ "orarioLezioneMartedi, orarioLezioneMercoledi, orarioLezioneGiovedi, orarioLezioneVenerdi, "
			+ "posti1, posti2, posti3, posti4, posti5, posti6, posti7, posti8, posti9, posti10, posti11, prezzoMensile) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_CORSO_BY_ID = "select * from corsi where id = ?";
	private static final String SELECT_LAST_CORSO = "select * from corsi ORDER BY id DESC LIMIT 1";
	private static final String SELECT_CORSO_BY_SPORT = "select * from corsi where sport = ?";
	private static final String SELECT_CORSI_BY_ISTRUTTORE = "select * from corsi where istruttore = ?";
	
	private static final String SELECT_ALL_CORSI = "select * from corsi";

	private static final String UPDATE_DATA_CORSO = "UPDATE corsi SET istruttore = ?, descrizione = ?, numeroLezioniSettimanali = ?, "
			+ "lezioneLunedi = ?, lezioneMartedi = ?, lezioneMercoledi = ?, lezioneGiovedi = ?, lezioneVenerdi = ?, "
			+ "orarioLezioneLunedi = ?, orarioLezioneMartedi = ?, orarioLezioneMercoledi = ?, orarioLezioneGiovedi = ?, "
			+ "orarioLezioneVenerdi = ?, posti1 = ?, posti2 = ?, posti3 = ?, posti4 = ?, posti5 = ?, posti6 = ?, posti7 = ?, "
			+ "posti8 = ?, posti9 = ?, posti10 = ?, posti11 = ?, prezzoMensile = ? WHERE id = ?";
	private static final String UPDATE_POSTI_CORSO = "UPDATE corsi SET posti1 = ?, posti2 = ?, posti3 = ?, posti4 = ?, "
			+ "posti5 = ?, posti6 = ?, posti7 = ?, posti8 = ?, posti9 = ?, posti10 = ?, posti11 = ? WHERE id = ?";
	
	private static final String DELETE_CORSO_BY_ID = "DELETE FROM corsi WHERE id = ?";
	private static final String DELETE_CORSO_BY_ISTRUTTORE = "DELETE FROM corsi WHERE istruttore = ?";

	public CorsoDao() {}

	protected static Connection getConnection() {
		Connection connection = null; 
		connection = MyConnection.getConnection(); 

		return connection;

	}
	

	//insert corso
	public static boolean insertCorso(Corso c) {
		Connection conn = getConnection();
		boolean ok = false;
		try {
			PreparedStatement pstm = conn.prepareStatement(INSERT_NEW_CORSO);
			pstm.setString(1, c.getSport());
			pstm.setInt(2, c.getIstruttore().getId());
			pstm.setString(3, c.getDescrizione());
			pstm.setInt(4, c.getNumeroLezioniSettimanali());
			pstm.setInt(5, c.getGiorniLezioni()[0]);
			pstm.setInt(6, c.getGiorniLezioni()[1]);
			pstm.setInt(7, c.getGiorniLezioni()[2]);
			pstm.setInt(8, c.getGiorniLezioni()[3]);
			pstm.setInt(9, c.getGiorniLezioni()[4]);
			pstm.setString(10, c.getOrariLezioni()[0]);
			pstm.setString(11, c.getOrariLezioni()[1]);
			pstm.setString(12, c.getOrariLezioni()[2]);
			pstm.setString(13, c.getOrariLezioni()[3]);
			pstm.setString(14, c.getOrariLezioni()[4]);
			
			pstm.setInt(15, c.getPostiDisponibili()[0]);
			pstm.setInt(16, c.getPostiDisponibili()[1]);
			pstm.setInt(17, c.getPostiDisponibili()[2]);
			pstm.setInt(18, c.getPostiDisponibili()[3]);
			pstm.setInt(19, c.getPostiDisponibili()[4]);
			pstm.setInt(20, c.getPostiDisponibili()[5]);
			pstm.setInt(21, c.getPostiDisponibili()[6]);
			pstm.setInt(22, c.getPostiDisponibili()[7]);
			pstm.setInt(23, c.getPostiDisponibili()[8]);
			pstm.setInt(24, c.getPostiDisponibili()[9]);
			pstm.setInt(25, c.getPostiDisponibili()[10]);
			pstm.setInt(26, c.getPrezzoMensile());

			pstm.executeUpdate();
			System.out.println("Inserimento nel database...query effettuata:");
			System.out.println(pstm);
			ok = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok;

	}

	//select corso by id
	public static Corso selectCorso (int id) {
		Corso c = null;
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_CORSO_BY_ID);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				String sport = rs.getString("sport");
				Istruttore istruttore = IstruttoreDao.selectIstruttore(rs.getInt("istruttore"));
				String descrizione = rs.getString("descrizione");
				int numeroLezioniSettimanali = rs.getInt("numeroLezioniSettimanali");
				int lezione1 = rs.getInt("lezioneLunedi");
				int lezione2 = rs.getInt("lezioneMartedi");
				int lezione3 = rs.getInt("lezioneMercoledi");
				int lezione4 = rs.getInt("lezioneGiovedi");
				int lezione5 = rs.getInt("lezioneVenerdi");
				int [] giorniLezioni = {lezione1, lezione2, lezione3, lezione4, lezione5};
				String orarioLezione1 = rs.getString("orarioLezioneLunedi");
				String orarioLezione2 = rs.getString("orarioLezioneMartedi");
				String orarioLezione3 = rs.getString("orarioLezioneMercoledi");
				String orarioLezione4 = rs.getString("orarioLezioneGiovedi");
				String orarioLezione5 = rs.getString("orarioLezioneVenerdi");
				String [] orariLezioni = {orarioLezione1, orarioLezione2, orarioLezione3, orarioLezione4, orarioLezione5};
				int posti1 = rs.getInt("posti1");
				int posti2 = rs.getInt("posti2");
				int posti3 = rs.getInt("posti3");
				int posti4 = rs.getInt("posti4");
				int posti5 = rs.getInt("posti5");
				int posti6 = rs.getInt("posti6");
				int posti7 = rs.getInt("posti7");
				int posti8 = rs.getInt("posti8");
				int posti9 = rs.getInt("posti9");
				int posti10 = rs.getInt("posti10");
				int posti11 = rs.getInt("posti11");
				int [] postiDisponibili = {posti1, posti2, posti3, posti4, posti5, posti6, posti7, posti8, posti9, posti10, posti11};
				int prezzoMensile = rs.getInt("prezzoMensile");
				c = new Corso(id, sport, istruttore, descrizione, numeroLezioniSettimanali, giorniLezioni, orariLezioni, postiDisponibili, prezzoMensile);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}
	
	
	//select corso by id
		public static Corso selectCorso () {
			Corso c = null;
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_LAST_CORSO);

				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					String sport = rs.getString("sport");
					Istruttore istruttore = IstruttoreDao.selectIstruttore(rs.getInt("istruttore"));
					String descrizione = rs.getString("descrizione");
					int numeroLezioniSettimanali = rs.getInt("numeroLezioniSettimanali");
					int lezione1 = rs.getInt("lezioneLunedi");
					int lezione2 = rs.getInt("lezioneMartedi");
					int lezione3 = rs.getInt("lezioneMercoledi");
					int lezione4 = rs.getInt("lezioneGiovedi");
					int lezione5 = rs.getInt("lezioneVenerdi");
					int [] giorniLezioni = {lezione1, lezione2, lezione3, lezione4, lezione5};
					String orarioLezione1 = rs.getString("orarioLezioneLunedi");
					String orarioLezione2 = rs.getString("orarioLezioneMartedi");
					String orarioLezione3 = rs.getString("orarioLezioneMercoledi");
					String orarioLezione4 = rs.getString("orarioLezioneGiovedi");
					String orarioLezione5 = rs.getString("orarioLezioneVenerdi");
					String [] orariLezioni = {orarioLezione1, orarioLezione2, orarioLezione3, orarioLezione4, orarioLezione5};
					int posti1 = rs.getInt("posti1");
					int posti2 = rs.getInt("posti2");
					int posti3 = rs.getInt("posti3");
					int posti4 = rs.getInt("posti4");
					int posti5 = rs.getInt("posti5");
					int posti6 = rs.getInt("posti6");
					int posti7 = rs.getInt("posti7");
					int posti8 = rs.getInt("posti8");
					int posti9 = rs.getInt("posti9");
					int posti10 = rs.getInt("posti10");
					int posti11 = rs.getInt("posti11");
					int [] postiDisponibili = {posti1, posti2, posti3, posti4, posti5, posti6, posti7, posti8, posti9, posti10, posti11};
					int prezzoMensile = rs.getInt("prezzoMensile");
					c = new Corso(id, sport, istruttore, descrizione, numeroLezioniSettimanali, giorniLezioni, orariLezioni, postiDisponibili, prezzoMensile);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return c;
		}


	//select corso by sport
	public static List<Corso> selectCorsi (String sport) {
		List<Corso> corsi = new ArrayList<Corso>();
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_CORSO_BY_SPORT);
			pstm.setString(1, sport);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				int id = rs.getInt("id");
				Istruttore istruttore = IstruttoreDao.selectIstruttore(rs.getInt("istruttore"));
				String descrizione = rs.getString("descrizione");
				int numeroLezioniSettimanali = rs.getInt("numeroLezioniSettimanali");
				int lezione1 = rs.getInt("lezioneLunedi");
				int lezione2 = rs.getInt("lezioneMartedi");
				int lezione3 = rs.getInt("lezioneMercoledi");
				int lezione4 = rs.getInt("lezioneGiovedi");
				int lezione5 = rs.getInt("lezioneVenerdi");
				int [] giorniLezioni = {lezione1, lezione2, lezione3, lezione4, lezione5};
				String orarioLezione1 = rs.getString("orarioLezioneLunedi");
				String orarioLezione2 = rs.getString("orarioLezioneMartedi");
				String orarioLezione3 = rs.getString("orarioLezioneMercoledi");
				String orarioLezione4 = rs.getString("orarioLezioneGiovedi");
				String orarioLezione5 = rs.getString("orarioLezioneVenerdi");
				String [] orariLezioni = {orarioLezione1, orarioLezione2, orarioLezione3, orarioLezione4, orarioLezione5};
				int posti1 = rs.getInt("posti1");
				int posti2 = rs.getInt("posti2");
				int posti3 = rs.getInt("posti3");
				int posti4 = rs.getInt("posti4");
				int posti5 = rs.getInt("posti5");
				int posti6 = rs.getInt("posti6");
				int posti7 = rs.getInt("posti7");
				int posti8 = rs.getInt("posti8");
				int posti9 = rs.getInt("posti9");
				int posti10 = rs.getInt("posti10");
				int posti11 = rs.getInt("posti11");
				int [] postiDisponibili = {posti1, posti2, posti3, posti4, posti5, posti6, posti7, posti8, posti9, posti10, posti11};
				int prezzoMensile = rs.getInt("prezzoMensile");
				Corso c = new Corso(id, sport, istruttore, descrizione, numeroLezioniSettimanali, giorniLezioni, orariLezioni, postiDisponibili, prezzoMensile);
				corsi.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Corso c: corsi)
			System.out.println(c);

		return corsi;

	}
	
	//select corso by istruttore
		public static List<Corso> selectCorsi (int idIstruttore) {
			List<Corso> corsi = new ArrayList<Corso>();
			Connection conn = getConnection();
			PreparedStatement pstm;
			try {
				pstm = conn.prepareStatement(SELECT_CORSI_BY_ISTRUTTORE);
				pstm.setInt(1, idIstruttore);

				ResultSet rs = pstm.executeQuery();
				System.out.println("\nRecupero dal database...query effettuata:");
				System.out.println(pstm);
				while(rs.next()) {
					int id = rs.getInt("id");
					String sport = rs.getString("sport");
					Istruttore istruttore = IstruttoreDao.selectIstruttore(rs.getInt("istruttore"));
					String descrizione = rs.getString("descrizione");
					int numeroLezioniSettimanali = rs.getInt("numeroLezioniSettimanali");
					int lezione1 = rs.getInt("lezioneLunedi");
					int lezione2 = rs.getInt("lezioneMartedi");
					int lezione3 = rs.getInt("lezioneMercoledi");
					int lezione4 = rs.getInt("lezioneGiovedi");
					int lezione5 = rs.getInt("lezioneVenerdi");
					int [] giorniLezioni = {lezione1, lezione2, lezione3, lezione4, lezione5};
					String orarioLezione1 = rs.getString("orarioLezioneLunedi");
					String orarioLezione2 = rs.getString("orarioLezioneMartedi");
					String orarioLezione3 = rs.getString("orarioLezioneMercoledi");
					String orarioLezione4 = rs.getString("orarioLezioneGiovedi");
					String orarioLezione5 = rs.getString("orarioLezioneVenerdi");
					String [] orariLezioni = {orarioLezione1, orarioLezione2, orarioLezione3, orarioLezione4, orarioLezione5};
					int posti1 = rs.getInt("posti1");
					int posti2 = rs.getInt("posti2");
					int posti3 = rs.getInt("posti3");
					int posti4 = rs.getInt("posti4");
					int posti5 = rs.getInt("posti5");
					int posti6 = rs.getInt("posti6");
					int posti7 = rs.getInt("posti7");
					int posti8 = rs.getInt("posti8");
					int posti9 = rs.getInt("posti9");
					int posti10 = rs.getInt("posti10");
					int posti11 = rs.getInt("posti11");
					int [] postiDisponibili = {posti1, posti2, posti3, posti4, posti5, posti6, posti7, posti8, posti9, posti10, posti11};
					int prezzoMensile = rs.getInt("prezzoMensile");
					Corso c = new Corso(id, sport, istruttore, descrizione, numeroLezioniSettimanali, giorniLezioni, orariLezioni, postiDisponibili, prezzoMensile);
					corsi.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (Corso c: corsi)
				System.out.println(c);

			return corsi;

		}
	

	//select  tutti i corsi
	public static List<Corso> selectCorsi () {
		List<Corso> corsi = new ArrayList<Corso>();
		Connection conn = getConnection();
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(SELECT_ALL_CORSI);

			ResultSet rs = pstm.executeQuery();
			System.out.println("\nRecupero dal database...query effettuata:");
			System.out.println(pstm);
			while(rs.next()) {
				int id = rs.getInt("id");
				String sport = rs.getString("sport");
				Istruttore istruttore = IstruttoreDao.selectIstruttore(rs.getInt("istruttore"));
				String descrizione = rs.getString("descrizione");
				int numeroLezioniSettimanali = rs.getInt("numeroLezioniSettimanali");
				int lezione1 = rs.getInt("lezioneLunedi");
				int lezione2 = rs.getInt("lezioneMartedi");
				int lezione3 = rs.getInt("lezioneMercoledi");
				int lezione4 = rs.getInt("lezioneGiovedi");
				int lezione5 = rs.getInt("lezioneVenerdi");
				int [] giorniLezioni = {lezione1, lezione2, lezione3, lezione4, lezione5};
				String orarioLezione1 = rs.getString("orarioLezioneLunedi");
				String orarioLezione2 = rs.getString("orarioLezioneMartedi");
				String orarioLezione3 = rs.getString("orarioLezioneMercoledi");
				String orarioLezione4 = rs.getString("orarioLezioneGiovedi");
				String orarioLezione5 = rs.getString("orarioLezioneVenerdi");
				String [] orariLezioni = {orarioLezione1, orarioLezione2, orarioLezione3, orarioLezione4, orarioLezione5};
				int posti1 = rs.getInt("posti1");
				int posti2 = rs.getInt("posti2");
				int posti3 = rs.getInt("posti3");
				int posti4 = rs.getInt("posti4");
				int posti5 = rs.getInt("posti5");
				int posti6 = rs.getInt("posti6");
				int posti7 = rs.getInt("posti7");
				int posti8 = rs.getInt("posti8");
				int posti9 = rs.getInt("posti9");
				int posti10 = rs.getInt("posti10");
				int posti11 = rs.getInt("posti11");
				int [] postiDisponibili = {posti1, posti2, posti3, posti4, posti5, posti6, posti7, posti8, posti9, posti10, posti11};
				int prezzoMensile = rs.getInt("prezzoMensile");
				Corso c = new Corso(id, sport, istruttore, descrizione, numeroLezioniSettimanali, giorniLezioni, orariLezioni, postiDisponibili, prezzoMensile);
				corsi.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Corso c: corsi)
			System.out.println(c);

		return corsi;

	}


	//update dati corsi
	public static boolean updateCorso (int idIstruttore, String descrizione, int numeroLezioni, int [] giorniLezione,
			String [] orariLezioni, int [] postiDisponibili, int prezzoMensile, int idCorso) {
		Connection conn = getConnection();
		PreparedStatement pstm;
		boolean ok = false;
		try {
			pstm = conn.prepareStatement(UPDATE_DATA_CORSO);
			pstm.setInt(1, idIstruttore);
			pstm.setString(2, descrizione);
			pstm.setInt(3, numeroLezioni);
			pstm.setInt(4, giorniLezione[0]);
			pstm.setInt(5, giorniLezione[1]);
			pstm.setInt(6, giorniLezione[2]);
			pstm.setInt(7, giorniLezione[3]);
			pstm.setInt(8, giorniLezione[4]);
			pstm.setString(9, orariLezioni[0]);
			pstm.setString(10, orariLezioni[1]);
			pstm.setString(11, orariLezioni[2]);
			pstm.setString(12, orariLezioni[3]);
			pstm.setString(13, orariLezioni[4]);
			pstm.setInt(14, postiDisponibili[0]);
			pstm.setInt(15, postiDisponibili[1]);
			pstm.setInt(16, postiDisponibili[2]);
			pstm.setInt(17, postiDisponibili[3]);
			pstm.setInt(18, postiDisponibili[4]);
			pstm.setInt(19, postiDisponibili[5]);
			pstm.setInt(20, postiDisponibili[6]);
			pstm.setInt(21, postiDisponibili[7]);
			pstm.setInt(22, postiDisponibili[8]);
			pstm.setInt(23, postiDisponibili[9]);
			pstm.setInt(24, postiDisponibili[10]);
			pstm.setInt(25, prezzoMensile);
			pstm.setInt(26, idCorso);

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
	
	//update dati corsi
		public static boolean updateCorso (int[] postiDisponibili, int idCorso) {
			Connection conn = getConnection();
			PreparedStatement pstm;
			boolean ok = false;
			try {
				pstm = conn.prepareStatement(UPDATE_POSTI_CORSO);
				
				pstm.setInt(1, postiDisponibili[0]);
				pstm.setInt(2, postiDisponibili[1]);
				pstm.setInt(3, postiDisponibili[2]);
				pstm.setInt(4, postiDisponibili[3]);
				pstm.setInt(5, postiDisponibili[4]);
				pstm.setInt(6, postiDisponibili[5]);
				pstm.setInt(7, postiDisponibili[6]);
				pstm.setInt(8, postiDisponibili[7]);
				pstm.setInt(9, postiDisponibili[8]);
				pstm.setInt(10, postiDisponibili[9]);
				pstm.setInt(11, postiDisponibili[10]);
				pstm.setInt(12, idCorso);

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


	//delete corso
	public static boolean deleteCorso (int id) {
		Connection conn = getConnection();
		PreparedStatement pstm;
		boolean ok = false;
		try {
			pstm = conn.prepareStatement(DELETE_CORSO_BY_ID);
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
	
	//delete corso
		public static boolean deleteCorsi (int idIstruttore) {
			Connection conn = getConnection();
			PreparedStatement pstm;
			boolean ok = false;
			try {
				pstm = conn.prepareStatement(DELETE_CORSO_BY_ISTRUTTORE);
				pstm.setInt(1, idIstruttore);
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