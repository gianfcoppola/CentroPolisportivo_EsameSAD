package model;

import database.PrenotazioneDao;


public class Campo {

	private int id;
	private String sport;
	private int prezzo;
	private int oraInizioDisponibilit�;		//campo affittabile dalle ore n...
	private int oraFineDisponibilit�;		//...alle ore m
	
	public Campo(int id, String sport, int prezzo, int oraInizioDisponibilit�, int oraFineDisponibilit�) {
		super();
		this.id = id;
		this.sport = sport;
		this.prezzo = prezzo;
		this.oraInizioDisponibilit� = oraInizioDisponibilit�;
		this.oraFineDisponibilit� = oraFineDisponibilit�;
	}
	
	public Campo(String sport, int prezzo, int oraInizioDisponibilit�, int oraFineDisponibilit�) {
		super();
		this.sport = sport;
		this.prezzo = prezzo;
		this.oraInizioDisponibilit� = oraInizioDisponibilit�;
		this.oraFineDisponibilit� = oraFineDisponibilit�;
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

	public int getOraInizioDisponibilit�() {
		return oraInizioDisponibilit�;
	}

	public void setOraInizioDisponibilit�(int oraInizioDisponibilit�) {
		this.oraInizioDisponibilit� = oraInizioDisponibilit�;
	}

	public int getOraFineDisponibilit�() {
		return oraFineDisponibilit�;
	}

	public void setOraFineDisponibilit�(int oraFineDisponibilit�) {
		this.oraFineDisponibilit� = oraFineDisponibilit�;
	}

	
	
	@Override
	public String toString() {
		return "Campo [id=" + id + ", sport=" + sport + ", prezzo=" + prezzo + ", oraInizioDisponibilit�="
				+ oraInizioDisponibilit� + ", oraFineDisponibilit�=" + oraFineDisponibilit� + "]";
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
			uguale = this.id == c.id && this.oraInizioDisponibilit� == c.oraInizioDisponibilit� && this.oraFineDisponibilit� == c.oraFineDisponibilit�
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