package controller;


import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ComplessoSportivo;
import model.Utente;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
 // Funzioni per la correttezza del cellulare e dell'e-mail.
 	//------------------------------------------
 	private boolean mailSyntaxCheck(String email){		
 	        // Create the Pattern using the regex
 	        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
 	 
 	        // Match the given string with the pattern
 	        Matcher m = p.matcher(email);
 	 
 	        // check whether match is found
 	        boolean matchFound = m.matches();
 	 
 	        StringTokenizer st = new StringTokenizer(email, ".");
 	        String lastToken = null;
 	        while (st.hasMoreTokens()) {
 	            lastToken = st.nextToken();
 	        }
 	 
 	    // validate the country code
 	        if (matchFound && lastToken.length() >= 2
 	                && email.length() - 1 != lastToken.length()) {
 	 
 	            return true;
 	        } else {
 	            return false;
 	        }
 	 
 	    } 
 	private boolean validateTelNumber(String number){
 		
 		if (number == null){
 			return false;
 		}

 		//i caratteri "spazio" non vengono presi in considerazione
 		String patternSpace = "\\s+";
 		String replaceStr = "";
 		Pattern pattern = Pattern.compile(patternSpace);
 		Matcher matcher = pattern.matcher(number);
 		number = matcher.replaceAll(replaceStr);
 	
 		//i caratteri "-" non vengono presi in considerazione
 		patternSpace = "\\-+";
 		replaceStr = "";
 		pattern = Pattern.compile(patternSpace);
 		matcher = pattern.matcher(number);
 		number = matcher.replaceAll(replaceStr);   
 	
 		//i caratteri "+" non vengono presi in considerazione
 		patternSpace = "\\/+";
 		replaceStr = "";
 		pattern = Pattern.compile(patternSpace);
 		matcher = pattern.matcher(number);
 		number = matcher.replaceAll(replaceStr);
 		
 		//stringa di 10 caratteri
 		if ( !(number.length()==10)) {
 			return false;
 		}
 	
 		Pattern p = Pattern.compile("^(\\+)?[0-9]+$");
 		Matcher m = p.matcher(number);
 		boolean matchFound = m.matches();
 		return matchFound;
 	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String alert = "8";		//avviso di allarme
		//------------------Possibili inconvenienti------------------
		
		// alert = 1 -> Campi obbligatori non inseriti!
		// alert = 2 -> Le due password non combaciano.
		// alert = 3 -> Utente già registrato
		// alert = 4 -> C'è già qualche utente con l'username inserito
		// alert = 5 -> Cellulare non valido.
		// alert = 6 -> E-mail non valida.
		// alert = 7 -> Data di nascita 
		// alert = 8 -> tutto ok.
		
		boolean ok = true;		//variabile locale per verificare la correttezza degli input
		
		//------------------------------------------
		// Recupero i dati inseriti dall'utente
		//------------------------------------------
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");
		System.out.println(dataNascita);
		String email = request.getParameter("email");
		String cellulare = request.getParameter("cellulare");
		String ruolo = request.getParameter("ruolo");	
		
		//------------------------------------
		// 1) Check sui campi obbligatori, verifico che non siano stati inseriti dei campi vuoti;
		// 2) Verifico che le due password inserite siano uguali, che l'utente non sia già registrato;
		// 3) Verifico che lo username inserito dall'utente non sia già stato scelto da qualcun altro
		//------------------------------------
					
			if ( ok == true && (username.isEmpty() || password.isEmpty() || confermaPassword.isEmpty() || nome.isEmpty() || cognome.isEmpty() || 
				email.isEmpty() || cellulare.isEmpty() || dataNascita.length()==0)) {
				ok = false;
				alert="1";
			} else {
				if (!(password.equals(confermaPassword))) {
					ok = false;
					alert="2";
				}
				else {
					Utente u = Utente.getUtente(username);
					//Utente u = UtenteDao.selectUser(username);
					//entro in questo ramo se è già presente un utente nel db con l'username inserito in input
					//ora devo verificare se è l'username di un altro utente, o se l'utente è già registrato...
					if (ComplessoSportivo.usernameUsed(username)) {
						ok = false;
						//se si tratta dello stesso identico utente avrà tutti i dati uguali...
						if(u.getPassword().equals(password) && u.getNome().equals(nome) && u.getCognome().equals(cognome)
								&& u.getDataNascita().equals(dataNascita) && u.getEmail().equals(email) && u.getCellulare().equals(cellulare)
								&& u.getRuolo().equals(ruolo))
							alert = "3";
						else
							alert = "4";	//due utenti diversi, con stesso username
								
						
					} 
				}
			}
			
			//------------------------------------
			// Check sul cellulare e sull' email
			//------------------------------------
				
				if (ok == true && !validateTelNumber(cellulare)) {
						ok = false;
						alert="5";
				}
				
				if(ok == true && !mailSyntaxCheck(email)) {
					ok = false;
					alert="6";
				}
			
			//------------------------------------
			// Check sulla data di nascita
			//------------------------------------
			
				
			
			if (ok == true) {
				String data_anno = dataNascita.substring(0,4);
				String data_mese = dataNascita.substring(5,7);
				String data_giorno = dataNascita.substring(8);
				System.out.println(data_anno);
				System.out.println(data_mese);
				System.out.println(data_giorno);
				
				int anno = Integer.parseInt(data_anno);
				int mese = Integer.parseInt(data_mese);
				int giorno = Integer.parseInt(data_giorno);
				System.out.println("anno: " + anno + " mese: " + mese + " giorno: " + giorno);
					LocalDate data = LocalDate.of(anno, mese, giorno);
			        LocalDate oggi = LocalDate.now();
			        boolean verify = data.isAfter(oggi);
			        if (verify == true) {		
			       	 System.out.println("utente nato dal futuro");
			       	 ok = false;
			       	 alert = "7";
			        }

			             
				
			}
		
		
		if ( ok == true) {
			//------------------------------------
			// ESITO POSITIVO: necessito di salvare i dati nel database, 
			// 				   reinderizzo l'utente al login
			//------------------------------------
			Utente u = new Utente(username, password, nome, cognome, dataNascita, email, cellulare, ruolo);
			ComplessoSportivo.registraUtente(u);
			//------------------------------------
			HttpSession currentsession = request.getSession();
			currentsession.setAttribute("alert", alert);
			response.sendRedirect("login.jsp");
			//------------------------------------
		} else {
			//------------------------------------
			// ESITO NEGATIVO: ritorno alla pagina di registrazione + msg di errore
			//------------------------------------
			HttpSession currentsession = request.getSession();
			currentsession.setAttribute("alert", alert);
			response.sendRedirect("registration.jsp");	
			//------------------------------------
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}