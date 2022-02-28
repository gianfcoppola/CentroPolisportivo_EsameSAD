package model;

import java.util.List;

import database.AbbonamentoDao;

public class Abbonamento {

	private int id;
	private Utente utente;
	private Corso corso;
	private String inizioAbbonamento;
	private String fineAbbonamento;
	private int prezzoTotale;
	
	public Abbonamento(int id, Utente utente, Corso corso, String inizioAbbonamento, String fineAbbonamento,
			int prezzoTotale) {
		super();
		this.id = id;
		this.utente = utente;
		this.corso = corso;
		this.inizioAbbonamento = inizioAbbonamento;
		this.fineAbbonamento = fineAbbonamento;
		this.prezzoTotale = prezzoTotale;
	}

	public Abbonamento(Utente utente, Corso corso, String inizioAbbonamento, String fineAbbonamento,
			int prezzoTotale) {
		super();
		this.utente = utente;
		this.corso = corso;
		this.inizioAbbonamento = inizioAbbonamento;
		this.fineAbbonamento = fineAbbonamento;
		this.prezzoTotale = prezzoTotale;
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

	public String getInizioAbbonamento() {
		return inizioAbbonamento;
	}

	public void setInizioAbbonamento(String inizioAbbonamento) {
		this.inizioAbbonamento = inizioAbbonamento;
	}

	public String getFineAbbonamento() {
		return fineAbbonamento;
	}

	public void setFineAbbonamento(String fineAbbonamento) {
		this.fineAbbonamento = fineAbbonamento;
	}

	public int getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(int prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	@Override
	public String toString() {
		return "Abbonamento [id=" + id + ", utente=" + utente + ", corso=" + corso + ", inizioAbbonamento="
				+ inizioAbbonamento + ", fineAbbonamento=" + fineAbbonamento + ", prezzoTotale=" + prezzoTotale + "]";
	}
	
	
	public static List<Abbonamento> visualizzaAbbonamentiUtente(int idUtente){
		return AbbonamentoDao.selectAbbonamentiUtente(idUtente);
	}
	
	public static int calcolaCostoAbbonamento(int meseInizioAbb, int meseFineAbb, int prezzoMensileCorso) {
		double [] tasso = {0, 0.02, 0.05, 0.07, 0.08, 0.11, 0.14, 0.16, 0.18, 0.20, 0.25}; 
		int prezzoTotaleAbbonamento = (meseFineAbb-meseInizioAbb)*prezzoMensileCorso - (int)Math.round(prezzoMensileCorso*(meseFineAbb-meseInizioAbb)*tasso[meseFineAbb-meseInizioAbb-1]);
		return prezzoTotaleAbbonamento;
	}
	
}