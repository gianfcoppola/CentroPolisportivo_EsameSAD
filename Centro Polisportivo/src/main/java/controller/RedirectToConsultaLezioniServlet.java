package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ComplessoSportivo;
import model.Corso;
import model.Prenotazione;
import model.Utente;

/**
 * Servlet implementation class RedirectToConsultaLezioniServlet
 */
@WebServlet("/RedirectToConsultaLezioniServlet")
public class RedirectToConsultaLezioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectToConsultaLezioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currentSession = request.getSession();
		
		Utente u = (Utente)currentSession.getAttribute("utente");
		List<Corso> listaCorsiIstruttore = ComplessoSportivo.getCorsiIstruttore(u.getId());
		//currentSession.setAttribute("listaCorsiIstruttore", listaCorsiIstruttore);
		String giorno, mese, anno;
		LocalDate data;
		LocalDate oggi = LocalDate.now();
		List<Prenotazione> lezioniInCalendario = new ArrayList<Prenotazione>();
		for(Corso c: listaCorsiIstruttore) {
			List<Prenotazione> listaPrenotazioniCorso = new ArrayList<Prenotazione>();
			try {
				listaPrenotazioniCorso = Prenotazione.recuperaPrenotazioniCorso(c.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Prenotazione p: listaPrenotazioniCorso){
				giorno = p.getData().substring(0, 2);
				mese = p.getData().substring(3, 5);
				anno = p.getData().substring(6);
				data = LocalDate.of(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
		        if(oggi.getMonth().equals(data.getMonth()) && (data.isAfter(oggi) || data.isEqual(oggi)))
		        	lezioniInCalendario.add(p);
			}
		}
		currentSession.setAttribute("calendarioLezioni", lezioniInCalendario);
		
		
		response.sendRedirect("consultaLezioni.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
