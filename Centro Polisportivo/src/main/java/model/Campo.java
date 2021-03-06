package model;

import database.PrenotazioneDao;


public class Campo {

	private int id;
	private String sport;
	private int prezzo;
	private int oraInizioDisponibilitÓ;		//campo affittabile dalle ore n...
	private int oraFineDisponibilitÓ;		//...alle ore m
	
	public Campo(int id, String sport, int prezzo, int oraInizioDisponibilitÓ, int oraFineDisponibilitÓ) {
		super();
		this.id = id;
		this.sport = sport;
		this.prezzo = prezzo;
		this.oraInizioDisponibilitÓ = oraInizioDisponibilitÓ;
		this.oraFineDisponibilitÓ = oraFineDisponibilitÓ;
	}
	
	public Campo(String sport, int prezzo, int oraInizioDisponibilitÓ, int oraFineDisponibilitÓ) {
		super();
		this.sport = sport;
		this.prezzo = prezzo;
		this.oraInizioDisponibilitÓ = oraInizioDisponibilitÓ;
		this.oraFineDisponibilitÓ = oraFineDisponibilitÓ;
	}

	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getOraInizioDisponibilitÓ() {
		return oraInizioDisponibilitÓ;
	}

	public void setOraInizioDisponibilitÓ(int oraInizioDisponibilitÓ) {
		this.oraInizioDisponibilitÓ = oraInizioDisponibilitÓ;
	}

	public int getOraFineDisponibilitÓ() {
		return oraFineDisponibilitÓ;
	}

	public void setOraFineDisponibilitÓ(int oraFineDisponibilitÓ) {
		this.oraFineDisponibilitÓ = oraFineDisponibilitÓ;
	}

	
	
	@Override
	public String toString() {
		return "Campo [id=" + id + ", sport=" + sport + ", prezzo=" + prezzo + ", oraInizioDisponibilitÓ="
				+ oraInizioDisponibilitÓ + ", oraFineDisponibilitÓ=" + oraFineDisponibilitÓ + "]";
	}

	/*
	public static boolean registraPrenotazioneCampo(Prenotazione p) {
		return PrenotazioneDao.insertPrenotazione(p);
	}
	*/
	
	public boolean equals(Object obj) {
		boolean uguale = false;
		if((obj != null) && obj instanceof Campo) {
			Campo c = (Campo) obj;
			uguale = this.id == c.id && this.oraInizioDisponibilitÓ == c.oraInizioDisponibilitÓ && this.oraFineDisponibilitÓ == c.oraFineDisponibilitÓ
					&& this.prezzo == c.prezzo && this.sport.equals(c.sport);
		}
		return uguale;
	}
	
	
	public static void registraPrenotazione(Prenotazione p) {
		PrenotazioneDao.insertPrenotazione(p);
	}
	
	public static boolean annullaPrenotazioniCorso(int idCorso) {
		return PrenotazioneDao.deletePrenotazioniCorso(idCorso);
	}
	
	public static boolean annullaPrenotazione(int id) {
		return PrenotazioneDao.deletePrenotazione(id);
	}
}