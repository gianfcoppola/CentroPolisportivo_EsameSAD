package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.AbbonamentoDao;
import database.IstruttoreDao;
import database.PrenotazioneDao;

public class Amministratore {

	private String username;
	private String password;
	
	public Amministratore(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	public static int getIncassiPrenotazioni() {
		int incassi = 0;
		try {
			List<Prenotazione> listaPrenotazioni = PrenotazioneDao.selectPrenotazioni();
			for(Prenotazione p: listaPrenotazioni)
				incassi = incassi + p.getCampo().getPrezzo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return incassi;
	}
	
	public static int getIncassiAbbonamenti() {
		int incassi = 0;
		List<Abbonamento> listaAbbonamenti = AbbonamentoDao.selectAbbonamenti();
		for(Abbonamento a: listaAbbonamenti)
			incassi = incassi + a.getPrezzoTotale();
		return incassi;
	}
	
	public static int getIncassiTotali() {
		return getIncassiPrenotazioni() + getIncassiAbbonamenti();
	}
	
	public static void creaIstruttore(Istruttore i) {
		IstruttoreDao.insertIstr(i);
	}
	
	
	public static boolean eliminaIstruttore(int id) {
		return IstruttoreDao.deleteIstruttore(id);
	}
	
	
	
	public static List<Prenotazione> visualizzaPrenotazioni(){
		List<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();
		try {
			return PrenotazioneDao.selectPrenotazioni();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPrenotazioni;
	}
	
	public static List<Abbonamento> visualizzaAbbonamenti(){
		return AbbonamentoDao.selectAbbonamenti();
	}
	
}