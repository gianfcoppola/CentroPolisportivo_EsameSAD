package controller;

import java.io.IOException;
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
 * Servlet implementation class RedirectToVisualizzaPrenotazioni
 */
@WebServlet("/redirectToVisualizzaPrenotazioni")
public class RedirectToVisualizzaPrenotazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectToVisualizzaPrenotazioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currentSession = request.getSession();
		List<Corso> listaCorsi = ComplessoSportivo.getInfoCorsi();
		currentSession.setAttribute("listaCorsi", listaCorsi);
		
		Utente u = (Utente)currentSession.getAttribute("utente");
		List<Prenotazione> listaPrenotazioniUtente = Prenotazione.visualizzaPrenotazioniEffettuate(u.getId());
		currentSession.setAttribute("listaPrenotazioniUtente", listaPrenotazioniUtente);
		response.sendRedirect("annullaPrenotazione.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
