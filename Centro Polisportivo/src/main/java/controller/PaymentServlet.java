package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Abbonamento;
import model.Campo;
import model.ComplessoSportivo;
import model.Corso;

import model.Prenotazione;

import model.Utente;
import util.Calendario;
/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String alert = "0";		//avviso di allarme
		//------------------Possibili inconvenienti------------------
		// alert = 0 -> Ok, no errori.
		// alert = 1 -> Campi *obligatori*! 
		// alert = 2 -> CVV e Card Number sono costituiti da sole cifre!
		// alert = 3 -> CVV e Card Number non hanno un formato corretto!
		// alert = 4 -> Circuito di credito non riconosciuto.
		// alert = 5 -> Carta di Credito scaduta.
		//------------------------------------------
		
		boolean ok = true;		//variabile locale per verificare la correttezza degli input
		
		//------------------------------------
		// Prelevo l'input dalla View.
		//------------------------------------
		String cardholder = (String) request.getParameter("cardholder");
		String cardNumber = (String)request.getParameter("cardNumber");
		
		String mouth = (String) request.getParameter("mouth");
		String year = (String) request.getParameter("year");
		String cvv = (String) request.getParameter("CVV");	
		
		System.out.println(cardholder + " " + cardNumber + " " + mouth + " " + year + " " + cvv);
		
		//------------------------------------
		// Check campi obligatori
		//------------------------------------
		if (cardholder.isEmpty() || cardNumber.isEmpty() || cardNumber.length() != 16 || mouth.isEmpty() || year.isEmpty() || cvv.isEmpty()) {
			ok = false;
			alert="1";
		}else {
			
			String cd1 = cardNumber.substring(0,4);
			String cd2 = cardNumber.substring(4,8);
			String cd3 = cardNumber.substring(8,12);
			String cd4 = cardNumber.substring(12,16);
			
			//------------------------------------
			// Check affinchè CVV e Card Number siano costituiti da sole cifre.
			//------------------------------------
			char c;
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			int count5 = 0;
			for (int i = 0; i < cd1.length() ; i++) {  
	        	c = cd1.charAt(i);  
	        	if (!Character.isDigit(c)) { 
					ok = false;
					alert="2";
	            }
	        	count1 ++;
	        }
			for (int i = 0;ok == true && i < cd2.length() ; i++) {  
	        	c = cd2.charAt(i);  
	        	if (!Character.isDigit(c)) { 
					ok = false;
					alert="2";
	            }
	        	count2 ++;
	        }
			for (int i = 0;ok == true && i < cd3.length() ; i++) {  
	        	c = cd3.charAt(i);  
	        	if (!Character.isDigit(c)) { 
					ok = false;
					alert="2";
	            }
	        	count3 ++;
	        }
			for (int i = 0;ok == true && i < cd4.length() ; i++) {  
	        	c = cd4.charAt(i);  
	        	if (!Character.isDigit(c)) { 
					ok = false;
					alert="2";
	            }
	        	count4 ++;
	        }
			for (int i = 0;ok == true && i < cvv.length() ; i++) {  
	        	c = cvv.charAt(i);  
	        	if (!Character.isDigit(c)) { 
					ok = false;
					alert="2";
	            }
	        	count5 ++;
	        }
			
			if ( ok == true) {
				System.out.println(cd1.length());
				System.out.println(cd2.length());
				System.out.println(cd3.length());
				System.out.println(cd4.length());
				System.out.println(cvv.length());
				System.out.println();
				System.out.println(count1);
				System.out.println(count2);
				System.out.println(count3);
				System.out.println(count4);
				System.out.println(count5);
				
				//------------------------------------
				// Check affinchè CVV e Card Number abbiano un formato corretto.
				//------------------------------------
				if(count1 != 4 || count2 != 4 ||count3 != 4 ||count4 != 4 || count5 != 3) {
					ok = false;
					alert="3";
				}
				//------------------------------------
				// Check affinchè il circuito di credito sia riconosciuto:
				// le carte di credito VISA iniziano con il numero 4
				// le carte di credito MasterCard iniziano con il numero 5 oppure il 2
				// le carte di credito Diners Club iniziano con il numero 3
				// le carte di credito American Express iniziano con il numero 3
				// le carte di credito Discover Card iniziano con il numero 6
				//------------------------------------
				// Effettuo il parsing per renderli degli interi
				int card1 = Integer.parseInt(cd1);
				int mese = Integer.parseInt(mouth);
				int anno = Integer.parseInt(year);
				if ( ok == true && (card1<2000 || card1>6999)) {
					ok = false;
					alert="4";
				}
				//------------------------------------
				// Check affinchè la card non sia scaduta.
				//------------------------------------
				try {
					// Effettuo il parsing per lavorare con la data
					SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
					String data = new String(anno+"-"+mese+"-01");
					Date scad = dateParser.parse(data);
					Calendar calendar = Calendar.getInstance();
		 	        Date oggi = calendar.getTime();
		 	        if ( ok == true && (oggi.after(scad))) {
						ok = false;
						alert = "5";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
		 	}
		}
		
		HttpSession currentsession = request.getSession();
				
		if (ok == true) {
			//------------------------------------
			//SUCCESSO. Pagamento corretto. Posso proseguire con la registrazione della PRENOTAZIONE.
			//------------------------------------
			
			//TIPOLOGIA PAGAMENTO
			//1. PAGAMENTO PRENOTAZIONE PARTITA/E
			//2. PAGAMENTO ABBONAMENTO
			int tipologiaPagamento = Integer.parseInt((String)currentsession.getAttribute("tipologiaPagamento"));
			
			if(tipologiaPagamento == 1) {
				Prenotazione p = (Prenotazione)currentsession.getAttribute("prenotazione");
				Campo.registraPrenotazione(p);
				//PrenotazioneDao.insertPrenotazione(p);
				
				int oraPartita = p.getOraInizio();
				Utente u = Utente.getUtente(p.getUtente().getId());
				//Utente u = UtenteDao.selectUser(p.getUtente().getId());
				//Campo c = CampoDao.selectCampo(p.getCampo().getId());
				Campo c = ComplessoSportivo.getCampo(p.getCampo().getId());
				
				String campoFisso = (String)currentsession.getAttribute("campoFisso");
				String dataPartita = (String)currentsession.getAttribute("dataPartita");
				String data_anno, data_mese, data_giorno;
				int anno, mese, giorno;
				LocalDate data;
				if(campoFisso != null) {
					if(campoFisso.equalsIgnoreCase("campoFisso")) {
						Calendario cal = new Calendario();
						data_anno = dataPartita.substring(6);
						data_mese = dataPartita.substring(3,5);
						data_giorno = dataPartita.substring(0,2);
						anno = Integer.parseInt(data_anno);
						mese = Integer.parseInt(data_mese);
						giorno = Integer.parseInt(data_giorno);
						data = LocalDate.of(anno, mese, giorno);
						String date = data_giorno + "-"+data_mese +"-" + data_anno;
						if(cal.getLunedi().contains(date)) {
							for(String s: cal.getLunedi()) {
								int giornoLunedi = Integer.parseInt(s.substring(0, 2));
								int meseLunedi = Integer.parseInt(s.substring(3, 5));
								int annoLunedi = Integer.parseInt(s.substring(6));
								LocalDate dataLunedi = LocalDate.of(annoLunedi, meseLunedi, giornoLunedi);
								if(dataLunedi.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						else if(cal.getMartedi().contains(date)) {
							for(String s: cal.getMartedi()) {
								int giornoMartedi = Integer.parseInt(s.substring(0, 2));
								int meseMartedi = Integer.parseInt(s.substring(3, 5));
								int annoMartedi = Integer.parseInt(s.substring(6));
								LocalDate dataMartedi = LocalDate.of(annoMartedi, meseMartedi, giornoMartedi);
								if(dataMartedi.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						if(cal.getMercoledi().contains(date)) {
							for(String s: cal.getMercoledi()) {
								int giornoMercoledi = Integer.parseInt(s.substring(0, 2));
								int meseMercoledi = Integer.parseInt(s.substring(3, 5));
								int annoMercoledi = Integer.parseInt(s.substring(6));
								LocalDate dataMercoledi = LocalDate.of(annoMercoledi, meseMercoledi, giornoMercoledi);
								if(dataMercoledi.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						if(cal.getGiovedi().contains(date)) {
							for(String s: cal.getGiovedi()) {
								int giornoGiovedi = Integer.parseInt(s.substring(0, 2));
								int meseGiovedi = Integer.parseInt(s.substring(3, 5));
								int annoGiovedi = Integer.parseInt(s.substring(6));
								LocalDate dataGiovedi = LocalDate.of(annoGiovedi, meseGiovedi, giornoGiovedi);
								if(dataGiovedi.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						if(cal.getVenerdi().contains(date)) {
							for(String s: cal.getVenerdi()) {
								int giornoVenerdi = Integer.parseInt(s.substring(0, 2));
								int meseVenerdi = Integer.parseInt(s.substring(3, 5));
								int annoVenerdi = Integer.parseInt(s.substring(6));
								LocalDate dataVenerdi = LocalDate.of(annoVenerdi, meseVenerdi, giornoVenerdi);
								if(dataVenerdi.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						if(cal.getSabato().contains(date)) {
							for(String s: cal.getSabato()) {
								int giornoSabato = Integer.parseInt(s.substring(0, 2));
								int meseSabato = Integer.parseInt(s.substring(3, 5));
								int annoSabato = Integer.parseInt(s.substring(6));
								LocalDate dataSabato = LocalDate.of(annoSabato, meseSabato, giornoSabato);
								if(dataSabato.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						if(cal.getDomenica().contains(date)) {
							for(String s: cal.getDomenica()) {
								int giornoDomenica = Integer.parseInt(s.substring(0, 2));
								int meseDomenica = Integer.parseInt(s.substring(3, 5));
								int annoDomenica = Integer.parseInt(s.substring(6));
								LocalDate dataDomenica = LocalDate.of(annoDomenica, meseDomenica, giornoDomenica);
								if(dataDomenica.isAfter(data)) {
									//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1));
									p = new Prenotazione(u, new Corso(), c, s, oraPartita, oraPartita + 1);
									Campo.registraPrenotazione(p);
								}
							}
								
						}
						
						
					}
					
					List<Prenotazione> listaPrenotazioni;
					try {
						listaPrenotazioni = ComplessoSportivo.recuperaPrenotazioni();
						//listaPrenotazioni = PrenotazioneDao.selectPrenotazioni();
						currentsession.setAttribute("listaPrenotazioni", listaPrenotazioni);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				alert = "1";
				currentsession.setAttribute("alert", alert);
			}
			else if (tipologiaPagamento == 2) {
				Abbonamento a = (Abbonamento)currentsession.getAttribute("abbonamento");
				//AbbonamentoDao.insertAbbonamento(a);
				Corso.registraAbbonamento(a);
				List<Abbonamento> listaAbbonamenti = ComplessoSportivo.recuperaAbbonamenti();
				//List<Abbonamento> listaAbbonamenti = AbbonamentoDao.selectAbbonamenti();
				currentsession.setAttribute("listaAbbonamenti", listaAbbonamenti);
				alert = "2";
				currentsession.setAttribute("alert", alert);
			}
			
			
			
			response.sendRedirect("home.jsp");
			//response.sendRedirect("userHome.jsp");
			
			
		}
		else {
			currentsession.setAttribute("alert", alert);
			response.sendRedirect("pagamento.jsp");
		}
		
		System.out.println("alert: " + alert);
	}
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}