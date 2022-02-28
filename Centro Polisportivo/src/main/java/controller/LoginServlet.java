package controller;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.ComplessoSportivo;

import model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String alert = "0";		//avviso di allarme

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession currentsession = request.getSession();

		if(username.isEmpty() || password.isEmpty()) {
			alert = "1";
			currentsession.setAttribute("alert", alert);
			response.sendRedirect("login.jsp");
		}
		else {
			//caso in cui semplicemente le credenziali inserite non corrispondano a nessun utente
			if(ComplessoSportivo.isLogin(username, password) == false) {
				alert = "2";
				currentsession.setAttribute("alert", alert);
				response.sendRedirect("login.jsp");
			}
			else {
				// Autenticazione corretta	
				// Invalido la sessione precedente se esiste
				//------------------------------------
				HttpSession oldsession = request.getSession(false);
				if ( oldsession != null) { 
					oldsession.invalidate();
				}
				
				Utente u = Utente.getUtente(username, password);
				
				currentsession = request.getSession();
				currentsession.setAttribute("utente", u);
				currentsession.setAttribute("utenteNonRegistrato", "0");
				
				//------------------------------------
				//Setto il massimo intervallo di inattività pari a 5 minuti.
				//------------------------------------
				currentsession.setMaxInactiveInterval(60*5);
				
				//se l'utente è autenticato è un classico utente dell'applicazione...
				if(u.getRuolo().equalsIgnoreCase("user")) {
					
					currentsession.setAttribute("prenotaPartita", "0");
					currentsession.setAttribute("alert", alert);
					currentsession.setAttribute("tipologiaUtente", "1");

					

					//------------------------------------
					// Reindirizzo alla Home 
					//------------------------------------
					response.sendRedirect("home.jsp");
				}
				else if(u.getRuolo().equalsIgnoreCase("istruttore")) {

					
					currentsession.setAttribute("modificaCorso", "0");
					currentsession.setAttribute("istruttore", "1");
					currentsession.setAttribute("tipologiaUtente", "2");
					currentsession.setAttribute("alert", alert);
			
					response.sendRedirect("home.jsp");
				}
				//altrimenti se l'utente autenticato è l'admin dell'applicazione...
				else if(u.getRuolo().equalsIgnoreCase("admin")) {

					currentsession.setAttribute("alert", alert);
					currentsession.setAttribute("creaIstruttoreDaZero", "0");
					currentsession.setAttribute("modificaCorso", "0");
					currentsession.setAttribute("modificaIstruttore", "0");

					currentsession.setAttribute("tipologiaUtente", "3");

					currentsession.setAttribute("istruttore", "0");


					response.sendRedirect("home.jsp");
					
				}


			}



		}
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}


	}