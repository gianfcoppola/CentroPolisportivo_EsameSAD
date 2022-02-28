package model;

import database.UtenteDao;

public class Utente {

	private int id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String email;
	private String cellulare;
	private String ruolo;
	
	public Utente(int id, String username, String password, String nome, String cognome, String dataNascita,
			String email, String cellulare, String ruolo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.cellulare = cellulare;
		this.ruolo = ruolo;
	}

	public Utente(String username, String password, String nome, String cognome, String dataNascita, String email, String cellulare,
			String ruolo) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.cellulare = cellulare;
		this.ruolo = ruolo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", email=" + email + ", cellulare="
				+ cellulare + ", ruolo=" + ruolo + "]";
	}

	public boolean equals(Object obj) {
		boolean uguale = false;
		if((obj != null) && obj instanceof Utente) {
			Utente u = (Utente) obj;
			uguale = this.id == u.id && this.username.equals(u.username) && this.password.equals(u.password) && this.nome.equals(u.nome)
					&& this.cognome.equals(u.cognome) && this.dataNascita.equals(u.dataNascita) && this.email.equals(u.email)
					&& this.cellulare.equals(u.cellulare) && this.ruolo.equals(u.ruolo);
		}
		return uguale;
		
	}
	
	public static Utente getUtente (int id) {
		return UtenteDao.selectUser(id);
	}
	
	public static Utente getUtente (String username) {
		return UtenteDao.selectUser(username);
	}
	
	public static Utente getUtente (String username, String password) {
		return UtenteDao.selectUser(username, password);
	}
	
	public static void updateRuoloUtente(String ruolo, int id) {
		UtenteDao.updateRuoloUtente(ruolo, id);
	}
	
	
}