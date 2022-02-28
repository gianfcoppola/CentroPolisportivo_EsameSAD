package model;

import java.sql.SQLException;
import java.util.List;

import database.PrenotazioneDao;



public class Prenotazione {

	private int id;
	private Utente utente;
	private Corso corso;
	private Campo campo;
	private String data;
	private int oraInizio;
	private int oraFine;

	public Prenotazione(int id, Utente utente, Corso corso, Campo campo, String data, int oraInizio, int oraFine) {
		super();
		this.id = id;
		this.utente = utente;
		this.corso = corso;
		this.campo = campo;
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
	}

	public Prenotazione(Utente utente, Corso corso, Campo campo, String data, int oraInizio, int oraFine) {
		super();
		this.utente = utente;
		this.corso = corso;
		this.campo = campo;
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
	}

	public Prenotazione(Corso corso, Campo campo, String data, int oraInizio, int oraFine) {
		super();
		this.corso = corso;
		this.campo = campo;
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}

	public int getOraFine() {
		return oraFine;
	}

	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}

	@Override
	public String toString() {
		return "Prenotazione [id=" + id + ", utente=" + utente + ", corso=" + corso + ", campo=" + campo + ", data="
				+ data + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine + "]";
	}

	
	public boolean equals(Object obj) {
		boolean uguale = false;
		if((obj != null) && obj instanceof Prenotazione) {
			Prenotazione p = (Prenotazione) obj;
			uguale = this.corso.getId()==p.getCorso().getId() && this.campo.equals(p.campo) && this.data.equals(p.data) && this.oraInizio == p.oraInizio && this.oraFine == p.oraFine;
		}
		return uguale;
	}

	
	
	public static List<Prenotazione> visualizzaPrenotazioniEffettuate(int idUtente){
		return PrenotazioneDao.selectPrenotazioniUtente(idUtente);
	}
	
	public static List<Prenotazione> recuperaPrenotazioniCorso(int idCorso) throws SQLException{
		return PrenotazioneDao.selectPrenotazioniCorso(idCorso);
	}
	
	
	
	
	
}