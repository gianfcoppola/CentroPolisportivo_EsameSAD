package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Amministratore;


/**
 * Servlet implementation class RedirectToVisualizzaIncassiServlet
 */
@WebServlet("/RedirectToVisualizzaIncassiServlet")
public class RedirectToVisualizzaIncassiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectToVisualizzaIncassiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currentSession = request.getSession();
		int [] incassi = new int[3];
		incassi[0] = Amministratore.getIncassiPrenotazioni();
		incassi[1] = Amministratore.getIncassiAbbonamenti();
		incassi[2] = Amministratore.getIncassiTotali();
		
		currentSession.setAttribute("incassi", incassi);
		response.sendRedirect("visualizzaIncassi.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
