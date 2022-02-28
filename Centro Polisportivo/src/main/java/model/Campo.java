package model;

import database.PrenotazioneDao;


public class Campo {

	private int id;
	private String sport;
	private int prezzo;
	private int oraInizioDisponibilità;		//campo affittabile dalle ore n...
	private int oraFineDisponibilità;		//...alle ore m
	
	public Campo(int id, String sport, int prezzo, int oraInizioDisponibilità, int oraFineDisponibilità) {
		super();
		this.id = id;
		this.sport = sport;
		this.prezzo = prezzo;
		this.oraInizioDisponibilità = oraInizioDisponibilità;
		this.oraFineDisponibilità = oraFineDisponibilità;
	}
	
	public Campo(String sport, int prezzo, int oraInizioDisponibilità, int oraFineDisponibilità) {
		super();
		this.sport = sport;
		this.prezzo = prezzo;
		this.oraInizioDisponibilità = oraInizioDisponibilità;
		this.oraFineDisponibilità = oraFineDisponibilità;
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

	public int getOraInizioDisponibilità() {
		return oraInizioDisponibilità;
	}

	public void setOraInizioDisponibilità(int oraInizioDisponibilità) {
		this.oraInizioDisponibilità = oraInizioDisponibilità;
	}

	public int getOraFineDisponibilità() {
		return oraFineDisponibilità;
	}

	public void setOraFineDisponibilità(int oraFineDisponibilità) {
		this.oraFineDisponibilità = oraFineDisponibilità;
	}

	
	
	@Override
	public String toString() {
		return "Campo [id=" + id + ", sport=" + sport + ", prezzo=" + prezzo + ", oraInizioDisponibilità="
				+ oraInizioDisponibilità + ", oraFineDisponibilità=" + oraFineDisponibilità + "]";
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
			uguale = this.id == c.id && this.oraInizioDisponibilità == c.oraInizioDisponibilità && this.oraFineDisponibilità == c.oraFineDisponibilità
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