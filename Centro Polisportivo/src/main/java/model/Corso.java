package model;

import java.util.Arrays;

import database.AbbonamentoDao;

public class Corso {

	private int id;
	private String sport;
	private Istruttore istruttore;
	private String descrizione;
	private int numeroLezioniSettimanali;
	private int [] giorniLezioni = new int[5];	//01010
	private String [] orariLezioni = new String[5];
	private int [] postiDisponibili = new int[11];
	private int prezzoMensile;
	
	

	public Corso(int id, String sport, Istruttore istruttore, String descrizione, int numeroLezioniSettimanali,
			int[] giorniLezioni, String[] orariLezioni, int[] postiDisponibili, int prezzoMensile) {
		super();
		this.id = id;
		this.sport = sport;
		this.istruttore = istruttore;
		this.descrizione = descrizione;
		this.numeroLezioniSettimanali = numeroLezioniSettimanali;
		this.giorniLezioni = giorniLezioni;
		this.orariLezioni = orariLezioni;
		this.postiDisponibili = postiDisponibili;
		this.prezzoMensile = prezzoMensile;
	}

	


	public Corso() {
		super();
	}




	public Corso(String sport, Istruttore istruttore, String descrizione, int numeroLezioniSettimanali,
			int[] giorniLezioni, String[] orariLezioni, int[] postiDisponibili, int prezzoMensile) {
		super();
		this.sport = sport;
		this.istruttore = istruttore;
		this.descrizione = descrizione;
		this.numeroLezioniSettimanali = numeroLezioniSettimanali;
		this.giorniLezioni = giorniLezioni;
		this.orariLezioni = orariLezioni;
		this.postiDisponibili = postiDisponibili;
		this.prezzoMensile = prezzoMensile;
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




	public Istruttore getIstruttore() {
		return istruttore;
	}




	public void setIstruttore(Istruttore istruttore) {
		this.istruttore = istruttore;
	}




	public String getDescrizione() {
		return descrizione;
	}




	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}




	public int getNumeroLezioniSettimanali() {
		return numeroLezioniSettimanali;
	}




	public void setNumeroLezioniSettimanali(int numeroLezioniSettimanali) {
		this.numeroLezioniSettimanali = numeroLezioniSettimanali;
	}




	public int[] getGiorniLezioni() {
		return giorniLezioni;
	}




	public void setGiorniLezioni(int[] giorniLezioni) {
		this.giorniLezioni = giorniLezioni;
	}




	public String[] getOrariLezioni() {
		return orariLezioni;
	}




	public void setOrariLezioni(String[] orariLezioni) {
		this.orariLezioni = orariLezioni;
	}




	public int[] getPostiDisponibili() {
		return postiDisponibili;
	}




	public void setPostiDisponibili(int[] postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}




	public int getPrezzoMensile() {
		return prezzoMensile;
	}




	public void setPrezzoMensile(int prezzoMensile) {
		this.prezzoMensile = prezzoMensile;
	}


	

	@Override
	public String toString() {
		return "Corso [id=" + id + ", sport=" + sport + ", istruttore=" + istruttore + ", descrizione=" + descrizione
				+ ", numeroLezioniSettimanali=" + numeroLezioniSettimanali + ", giorniLezioni="
				+ Arrays.toString(giorniLezioni) + ", orariLezioni=" + Arrays.toString(orariLezioni)
				+ ", postiDisponibili=" + Arrays.toString(postiDisponibili) + ", prezzoMensile=" + prezzoMensile + "]";
	}


	public static boolean registraAbbonamento(Abbonamento a) {
		return AbbonamentoDao.insertAbbonamento(a);
	}
	
	
	
	public static Abbonamento getAbbonamento (int idAbb) {
		return AbbonamentoDao.selectAbbonamento(idAbb);
	}
	
	public static boolean eliminaAbbonamento(int id) {
		return AbbonamentoDao.deleteAbbonamento(id);
	}
	
	public static boolean eliminaTuttiAbbonamentiCorso(int id) {
		return AbbonamentoDao.deleteAbbonamentiCorso(id);
	}
	
	

	
	
}