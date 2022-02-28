package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CorsoDao;
import model.Abbonamento;
import model.Campo;
import model.ComplessoSportivo;
import model.Corso;
import model.Istruttore;
import model.Prenotazione;
import model.Utente;
import util.Calendario;

/**
 * Servlet implementation class GestioneCorsiServlet
 */
@WebServlet("/GestioneCorsiServlet")
public class GestioneCorsiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneCorsiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String action = request.getServletPath();
		

		String alert = "14";	//tutto ok
		String azione = request.getParameter("azioneCorso");

		if(azione.equalsIgnoreCase("crea")) {
			HttpSession currentsession = request.getSession();
			
			String sport = request.getParameter("sport");
			String descrizioneCorso = request.getParameter("descrizioneCorso");
			int numeroLezioni = Integer.parseInt(request.getParameter("numeroLezioni"));
			//int postiDisponibili = Integer.parseInt(request.getParameter("postiDisponibili"));
			String postiDisponibili = request.getParameter("postiDisponibili");
			String prezzoMensile = request.getParameter("prezzoMensile");
			//int prezzoMensile = Integer.parseInt(request.getParameter("prezzoMensile"));

			
			
				

				//controllo che il campo descrizione corso non sia vuoto
				if(descrizioneCorso.length() == 0) {
					alert = "16";	
					currentsession.setAttribute("alert", alert);
					response.sendRedirect("inserisciCorso.jsp");
				}
				//controllo che gli input inseriti siano non vuoti e siano interi (prezzo mensile e numero di posti disponibili)
				else if (!checkIsNumero(prezzoMensile) || !checkIsNumero(postiDisponibili)) {
					alert = "17";
					currentsession.setAttribute("alert", alert);
					response.sendRedirect("inserisciCorso.jsp");
				}

				//se non ci sono degli errori negli input
				else {

					Utente u = (Utente)currentsession.getAttribute("utente");
					int idIstruttore = u.getId();
					
					Istruttore i = Istruttore.getIstruttore(idIstruttore);
					
					String sportIstruttore = i.getSport(); 

					//controllo compatibilità tra sport di cui si vuole creare il corso e lo sport dell'istruttore
					if (!sportIstruttore.equalsIgnoreCase(sport)) {
						alert = "19";	
						currentsession.setAttribute("alert", alert);
						response.sendRedirect("inserisciCorso.jsp");
					}
					else {
						String [] giorni = request.getParameterValues("giorno");
						
						
						
						if(giorni == null) {
							alert = "21";	
							currentsession.setAttribute("alert", alert);
							response.sendRedirect("inserisciCorso.jsp");
						}
						//controllo che il numero di lezioni settimanali sia coerente col numero di giorni selezionati
						else if(giorni.length != numeroLezioni) {
							alert = "20";	
							currentsession.setAttribute("alert", alert);
							response.sendRedirect("inserisciCorso.jsp");
						}
						else {
							
							int [] giorniLezione = {0, 0, 0, 0, 0};
							String [] orariLezioni = {"-", "-", "-", "-", "-"};
							String orarioLunedi = request.getParameter("orarioLunedi");
							String orarioMartedi = request.getParameter("orarioMartedi");
							String orarioMercoledi = request.getParameter("orarioMercoledi");
							String orarioGiovedi = request.getParameter("orarioGiovedi");
							String orarioVenerdi = request.getParameter("orarioVenerdi");
							for (String s : giorni) {
								if(s.equalsIgnoreCase("lunedi")) {
									giorniLezione[0] = 1;
									orariLezioni[0] = orarioLunedi;
								}
									
								else if(s.equalsIgnoreCase("martedi")) {
									giorniLezione[1] = 1;
									orariLezioni[1] = orarioMartedi;
								}
									
								else if(s.equalsIgnoreCase("mercoledi")) {
									giorniLezione[2] = 1;
									orariLezioni[2] = orarioMercoledi;
								}
									
								else if(s.equalsIgnoreCase("giovedi")) {
									giorniLezione[3] = 1;
									orariLezioni[3] = orarioGiovedi;
								}
									
								else if(s.equalsIgnoreCase("venerdi")) {
									giorniLezione[4] = 1;
									orariLezioni[4] = orarioVenerdi;
								}
									
							}
							
							String[] orari = new String[numeroLezioni];
							int j=0;
							for(int indice=0; indice<5; indice++) {
								if(!orariLezioni[indice].contentEquals("-")) {
									orari[j] = orariLezioni[indice];
									j++;
								}
							}

							
							Calendario cal = new Calendario();
							
							String primoLunedi = cal.getLunedi().get(0);
							String primoMartedi = cal.getMartedi().get(0);
							String primoMercoledi = cal.getMercoledi().get(0);
							String primoGiovedi = cal.getGiovedi().get(0);
							String primoVenerdi = cal.getVenerdi().get(0);
							
							boolean impossibilePrenotare = false;
							
							int indice = 0;
							int idCampo;
							if(sport.equalsIgnoreCase("paddel"))
								idCampo = 3;
							else
								idCampo = 5;
							
							while(indice<giorni.length && !impossibilePrenotare) {
								if(giorni[indice].equalsIgnoreCase("lunedi")) {
									impossibilePrenotare = ComplessoSportivo.verificaDisponibilitaDataOraPrenotazione(idCampo, primoLunedi, Integer.parseInt(orarioLunedi.substring(0,2)) , Integer.parseInt(orarioLunedi.substring(3)));
									//impossibilePrenotare = PrenotazioneDao.selectPrenotazione(idCampo, primoLunedi, Integer.parseInt(orarioLunedi.substring(0,2)) , Integer.parseInt(orarioLunedi.substring(3)));
								}
								else if(giorni[indice].equalsIgnoreCase("martedi")) {
									impossibilePrenotare = ComplessoSportivo.verificaDisponibilitaDataOraPrenotazione(idCampo, primoMartedi, Integer.parseInt(orarioMartedi.substring(0,2)) , Integer.parseInt(orarioMartedi.substring(3)));
								}
								else if(giorni[indice].equalsIgnoreCase("mercoledi")) {
									impossibilePrenotare = ComplessoSportivo.verificaDisponibilitaDataOraPrenotazione(idCampo, primoMercoledi, Integer.parseInt(orarioMercoledi.substring(0,2)) , Integer.parseInt(orarioMercoledi.substring(3)));
								}
								else if(giorni[indice].equalsIgnoreCase("giovedi")) {
									impossibilePrenotare = ComplessoSportivo.verificaDisponibilitaDataOraPrenotazione(idCampo, primoGiovedi, Integer.parseInt(orarioGiovedi.substring(0,2)) , Integer.parseInt(orarioGiovedi.substring(3)));
								}
								else if(giorni[indice].equalsIgnoreCase("venerdi")) {
									impossibilePrenotare = ComplessoSportivo.verificaDisponibilitaDataOraPrenotazione(idCampo, primoVenerdi, Integer.parseInt(orarioVenerdi.substring(0,2)) , Integer.parseInt(orarioVenerdi.substring(3)));
								}
								
								indice++;
							}
							
							
							if(impossibilePrenotare == true) {
								System.out.println("IMPOSSIBILE PRENOTARE, campi occupati");
								alert = "22";	
								currentsession.setAttribute("alert", alert);
								response.sendRedirect("inserisciCorso.jsp");
							}
							else {
								System.out.println("VIA LIBERA");
								int prezzo= Integer.parseInt(prezzoMensile);
								int p = Integer.parseInt(postiDisponibili);
								int [] posti = {p, p, p, p, p, p, p, p, p, p, p, p};
								Corso c = new Corso(sport, i, descrizioneCorso, numeroLezioni, giorniLezione, orariLezioni, posti, prezzo);
								//se il corso viene creato con successo
								if(ComplessoSportivo.creaCorso(c)) {
									//List<Corso> listaCorsi = CorsoDao.selectCorsi();
									List<Corso> listaCorsi = ComplessoSportivo.getInfoCorsi();
									currentsession.setAttribute("listaCorsi", listaCorsi);
									
									//Corso corso = CorsoDao.selectCorso();
									Corso corso = ComplessoSportivo.getCorsoAppenaCreato();
									
									//dopo aver creato il corso, bisogna prenotare i campi per le lezioni...
									
									int prezzoCampo, oraInizioDisponibilità, oraFineDisponibilità;
									if(c.getSport().equalsIgnoreCase("calcio")) {
										prezzoCampo = 50;
										oraInizioDisponibilità = 15;
										oraFineDisponibilità = 24;
									}
									else {
										prezzoCampo = 40;
										oraInizioDisponibilità = 15;
										oraFineDisponibilità = 21;
									}
									
									Campo campo = new Campo(idCampo, c.getSport(), prezzoCampo, oraInizioDisponibilità, oraFineDisponibilità);
									
									
									String data="";
									System.out.println("NUMERO LEZIONI: " + numeroLezioni);
									System.out.println("GIORNI: ");
									for(String s: giorni)
										System.out.println(s);
									
									for(int ii=0; ii<numeroLezioni; ii++) {
										//System.out.println("ENTRATO NEL PRIMO CICLO FOR");
										for(int iii = 0; iii<giorni.length; iii++) {
											//System.out.println("ENTRATO NEL SECONDO CICLO FOR");
											if(giorni[iii].equalsIgnoreCase("lunedi")) {
												for(int iiii=0; iiii<cal.getLunedi().size(); iiii++) {
													data = cal.getLunedi().get(iiii);
													Prenotazione pre = new Prenotazione(corso, campo, data, Integer.parseInt(orari[ii].substring(0,2)), Integer.parseInt(orari[ii].substring(3)) );											
													Campo.registraPrenotazione(pre);
													//PrenotazioneDao.insertPrenotazione(pre);
												}
												
											}
											else if(giorni[iii].equalsIgnoreCase("martedi")) {
												for(int iiii=0; iiii<cal.getMartedi().size(); iiii++) {
													data = cal.getMartedi().get(iiii);
													Prenotazione pre = new Prenotazione(corso, campo, data, Integer.parseInt(orari[ii].substring(0,2)), Integer.parseInt(orari[ii].substring(3)) );											
													Campo.registraPrenotazione(pre);
													//PrenotazioneDao.insertPrenotazione(pre);
												}
												
											}
											else if(giorni[iii].equalsIgnoreCase("mercoledi")) {
												for(int iiii=0; iiii<cal.getMercoledi().size(); iiii++) {
													data = cal.getMercoledi().get(iiii);
													Prenotazione pre = new Prenotazione(corso, campo, data, Integer.parseInt(orari[ii].substring(0,2)), Integer.parseInt(orari[ii].substring(3)) );											
													Campo.registraPrenotazione(pre);
													//PrenotazioneDao.insertPrenotazione(pre);
												}
												
											}
											else if(giorni[iii].equalsIgnoreCase("giovedi")) {
												for(int iiii=0; iiii<cal.getGiovedi().size(); iiii++) {
													data = cal.getGiovedi().get(iiii);
													Prenotazione pre = new Prenotazione(corso, campo, data, Integer.parseInt(orari[ii].substring(0,2)), Integer.parseInt(orari[ii].substring(3)) );											
													Campo.registraPrenotazione(pre);
													//PrenotazioneDao.insertPrenotazione(pre);
												}
												
											}
											else if(giorni[iii].equalsIgnoreCase("venerdi")) {
												for(int iiii=0; iiii<cal.getVenerdi().size(); iiii++) {
													data = cal.getVenerdi().get(iiii);
													Prenotazione pre = new Prenotazione(corso, campo, data, Integer.parseInt(orari[ii].substring(0,2)), Integer.parseInt(orari[ii].substring(3)) );											
													Campo.registraPrenotazione(pre);
													//PrenotazioneDao.insertPrenotazione(pre);
												}
												
											}
											
										}
										
									}
									
									List<Prenotazione> listaPrenotazioni;
									try {
										//listaPrenotazioni = PrenotazioneDao.selectPrenotazioni();
										listaPrenotazioni = ComplessoSportivo.recuperaPrenotazioni();
										currentsession.setAttribute("listaPrenotazioni", listaPrenotazioni);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
									
									
									currentsession.setAttribute("alert", alert);
									response.sendRedirect("home.jsp");
									//response.sendRedirect("istruttoreHome.jsp");
								}
								//se qualcosa va storto...
								else {
									alert = "18";
									currentsession.setAttribute("alert", alert);
									response.sendRedirect("inserisciCorso.jsp");
								}
							}
							

							
						}
						
					}

					
				}
		}	
		
		
		else if (azione.equalsIgnoreCase("modifica")) {
			HttpSession currentSession = request.getSession();
			
			
			String modificaCorso = (String)currentSession.getAttribute("modificaCorso");
			
			Utente u = (Utente)currentSession.getAttribute("utente");
			String ruolo = u.getRuolo();
			
			if(modificaCorso == "0") {
				String corso = request.getParameter("corso");
				int idCorso;
				if(corso.substring(1, 2).equals(" "))
					idCorso = Integer.parseInt(corso.substring(0, 1));
				else
					idCorso = Integer.parseInt(corso.substring(0, 2));
				
				int idIstruttore;
				//Corso c = CorsoDao.selectCorso(idCorso);
				Corso c = ComplessoSportivo.getCorso(idCorso);
				if(ruolo.equalsIgnoreCase("istruttore")) {
					idIstruttore = u.getId();
					
					if(c.getIstruttore().getId() == idIstruttore) {
						
						
						currentSession.setAttribute("datiCorso", c);
						currentSession.setAttribute("modificaCorso", "1");
						response.sendRedirect("modificaCorso.jsp");
					}
					else {
						currentSession.setAttribute("alert", "15");
						response.sendRedirect("home.jsp");
						//response.sendRedirect("istruttoreHome.jsp");
					}
				}
				
				else if (ruolo.equalsIgnoreCase("admin")) {
					List<Istruttore> listaIstruttori = Istruttore.getListaIstruttoriSport(c.getSport());
					//List<Istruttore> listaIstruttori = IstruttoreDao.selectIstruttori(c.getSport());
					currentSession.setAttribute("listaIstruttori", listaIstruttori);
					currentSession.setAttribute("datiCorso", c);
					currentSession.setAttribute("modificaCorso", "1");
					response.sendRedirect("modificaCorso.jsp");
				}
				
				
					
				
				
				
				
			}
			
			else if (modificaCorso == "1") {
				
				currentSession.setAttribute("modificaCorso", "0");
				String descrizione = request.getParameter("descrizioneCorso");
				String prezzo = request.getParameter("prezzoMensile");
				String numLezioni;
				int idIstruttore;
				
				//modifica da parte di admin
				if(ruolo.equalsIgnoreCase("admin")) {
					String datiIstruttore = request.getParameter("istruttore");

					if(datiIstruttore.substring(1, 2).equals(" "))
						idIstruttore = Integer.parseInt(datiIstruttore.substring(0, 1));
					else
						idIstruttore = Integer.parseInt(datiIstruttore.substring(0, 2));



					Corso c = (Corso)currentSession.getAttribute("datiCorso");
					int idCorso = c.getId();

					

					if(ComplessoSportivo.modificaDatiCorso(idIstruttore, c.getDescrizione(), c.getNumeroLezioniSettimanali(), c.getGiorniLezioni(), c.getOrariLezioni(), c.getPostiDisponibili(), c.getPrezzoMensile(), idCorso)) {
						List<Corso> listaCorsi = ComplessoSportivo.getInfoCorsi();
						
						currentSession.setAttribute("listaCorsi", listaCorsi);

						currentSession.setAttribute("modificaCorso", "0");
						currentSession.setAttribute("alert", "30");
						response.sendRedirect("home.jsp");
										
					}
					else {
						currentSession.setAttribute("alert", "26");
						response.sendRedirect("modificaCorso.jsp");
					}


				}
				
				//modifica da parte di istruttore
				else {
					idIstruttore = u.getId();
					numLezioni = request.getParameter("numeroLezioni");
					int numeroLezioni = Integer.parseInt(numLezioni);
					String [] giorni = request.getParameterValues("giorno");
					
					if(descrizione.length()==0 || (giorni.length != numeroLezioni) || (!checkIsNumero(prezzo) || !checkIsNumero(numLezioni))) {
						if(descrizione.length()==0) {
							currentSession.setAttribute("alert", "23");
							currentSession.setAttribute("modificaCorso", "0");
						}
							
						else if(giorni.length != numeroLezioni) {
							currentSession.setAttribute("alert", "27");
							currentSession.setAttribute("modificaCorso", "1");
						}
							
						else if(!checkIsNumero(prezzo)) {
							currentSession.setAttribute("alert", "24");
							currentSession.setAttribute("modificaCorso", "0");
						}
							
						else {
							currentSession.setAttribute("alert", "25");
							currentSession.setAttribute("modificaCorso", "0");
						}
							
						
						response.sendRedirect("modificaCorso.jsp");
					}
					else {
			
						
							int [] giorniLezione = {0, 0, 0, 0, 0};
							String [] orariLezioni = {"-", "-", "-", "-", "-"};
							String orarioLunedi = request.getParameter("orarioLunedi");
							String orarioMartedi = request.getParameter("orarioMartedi");
							String orarioMercoledi = request.getParameter("orarioMercoledi");
							String orarioGiovedi = request.getParameter("orarioGiovedi");
							String orarioVenerdi = request.getParameter("orarioVenerdi");
							for (String s : giorni) {
								if(s.equalsIgnoreCase("lunedi")) {
									giorniLezione[0] = 1;
									orariLezioni[0] = orarioLunedi;
								}
									
								else if(s.equalsIgnoreCase("martedi")) {
									giorniLezione[1] = 1;
									orariLezioni[1] = orarioMartedi;
								}
									
								else if(s.equalsIgnoreCase("mercoledi")) {
									giorniLezione[2] = 1;
									orariLezioni[2] = orarioMercoledi;
								}
									
								else if(s.equalsIgnoreCase("giovedi")) {
									giorniLezione[3] = 1;
									orariLezioni[3] = orarioGiovedi;
								}
									
								else if(s.equalsIgnoreCase("venerdi")) {
									giorniLezione[4] = 1;
									orariLezioni[4] = orarioVenerdi;
								}
									
							}
							
							Corso c = (Corso)currentSession.getAttribute("datiCorso");
							int idCorso = c.getId();
							
							int prezzoMensile = Integer.parseInt(prezzo);
							
							
							
							if(ComplessoSportivo.modificaDatiCorso(idIstruttore, descrizione, numeroLezioni, giorniLezione, orariLezioni, c.getPostiDisponibili(), prezzoMensile, idCorso)) {
								List<Corso> listaCorsi = ComplessoSportivo.getInfoCorsi();
								
								currentSession.setAttribute("listaCorsi", listaCorsi);
								
								currentSession.setAttribute("modificaCorso", "0");
								currentSession.setAttribute("alert", "28");
								response.sendRedirect("home.jsp");
										
							}
							else {
								currentSession.setAttribute("alert", "26");
								response.sendRedirect("modificaCorso.jsp");
							}
						
						
						
						
						
					}
				}
					
			
				
				
			}
		}
		
		else if (azione.equalsIgnoreCase("elimina")) {
			HttpSession currentSession = request.getSession();
			
			String corso = request.getParameter("corso");
			int idCorso;
			if(corso.substring(1, 2).equals(" "))
				idCorso = Integer.parseInt(corso.substring(0, 1));
			else
				idCorso = Integer.parseInt(corso.substring(0, 2));
			if(ComplessoSportivo.eliminaCorso(idCorso)) {
				List<Corso> listaCorsi = CorsoDao.selectCorsi();
				currentSession.setAttribute("listaCorsi", listaCorsi);
				
				Corso.eliminaTuttiAbbonamentiCorso(idCorso);
				Campo.annullaPrenotazioniCorso(idCorso);
				
				List<Abbonamento> listaAbbonamenti = ComplessoSportivo.recuperaAbbonamenti();
				currentSession.setAttribute("listaAbbonamenti", listaAbbonamenti);
				
				List<Prenotazione> listaPrenotazioni;
				try {
					
					listaPrenotazioni = ComplessoSportivo.recuperaPrenotazioni();
					currentSession.setAttribute("listaPrenotazioni", listaPrenotazioni);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				currentSession.setAttribute("alert", "31");
				response.sendRedirect("home.jsp");
				
			}
			else {
				currentSession.setAttribute("alert", "42");
				response.sendRedirect("eliminaCorso.jsp");
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

