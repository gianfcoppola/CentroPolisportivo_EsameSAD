package model;

import java.util.List;

import database.IstruttoreDao;


public class Istruttore extends Utente{

	private String sport;
	private String descrizione;
	
	public Istruttore(int id, String username, String password, String nome, String cognome, String dataNascita,
			String email, String cellulare, String ruolo, String sport, String descrizione) {
		super(id, username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
		this.sport = sport;
		this.descrizione = descrizione;
	}

	public Istruttore(String username, String password, String nome, String cognome, String dataNascita, String email,
			String cellulare, String ruolo, String sport, String descrizione) {
		super(username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
		this.sport = sport;
		this.descrizione = descrizione;
	}



	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Istruttore [sport=" + sport + ", descrizione=" + descrizione + "]";
	}
	
	public static Istruttore getIstruttore(int id) {
		return IstruttoreDao.selectIstruttore(id);
	}
	
	public static List<Istruttore> getTuttiIstruttori(){
		return IstruttoreDao.selectIstruttori();
	}

	public static List<Istruttore> getListaIstruttoriSport(String sport){
		return IstruttoreDao.selectIstruttori(sport);
	}
	
	public static boolean modificaIstruttore (String sport, String descrizione, int id) {
		return IstruttoreDao.updateIstruttore(sport, descrizione, id);
	}
	
	
}
	