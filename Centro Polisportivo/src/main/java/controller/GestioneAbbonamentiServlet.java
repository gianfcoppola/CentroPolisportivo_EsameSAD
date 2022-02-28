package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.IstruttoreDao;
import model.Abbonamento;
import model.ComplessoSportivo;
import model.Corso;
import model.Utente;

/**
 * Servlet implementation class GestioneAbbonamentiServlet
 */
@WebServlet("/GestioneAbbonamentiServlet")
public class GestioneAbbonamentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneAbbonamentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    private int meseToNumero(String mese) {
    	int num = 1;
    	if(mese.equalsIgnoreCase("gennaio"))
    		num = 1;
    	else if(mese.equalsIgnoreCase("febbraio"))
    		num = 2;
    	else if(mese.equalsIgnoreCase("marzo"))
    		num = 3;
    	else if(mese.equalsIgnoreCase("aprile"))
    		num = 4;
    	else if(mese.equalsIgnoreCase("maggio"))
    		num = 5;
    	else if(mese.equalsIgnoreCase("giugno"))
    		num = 6;
    	else if(mese.equalsIgnoreCase("luglio"))
    		num = 7;
    	else if(mese.equalsIgnoreCase("agosto"))
    		num = 8;
    	else if(mese.equalsIgnoreCase("settembre"))
    		num = 9;
    	else if(mese.equalsIgnoreCase("ottobre"))
    		num = 10;
    	else if(mese.equalsIgnoreCase("novembre"))
    		num = 11;
    	else if(mese.equalsIgnoreCase("dicembre"))
    		num = 12;
    	return num;
    }
    
    private String numeroToMese(String mese) {
    	String month = "gennaio";
    	if(mese.equalsIgnoreCase("01") || mese.equalsIgnoreCase("1")  )
    		month = "gennaio";
    	else if(mese.equalsIgnoreCase("02") || mese.equalsIgnoreCase("2") )
    		month = "febbraio";
    	else if(mese.equalsIgnoreCase("03") || mese.equalsIgnoreCase("3"))
    		month = "marzo";
    	else if(mese.equalsIgnoreCase("04") || mese.equalsIgnoreCase("4"))
    		month = "aprile";
    	else if(mese.equalsIgnoreCase("05") || mese.equalsIgnoreCase("5"))
    		month = "maggio";
    	else if(mese.equalsIgnoreCase("06") || mese.equalsIgnoreCase("6"))
    		month = "giugno";
    	else if(mese.equalsIgnoreCase("07") || mese.equalsIgnoreCase("7"))
    		month = "luglio";
    	else if(mese.equalsIgnoreCase("08") || mese.equalsIgnoreCase("8"))
    		month = "agosto";
    	else if(mese.equalsIgnoreCase("09") || mese.equalsIgnoreCase("9"))
    		month = "settembre";
    	else if(mese.equalsIgnoreCase("10"))
    		month = "ottobre";
    	else if(mese.equalsIgnoreCase("11"))
    		month = "novembre";
    	else if(mese.equalsIgnoreCase("12"))
    		month = "dicembre";
    	return month;
    }
    
    private int numeroToNumero(String mese) {
    	int num = 1;
    	if(mese.equalsIgnoreCase("01") || mese.equalsIgnoreCase("1"))
    		num = 1;
    	else if(mese.equalsIgnoreCase("02") || mese.equalsIgnoreCase("2"))
    		num = 2;
    	else if(mese.equalsIgnoreCase("03") || mese.equalsIgnoreCase("3"))
    		num = 3;
    	else if(mese.equalsIgnoreCase("04") || mese.equalsIgnoreCase("4"))
    		num = 4;
    	else if(mese.equalsIgnoreCase("05") || mese.equalsIgnoreCase("5"))
    		num = 5;
    	else if(mese.equalsIgnoreCase("06") || mese.equalsIgnoreCase("6"))
    		num = 6;
    	else if(mese.equalsIgnoreCase("07") || mese.equalsIgnoreCase("7"))
    		num = 7;
    	else if(mese.equalsIgnoreCase("08") || mese.equalsIgnoreCase("8"))
    		num = 8;
    	else if(mese.equalsIgnoreCase("09") || mese.equalsIgnoreCase("9"))
    		num = 9;
    	else if(mese.equalsIgnoreCase("10"))
    		num = 10;
    	else if(mese.equalsIgnoreCase("11"))
    		num = 11;
    	else if(mese.equalsIgnoreCase("12"))
    		num = 12;
    	return num;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession currentSession = request.getSession();
		
		String azione = request.getParameter("abb");
		
		Utente u = (Utente)currentSession.getAttribute("utente");
		
		String alert;
		
		if(azione.equalsIgnoreCase("sottoscrivi")) {
			String corso = request.getParameter("corso");
			int idCorso;
			if(corso.substring(1, 2).equals(" "))
				idCorso = Integer.parseInt(corso.substring(0, 1));
			else
				idCorso = Integer.parseInt(corso.substring(0, 2));
			//Corso c = CorsoDao.selectCorso(idCorso);
			Corso c = ComplessoSportivo.getCorso(idCorso);
			int prezzoMensileCorso = c.getPrezzoMensile();
			
			String meseInizioAbbonamento = request.getParameter("inizioAbbonamento");
			String meseFineAbbonamento = request.getParameter("fineAbbonamento");
			int meseInizio = meseToNumero(meseInizioAbbonamento);
			int meseFine = meseToNumero(meseFineAbbonamento);
			
			String isIstruttore = (String)currentSession.getAttribute("istruttore");
			int idIstruttore=0;
			if(isIstruttore == "1")
				idIstruttore = (int)currentSession.getAttribute("idIstruttore");
			
			if(isIstruttore == "1" && c.getIstruttore().getId() == idIstruttore) {
				alert = "11";
				currentSession.setAttribute("alert", alert);
				response.sendRedirect("sottoscriviAbbonamento.jsp");
				
			}
			
			
			else if(meseInizio >= meseFine) {
				alert = "10";
				currentSession.setAttribute("alert", alert);
				response.sendRedirect("sottoscriviAbbonamento.jsp");
			}
			else {
				String dataInizioAbbonamento, dataFineAbbonamento;
				dataInizioAbbonamento = "2022-" + meseInizio + "-1";
				dataFineAbbonamento = "2022-" + meseFine + "-1";
				
				int prezzoTotaleAbbonamento = 0;
				
				
				//GESTIONE PREZZI
				prezzoTotaleAbbonamento = Abbonamento.calcolaCostoAbbonamento(meseInizio, meseFine, prezzoMensileCorso);
				//double [] tasso = {0, 0.02, 0.05, 0.07, 0.08, 0.11, 0.14, 0.16, 0.18, 0.20, 0.25}; 
				//prezzoTotaleAbbonamento = (meseFine-meseInizio)*prezzoMensileCorso - (int)Math.round(prezzoMensileCorso*(meseFine-meseInizio)*tasso[meseFine-meseInizio-1]);
				
				//GESTIONI POSTI
				
				/*int [] nuoviPosti = new int [11];
				List<Integer> mesiFiniti = new ArrayList<Integer>();
				nuoviPosti = c.getPostiDisponibili();
				
				int i = meseInizio;
				
				
				
				boolean impossibilePrenotareAbbonamento = false;
				while(i<meseFine) {
					if(c.getPostiDisponibili()[i-1] == 0) {
						mesiFiniti.add(i);
						impossibilePrenotareAbbonamento = true;
					}
					else {
						nuoviPosti[i-1]--;
						c.setPostiDisponibili(nuoviPosti);
					}
					
					i++;
				}
				
				*/
				List<Integer> mesiFiniti = ComplessoSportivo.verificaDisponibilit‡Corso(c, meseInizio, meseFine);
				
				boolean impossibilePrenotareAbbonamento = false;
				if(mesiFiniti.size() != 0)
					impossibilePrenotareAbbonamento = true;
				
				if(impossibilePrenotareAbbonamento == true) {
					alert = "9";
					currentSession.setAttribute("alert", alert);
					currentSession.setAttribute("mesiFiniti", mesiFiniti);
					response.sendRedirect("sottoscriviAbbonamento.jsp");
				}
				
				//se il corso a cui ci si vuole abbonare ha posti disponibii
				else {
					
					//List<Abbonamento> abbonamentiUtente = AbbonamentoDao.selectAbbonamentiUtente(u.getId());
					List<Abbonamento> abbonamentiUtente = Abbonamento.visualizzaAbbonamentiUtente(u.getId());
					
					boolean abbonamentoGiaEsistente = false;
					int i=0;
					while(i<abbonamentiUtente.size() && !abbonamentoGiaEsistente) {
						System.out.println("ENTRATO NEL WHILE");
						//se tra gli abbonamenti gi‡ effettuati dall'utente esiste quello per cui si sta registrando
						if(idCorso == abbonamentiUtente.get(i).getCorso().getId()) {
							
							//mesi di inizio e fine dell'abbonamento selezionati dall'utente
							int numeroMeseInizioAbb = meseToNumero(meseInizioAbbonamento);
							int numeroMeseFineAbb = meseToNumero(meseFineAbbonamento);
							
							//mesi di inizio e fine dell'i-esimo abbonamento (gi‡ sottoscritto) dall'utente
							String s1, s2;
							s1 = abbonamentiUtente.get(i).getInizioAbbonamento().substring(5);
							int indiceTrattino1 = s1.indexOf("-");
							s2 = abbonamentiUtente.get(i).getFineAbbonamento().substring(5);
							int indiceTrattino2 = s2.indexOf("-");
							
							int nMeseInizioAbbEsist = numeroToNumero(s1.substring(0, indiceTrattino1));
							int nMeseFineAbbEsist = numeroToNumero(s2.substring(0, indiceTrattino2));
							
							//controllo per vedere se Ë gi‡ abbonato nel periodo selezionato
							if(numeroMeseFineAbb > nMeseInizioAbbEsist && numeroMeseInizioAbb < nMeseFineAbbEsist) {
								abbonamentoGiaEsistente = true;
								String[] mesiGiaAbbonati = {numeroToMese(String.valueOf(nMeseInizioAbbEsist)), numeroToMese(String.valueOf(nMeseFineAbbEsist)) };
								currentSession.setAttribute("mesiGiaAbbonati", mesiGiaAbbonati);
							}
							
						}
						i++;
					}
					
					if(abbonamentoGiaEsistente == true) {
						alert = "12";
						currentSession.setAttribute("alert", alert);
						response.sendRedirect("sottoscriviAbbonamento.jsp");
					}
					else {

						Abbonamento a = new Abbonamento(u, c, dataInizioAbbonamento, dataFineAbbonamento, prezzoTotaleAbbonamento);
						currentSession.setAttribute("abbonamento", a);
						//AbbonamentoDao.insertAbbonamento(a);
						//List<Corso> listaCorsi = CorsoDao.selectCorsi();
						//currentSession.setAttribute("listaCorsi", listaCorsi);
						
						//List<Abbonamento> listaAbbonamenti = AbbonamentoDao.selectAbbonamenti();
						//currentSession.setAttribute("listaAbbonamenti", listaAbbonamenti);
						
						currentSession.setAttribute("dataInizioAbbonamento", dataInizioAbbonamento);
						currentSession.setAttribute("dataFineAbbonamento", dataFineAbbonamento);
						currentSession.setAttribute("prezzoTotaleAbbonamento", prezzoTotaleAbbonamento);
						currentSession.setAttribute("corsoAbb", c.getDescrizione());
						//currentSession.setAttribute("sportAbb", c.getSport());
						String nomeCognomeIstruttore = IstruttoreDao.selectIstruttore(c.getIstruttore().getId()).getNome() + " " + IstruttoreDao.selectIstruttore(c.getIstruttore().getId()).getCognome();
						currentSession.setAttribute("istruttoreCorso", nomeCognomeIstruttore);
						currentSession.setAttribute("numeroLezioniSettimana", c.getNumeroLezioniSettimanali());
						
						
						
						
						response.sendRedirect("confermaAbbonamento.jsp");
					}
					
				}
			}
		}
		
		else if (azione.equalsIgnoreCase("conferma")) {
			//Abbonamento a = (Abbonamento)currentSession.getAttribute("abbonamento");
			//AbbonamentoDao.insertAbbonamento(a);
			//List<Abbonamento> listaAbbonamenti = AbbonamentoDao.selectAbbonamenti();
			//currentSession.setAttribute("listaAbbonamenti", listaAbbonamenti);
			//alert = "2";
			//currentSession.setAttribute("alert", alert);
			currentSession.setAttribute("tipologiaPagamento", "2");
			response.sendRedirect("pagamento.jsp");
		}
		
		else if (azione.equalsIgnoreCase("annulla")) {
			String abbonamento = request.getParameter("abbonamento");
			int indiceSpazio = abbonamento.indexOf(" ");
			int idAbbonamento = Integer.parseInt(abbonamento.substring(0, indiceSpazio));
			//Abbonamento a = AbbonamentoDao.selectAbbonamento(idAbbonamento);
			Abbonamento a = Corso.getAbbonamento(idAbbonamento);
			
			int indiceTrattino1, indiceTrattino2;
			indiceTrattino1 = a.getInizioAbbonamento().substring(5).indexOf("-");
			indiceTrattino2 = a.getFineAbbonamento().substring(5).indexOf("-");
			int meseInizioAbbonamento = Integer.parseInt(a.getInizioAbbonamento().substring(5).substring(0, indiceTrattino1));
			int meseFineAbbonamento = Integer.parseInt(a.getFineAbbonamento().substring(5).substring(0, indiceTrattino2));
			
			int [] postiCorso = a.getCorso().getPostiDisponibili();
			if(Corso.eliminaAbbonamento(idAbbonamento)) {
				for(int i=meseInizioAbbonamento-1; i<meseFineAbbonamento-1; i++)
					postiCorso[i]++;
				ComplessoSportivo.modificaDatiCorso(postiCorso, a.getCorso().getId());
				
				
				currentSession.setAttribute("alert", "4");
				response.sendRedirect("home.jsp");
				//response.sendRedirect("userHome.jsp");
			}
			else {
				currentSession.setAttribute("alert", "13");
				response.sendRedirect("annullaAbbonamento.jsp");
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
