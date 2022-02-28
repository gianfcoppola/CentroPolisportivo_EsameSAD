package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Amministratore;
import model.Prenotazione;


/**
 * Servlet implementation class RedirectToVisualizzaPrenotazioniAdminServlet
 */
@WebServlet("/RedirectToVisualizzaPrenotazioniAdminServlet")
public class RedirectToVisualizzaPrenotazioniAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectToVisualizzaPrenotazioniAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currentSession = request.getSession();
		
		List<Prenotazione> listaPrenotazioni = Amministratore.visualizzaPrenotazioni();
		currentSession.setAttribute("listaPrenotazioni", listaPrenotazioni);
		response.sendRedirect("visualizzaPrenotazioni.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
