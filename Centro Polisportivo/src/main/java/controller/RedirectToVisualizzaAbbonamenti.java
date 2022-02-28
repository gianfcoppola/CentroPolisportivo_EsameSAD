package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Abbonamento;
import model.Utente;

/**
 * Servlet implementation class RedirectToVisualizzaAbbonamenti
 */
@WebServlet("/redirectToVisualizzaAbbonamenti")
public class RedirectToVisualizzaAbbonamenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectToVisualizzaAbbonamenti() {
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
		List<Abbonamento> listaAbbonamentiUtente = Abbonamento.visualizzaAbbonamentiUtente(u.getId());
		currentSession.setAttribute("abbonamentiUtente", listaAbbonamentiUtente);
		
		
		response.sendRedirect("annullaAbbonamento.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}