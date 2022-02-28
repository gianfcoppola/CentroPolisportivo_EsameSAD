package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Amministratore;
import model.ComplessoSportivo;
import model.Istruttore;
import model.Utente;

/**
 * Servlet implementation class GestioneIstruttoriServlet
 */
@WebServlet("/GestioneIstruttoriServlet")
public class GestioneIstruttoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneIstruttoriServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 // Funzioni per la correttezza del cellulare e dell'e-mail.
 	//------------------------------------------
 	private boolean mailSyntaxCheck(String email){		
 		// Create the Pattern using the regex
 		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

 		// Match the given string with the pattern
 		Matcher m = p.matcher(email);

 		// check whether match is found
 		boolean matchFound = m.matches();

 		StringTokenizer st = new StringTokenizer(email, ".");
 		String lastToken = null;
 		while (st.hasMoreTokens()) {
 			lastToken = st.nextToken();
 		}

 		// validate the country code
 		if (matchFound && lastToken.length() >= 2
 				&& email.length() - 1 != lastToken.length()) {

 			return true;
 		} else {
 			return false;
 		}

 	} 
 	
 	private static boolean checkIsNumero (String s) {
 		boolean ok = false;
 		for (int i=0; i<s.length(); i++) {
 			if(Character.isDigit(s.charAt(i)))
 				ok = true;
 			else
 				return false;
 		}
 		return ok;
 	}
 	
 	


 	/**
 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		response.getWriter().append("Served at: ").append(request.getContextPath());

 		
 		
 		String azione = request.getParameter("azioneIstruttore");

 		if(azione.equalsIgnoreCase("crea")) {
 			HttpSession currentsession = request.getSession();
 			
 			String creaIstruttoreDaZero = (String) currentsession.getAttribute("creaIstruttoreDaZero");
 	 		String alert;

 	 		String tipoInserimento = request.getParameter("tipoInserimento");
 	 		if (creaIstruttoreDaZero.equals("3")) {
 	 			
 	 			String utenteSelezionato = request.getParameter("listaUtenti");
 	 			String sport = request.getParameter("sport");
 	 			String descrizione = request.getParameter("descrizioneCorso");

 	 			if(utenteSelezionato.length() == 0 || descrizione.length()==0) {
 	 				alert = "34";	
 	 				currentsession.setAttribute("alert", alert);
 	 				creaIstruttoreDaZero = "1";
 	 				currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 				response.sendRedirect("creaIstruttore.jsp");
 	 			}
 	 			else {
 	 				int idUtente;
 	 				if(utenteSelezionato.substring(1, 2).equals(" "))
 	 					idUtente = Integer.parseInt(utenteSelezionato.substring(0, 1));
 	 				else
 	 					idUtente = Integer.parseInt(utenteSelezionato.substring(0, 2));
 	 				//Utente u = UtenteDao.selectUser(idUtente);
 	 				Utente u = Utente.getUtente(idUtente);


 	 				Istruttore i = new Istruttore (idUtente, u.getUsername(), u.getPassword(), u.getNome(), u.getCognome(),
 	 						u.getDataNascita(), u.getEmail(), u.getCellulare(), "istruttore", sport, descrizione);
 	 				//IstruttoreDao.insertIstr(i);
 	 				Amministratore.creaIstruttore(i);
 	 				
 	 				//UtenteDao.updateRuoloUtente("istruttore", idUtente);
 	 				Utente.updateRuoloUtente("istruttore", idUtente);
 	 				
 	 				
 	 				
 	 				List<Istruttore> listaIstruttori = Istruttore.getTuttiIstruttori();
 	 				//List<Istruttore> listaIstruttori = IstruttoreDao.selectIstruttori();
 	 				currentsession.setAttribute("listaIstruttori", listaIstruttori);

 	 				alert = "29";
 	 				currentsession.setAttribute("alert", alert);
 	 				response.sendRedirect("home.jsp");
 	 				//response.sendRedirect("adminHome.jsp");
 	 			}

 	 		}
 	 		else if (creaIstruttoreDaZero.equals("4")) {
 	 			String sport = request.getParameter("sport");
 	 			String descrizione = request.getParameter("descrizioneCorso");
 	 			String nome = request.getParameter("nome");
 	 			String cognome = request.getParameter("cognome");
 	 			String dataNascita = request.getParameter("dataNascita");
 	 			String email = request.getParameter("email");
 	 			String cellulare = request.getParameter("cellulare");
 	 			String username = request.getParameter("username");
 	 			String password = request.getParameter("password");
 	 			String confermaPassword = request.getParameter("confermaPassword");		//check password...

 	 			if (username.isEmpty() || password.isEmpty() || confermaPassword.isEmpty() || nome.isEmpty() || cognome.isEmpty() || 
 	 					email.isEmpty() || cellulare.isEmpty() || dataNascita.isEmpty() || descrizione.isEmpty() ) {
 	 				alert = "34";	
 	 				currentsession.setAttribute("alert", alert);
 	 				creaIstruttoreDaZero = "2";
 	 				currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 				response.sendRedirect("creaIstruttore.jsp");
 	 			}
 	 			//tutti i campi sono non vuoti
 	 			else {
 	 				if (!(password.equals(confermaPassword))) {
 	 					alert="35";
 	 					currentsession.setAttribute("alert", alert);
 	 					creaIstruttoreDaZero = "2";
 	 					currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 					response.sendRedirect("creaIstruttore.jsp");
 	 				}
 	 				else{

 	 					//Utente u =  UtenteDao.selectUser(username);  
 	 					Utente u = Utente.getUtente(username);
 	 					//esiste già un utente con questo username...
 	 					if (u != null) {
 	 						//se si tratta dello stesso identico utente avrà tutti i dati uguali...
 	 						if(u.getPassword().equals(password) && u.getNome().equals(nome) && u.getCognome().equals(cognome)
 	 								&& u.getDataNascita().equals(dataNascita) && u.getEmail().equals(email) && u.getCellulare().equals(cellulare)) {
 	 							if (u.getRuolo().equalsIgnoreCase("user")) {
 	 								alert = "41";
 	 								currentsession.setAttribute("alert", alert);
 	 								creaIstruttoreDaZero = "0";
 	 								currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 								response.sendRedirect("creaIstruttore.jsp");
 	 							}
 	 							else if(u.getRuolo().equalsIgnoreCase("istruttore")) {
 	 								alert = "36";
 	 								currentsession.setAttribute("alert", alert);
 	 								creaIstruttoreDaZero = "2";
 	 								currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 								response.sendRedirect("creaIstruttore.jsp");
 	 							}
 	 							
 	 						
 	 						}
 	 						else {
 	 							alert = "37";	//due utenti diversi, con stesso username
 	 							currentsession.setAttribute("alert", alert);
 	 							creaIstruttoreDaZero = "2";
 	 							currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 							response.sendRedirect("creaIstruttore.jsp");
 	 						}

 	 					} 
 	 				
 	 				
 	 					//se non esiste già un utente con quell'username
 	 					else {
 	 						//------------------------------------
 	 						// Check sul cellulare e sull' email
 	 						//------------------------------------
 	 						if(!checkIsNumero(cellulare)) {
 	 							alert="38";
 	 							currentsession.setAttribute("alert", alert);
 	 							creaIstruttoreDaZero = "2";
 	 							currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 							response.sendRedirect("creaIstruttore.jsp");
 	 						}
 	 						else {
 	 							if(!mailSyntaxCheck(email)) {
 	 								
 	 								alert="39";
 	 								currentsession.setAttribute("alert", alert);
 	 								creaIstruttoreDaZero = "2";
 	 								currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 								response.sendRedirect("creaIstruttore.jsp");
 	 							}
 	 							else {
 	 								//------------------------------------
 	 								// Check sulla data di nascita
 	 								//------------------------------------
 	 				
 	 								String data_anno = dataNascita.substring(0,4);
 	 								String data_mese = dataNascita.substring(5,7);
 	 								String data_giorno = dataNascita.substring(8);
 	 								System.out.println(data_anno);
 	 								System.out.println(data_mese);
 	 								System.out.println(data_giorno);
 	 				
 	 								int anno = Integer.parseInt(data_anno);
 	 								int mese = Integer.parseInt(data_mese);
 	 								int giorno = Integer.parseInt(data_giorno);
 	 								System.out.println("anno: " + anno + " mese: " + mese + " giorno: " + giorno);


 	 				
 	 								LocalDate data = LocalDate.of(anno, mese, giorno);
 	 								LocalDate oggi = LocalDate.now();
 	 								boolean verify = data.isAfter(oggi);
 	 								if (verify == true) {		
 	 									System.out.println("utente nato dal futuro");
 	 				
 	 									alert = "40";
 	 									currentsession.setAttribute("alert", alert);
 	 									creaIstruttoreDaZero = "2";
 	 									currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 	 									response.sendRedirect("creaIstruttore.jsp");
 	 								}
 	 								else {
 	 									Utente utente = new Utente(username, password, nome, cognome, dataNascita, email, cellulare, "istruttore");
 	 									//UtenteDao.insertUser(utente);
 	 									ComplessoSportivo.registraUtente(utente);
 	 									
 	 									Istruttore i = new Istruttore(username, password, nome, cognome, dataNascita, email, cellulare, "istruttore", sport, descrizione);
 	 									//IstruttoreDao.insertIstr(i);
 	 									Amministratore.creaIstruttore(i);
 	 									
 	 									//List<Istruttore> listaIstruttori = IstruttoreDao.selectIstruttori();
 	 									List<Istruttore> listaIstruttori = Istruttore.getTuttiIstruttori();
 	 									currentsession.setAttribute("listaIstruttori", listaIstruttori);

 	 									alert = "29";
 	 									currentsession.setAttribute("alert", alert);
 	 									response.sendRedirect("home.jsp");
 	 									//response.sendRedirect("adminHome.jsp");
 	 								}
 	 							}
 	 						}
 	 						
 	 					}
 	 					

 	 					
 	 				}
 	 			}


 	 		}
 	 		else if(tipoInserimento.equals("utenteRegistrato")) {
 	 			creaIstruttoreDaZero = "1";
 	 			List<Utente> utentiRegistrati = ComplessoSportivo.getListaUtenti();
 	 			/*
 	 			List<Utente> utentiRegistrati = UtenteDao.selectUsers();
 	 			for (int i=0; i<utentiRegistrati.size(); i++) {
 	 				if(!utentiRegistrati.get(i).getRuolo().equalsIgnoreCase("user"))
 	 					utentiRegistrati.remove(i);
 	 			}
 	 			for (Utente u: utentiRegistrati)
 	 				System.out.println(u);
 	 			 */
 	 			currentsession.setAttribute("utentiRegistrati", utentiRegistrati);
 	 			response.sendRedirect("creaIstruttore.jsp");
 	 		}
 	 		else if (tipoInserimento.equalsIgnoreCase("nuovoUtente")) {
 	 			response.sendRedirect("creaIstruttore.jsp");
 	 			creaIstruttoreDaZero = "2";
 	 		}

 	 		currentsession.setAttribute("creaIstruttoreDaZero", creaIstruttoreDaZero);
 		}
 		
 		else if (azione.equalsIgnoreCase("modifica")) {
 			HttpSession currentSession = request.getSession();
 			
 			String modificaIstruttore = (String)currentSession.getAttribute("modificaIstruttore");
 			
 			//prima volta (selezione dell'istruttore da modificare)
 			if(modificaIstruttore == "0") {
 				String infoIstruttore = request.getParameter("istruttore");
 				int idIstruttore;
 				if(infoIstruttore.substring(1, 2).equals(" "))
 					idIstruttore = Integer.parseInt(infoIstruttore.substring(0, 1));
 				else
 					idIstruttore = Integer.parseInt(infoIstruttore.substring(0, 2));
 				//Istruttore i = IstruttoreDao.selectIstruttore(idIstruttore);
 				Istruttore i = Istruttore.getIstruttore(idIstruttore);
 				
 				currentSession.setAttribute("datiIstruttore", i);
 				currentSession.setAttribute("modificaIstruttore", "1");
 				response.sendRedirect("modificaDatiIstruttore.jsp");
 			}
 			
 			//seconda volta (per la modifica vera e propria dei dati dell'istruttore)
 			else if (modificaIstruttore == "1") {
 				currentSession.setAttribute("modificaIstruttore", "0");
 				int id = Integer.parseInt(request.getParameter("id"));
 				String sport = request.getParameter("sport");
 				String descrizione = request.getParameter("descrizioneIstruttore");
 				
 				
 				if(descrizione.length()==0) {
 					
 					currentSession.setAttribute("alert", "43");
 					
 					currentSession.setAttribute("modificaIstruttore", "0");
 					response.sendRedirect("modificaDatiIstruttore.jsp");
 				}
 				else {
 					
 					if(Istruttore.modificaIstruttore(sport, descrizione, id)) {
 						//List<Istruttore> listaIstruttori = IstruttoreDao.selectIstruttori();
 						List<Istruttore> listaIstruttori = Istruttore.getTuttiIstruttori();
 						currentSession.setAttribute("listaIstruttori", listaIstruttori);
 						
 						currentSession.setAttribute("modificaIstruttore", "0");
 						currentSession.setAttribute("alert", "32");
 						response.sendRedirect("home.jsp");
 						//response.sendRedirect("adminHome.jsp");					
 					}
 					else {
 						currentSession.setAttribute("alert", "44");
 						response.sendRedirect("modificaDatiIstruttore.jsp");
 					}
 					
 					
 				}
 				
 				
 			}
 		}
 		
 		else if (azione.equalsIgnoreCase("elimina")) {
 			HttpSession currentSession = request.getSession();
 			
 			String infoIstruttore = request.getParameter("istruttore");
 			int idIstruttore;
 			if(infoIstruttore.substring(1, 2).equals(" "))
 				idIstruttore = Integer.parseInt(infoIstruttore.substring(0, 1));
 			else
 				idIstruttore = Integer.parseInt(infoIstruttore.substring(0, 2));
 			if(Amministratore.eliminaIstruttore(idIstruttore)) {
 				List<Istruttore> listaIstruttori = Istruttore.getTuttiIstruttori();
 				//List<Istruttore> listaIstruttori= IstruttoreDao.selectIstruttori();
 				currentSession.setAttribute("listaIstruttori", listaIstruttori);
 				
 				//
 				if(ComplessoSportivo.eliminaCorsiIstruttore(idIstruttore)) {
 					currentSession.setAttribute("alert", "33");
 					response.sendRedirect("home.jsp");
 					//response.sendRedirect("adminHome.jsp");
 				}
 				else {
 					currentSession.setAttribute("alert", "46");
 					response.sendRedirect("eliminaIstruttore.jsp");
 				}
 				
 			}
 			else {
 				currentSession.setAttribute("alert", "45");
 				response.sendRedirect("eliminaIstruttore.jsp");
 			}
 		}


 	}

 	/**
 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		doGet(request, response);
 	}

 }