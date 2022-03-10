package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Campo;
import model.ComplessoSportivo;
import model.Corso;
import model.Prenotazione;
import model.Utente;
import util.Calendario;

/**
 * Servlet implementation class PrenotazionePartitaServlet
 */
@WebServlet("/prenotazionePartita")
public class PrenotazionePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazionePartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String alert = "1";
		String dataPartita;
		String sport;
		String data_anno, data_mese, data_giorno;
		String campoFisso;
		String metodoPagamento;
		int anno, mese, giorno;
		Utente u;
		Campo c;
		LocalDate data;
		List<Prenotazione> prenotazioniDisponibili;
		int campo, oraPartita;
		
		
		HttpSession currentsession = request.getSession();
		
		String azione = request.getParameter("azione");
		
		String utenteNonRegistrato = (String)currentsession.getAttribute("utenteNonRegistrato");
		
		if(azione.equalsIgnoreCase("cercaCampetti")) {
		
			
			dataPartita = request.getParameter("dataPartita");
			sport = request.getParameter("sport");
			
			//verifico che l'utente abbia inserito in input una data
			if(dataPartita.length()==0) {
				alert = "5";
				currentsession.setAttribute("alert", alert);
				response.sendRedirect("prenotaPartita.jsp");
			}
			else {
				data_anno = dataPartita.substring(0,4);
				data_mese = dataPartita.substring(5,7);
				data_giorno = dataPartita.substring(8);
				anno = Integer.parseInt(data_anno);
				mese = Integer.parseInt(data_mese);
				giorno = Integer.parseInt(data_giorno);
				data = LocalDate.of(anno, mese, giorno);
				String date = data_giorno + "-"+data_mese +"-" + data_anno;
				//verifico che la data inserita dall'utente sia lecita (da oggi in poi , no passato)
				LocalDate oggi = LocalDate.now();
				boolean verify = data.isBefore(oggi);
		        if (verify == true) {		
		        	System.out.println("ERRORE! Inserire una data valida");
		        	alert = "6";
		        	currentsession.setAttribute("alert", alert);
		        	if(utenteNonRegistrato == "0")
		        		response.sendRedirect("prenotaPartita.jsp");
		        	else
		        		response.sendRedirect("cercaCampettiDisponibili.jsp");
		        }
		        else if(mese == 12) {
		        	alert = "8";
		        	currentsession.setAttribute("alert", alert);
		        	if(utenteNonRegistrato == "0")
		        		response.sendRedirect("prenotaPartita.jsp");
		        	else
		        		response.sendRedirect("cercaCampettiDisponibili.jsp");
		        }
		        else {
		        	//prenotazioniDisponibili = PrenotazioneDao.selectPrenotazioniDisponibili(date, sport);
		        	prenotazioniDisponibili = ComplessoSportivo.visualizzaCampettiDisponibili(date, sport);
		        	//se è tutto pieno in quel giorno...
					if(prenotazioniDisponibili.size()==0) {
						alert = "7";
						currentsession.setAttribute("alert", alert);
						if(utenteNonRegistrato == "0")
			        		response.sendRedirect("prenotaPartita.jsp");
			        	else
			        		response.sendRedirect("cercaCampettiDisponibili.jsp");
					}
					else {
						currentsession.setAttribute("prenotazioniDisponibili", prenotazioniDisponibili);
						currentsession.setAttribute("dataPartita", date);
						currentsession.setAttribute("data", dataPartita);
						currentsession.setAttribute("sport", sport);
						currentsession.setAttribute("prenotaPartita", "1");
						if(utenteNonRegistrato == "0")
			        		response.sendRedirect("prenotaPartita.jsp");
			        	else {
			        		currentsession.setAttribute("cercaCampetti", "1");
			        		response.sendRedirect("cercaCampettiDisponibili.jsp");
			        	}
			        		
						
					}
					
		        }
			}
			
			
		}
		
		else if(azione.equalsIgnoreCase("prenotaPartita")) {
		
			/*
			dataPartita = request.getParameter("dataPartita");
			currentsession.setAttribute("dataPartita", dataPartita);
			*/
			sport = request.getParameter("sport");
			campoFisso = request.getParameter("campoFisso");
			metodoPagamento = request.getParameter("metodoPagamento");
			
			
			String oraCampo = request.getParameter("orariDisponibili");
			campo = Integer.parseInt(oraCampo.substring(6, 7));
			oraPartita = Integer.parseInt(oraCampo.substring(12, 14));
			//c = CampoDao.selectCampo(campo);
			c = ComplessoSportivo.getCampo(campo);
			currentsession.setAttribute("campo", c);
			currentsession.setAttribute("oraInizioPartita", oraPartita);
			currentsession.setAttribute("oraFinePartita", oraPartita+1);
			currentsession.setAttribute("campoFisso", campoFisso);
			currentsession.setAttribute("metodoPagamento", metodoPagamento);
			
			//PrenotazioneDao.insertPrenotazione(new Prenotazione(u, c, dataPartita, oraPartita, oraPartita + 1));
			response.sendRedirect("confermaPrenotazionePartita.jsp");
		}
		
		else if(azione.equalsIgnoreCase("confermaPrenotazione")) {
		
			u = (Utente)currentsession.getAttribute("utente");
			
			//idCampo = Integer.parseInt(String.valueOf(currentsession.getAttribute("campo"))) ;
			//c = CampoDao.selectCampo(idCampo);
			c = (Campo)currentsession.getAttribute("campo");
			dataPartita = (String)currentsession.getAttribute("dataPartita");
			campoFisso = (String)currentsession.getAttribute("campoFisso");
			metodoPagamento = (String)currentsession.getAttribute("metodoPagamento");
			
			oraPartita = Integer.parseInt(String.valueOf(currentsession.getAttribute("oraInizioPartita")));
			Prenotazione p = new Prenotazione(u, new Corso(), c, dataPartita, oraPartita, oraPartita + 1);
			//PrenotazioneDao.insertPrenotazione(p);
			
			currentsession.setAttribute("prenotazione", p);
			currentsession.setAttribute("campoFisso", campoFisso);
		
			//se ho scelto modalità di pagamento in presenza
			if(metodoPagamento.equalsIgnoreCase("inPresenza")) {
				Campo.registraPrenotazione(p);
				//PrenotazioneDao.insertPrenotazione(p);
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
				//response.sendRedirect("userHome.jsp");
			}
				
			else {
				currentsession.setAttribute("tipologiaPagamento", "1");
				response.sendRedirect("pagamento.jsp");
			}
				
			
		}
		else if(azione.equalsIgnoreCase("annullaPrenotazione")) {
		
			String prenotazione = request.getParameter("prenotazione");
			int idPrenotazione, indiceVirgola;
			String substring = prenotazione.substring(4);
			indiceVirgola = substring.indexOf(",");
			System.out.println(prenotazione);
			System.out.println(substring.substring(0, indiceVirgola));
			idPrenotazione = Integer.parseInt(substring.substring(0, indiceVirgola));
			
			if(Campo.annullaPrenotazione(idPrenotazione)) {
				currentsession.setAttribute("alert", "3");
				response.sendRedirect("home.jsp");
				//response.sendRedirect("userHome.jsp");
			}
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}