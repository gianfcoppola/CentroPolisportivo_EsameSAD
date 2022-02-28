package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.AbbonamentoDao;
import database.CampoDao;
import database.CorsoDao;
import database.PrenotazioneDao;
import database.UtenteDao;

public class ComplessoSportivo {

	public ComplessoSportivo() {
		// TODO Auto-generated constructor stub
	}
	
	public static void registraUtente(Utente u) {
		UtenteDao.insertUser(u);
	}
	
	public static boolean creaCorso(Corso c) {
		return CorsoDao.insertCorso(c);
	}
	
	public static List<Prenotazione> visualizzaCampettiDisponibili(String date, String sport){
		return PrenotazioneDao.selectPrenotazioniDisponibili(date, sport);
	}
	
	public static boolean verificaDisponibilitaDataOraPrenotazione(int campo, String data, int oraInizio, int oraFine) {
		return PrenotazioneDao.selectPrenotazione(campo, data, oraInizio, oraFine);
	}
	
	public static List<Integer> verificaDisponibilit‡Corso(Corso c, int meseInizio, int meseFine) {
		int [] nuoviPosti = new int [11];
		List<Integer> mesiFiniti = new ArrayList<Integer>();
		nuoviPosti = c.getPostiDisponibili();
		
		int i = meseInizio;
		
		
		while(i<meseFine) {
			if(c.getPostiDisponibili()[i-1] == 0) {
				mesiFiniti.add(i);
			}
			else {
				nuoviPosti[i-1]--;
				c.setPostiDisponibili(nuoviPosti);
			}
			
			i++;
		}
		
		return mesiFiniti;
	}
	
	public static List<Corso> getInfoCorsi(){
		return CorsoDao.selectCorsi();
	}
	
	public static List<Corso> getCorsiIstruttore(int idIstruttore){
		return CorsoDao.selectCorsi(idIstruttore);
	}
	
	public static Campo getCampo (int idCampo) {
		return CampoDao.selectCampo(idCampo);
	}
	
	public static Corso getCorso(int id) {
		return CorsoDao.selectCorso(id);
	}
	
	public static Corso getCorsoAppenaCreato() {
		return CorsoDao.selectCorso();
	}
	
	public static boolean modificaDatiCorso(int [] postiDisponibili, int idCorso) {
		return CorsoDao.updateCorso(postiDisponibili, idCorso);
	}
	
	public static boolean modificaDatiCorso (int idIstruttore, String descrizione, int numeroLezioni, int [] giorniLezione,
			String [] orariLezioni, int [] postiDisponibili, int prezzoMensile, int idCorso) {
		return CorsoDao.updateCorso(idIstruttore, descrizione, numeroLezioni, giorniLezione, orariLezioni, postiDisponibili, prezzoMensile, idCorso);
	}

	public static boolean eliminaCorso(int idCorso) {
		return CorsoDao.deleteCorso(idCorso);
	}
	
	public static boolean eliminaCorsiIstruttore(int idIstruttore) {
		return CorsoDao.deleteCorsi(idIstruttore);
	}
	
	public static List<Abbonamento> recuperaAbbonamenti(){
		return AbbonamentoDao.selectAbbonamenti();
	}
	
	public static List<Prenotazione> recuperaPrenotazioni() throws SQLException{
		return PrenotazioneDao.selectPrenotazioni();
	}
	
	public static List<Utente> getListaUtenti(){
		return UtenteDao.selectUsers("user");
	}
	
	public static boolean isLogin(String username, String password) {
		if(UtenteDao.selectUser(username, password) != null)
			return true;
		else
			return false;
	}
	
	public static boolean usernameUsed(String username){
		if(UtenteDao.selectUser(username) != null)
			return true;
		else
			return false;
	}
}