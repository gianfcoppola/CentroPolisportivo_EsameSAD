<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Prenotazione"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%
int tipo = Integer.parseInt((String) session.getAttribute("tipologiaUtente"));
%>

<title>Prenotazione nuova partita</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/cercaCampetti.css" />
<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<style>
body, h1, h2, h3, h4, h5 {
	font-family: "Poppins", sans-serif
}

body {
	font-size: 16px;
}

.w3-half img {
	margin-bottom: -6px;
	margin-top: 16px;
	cursor: pointer
}

.w3-half img:hover {
	opacity: 1
}

<%
if(tipo == 2){%>
a{
font-size: 14px;
}
<%
}
%>
</style>
<body>

	<%
	Utente utente = (Utente) session.getAttribute("utente");
	String nome = utente.getNome();
	String cognome = utente.getCognome();
	String mod = (String)session.getAttribute("prenotaPartita");
	%>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding"
		style="z-index: 1; width: 300px; font-weight: bold;" id="mySidebar">
		<br> <a href="javascript:void(0)" onclick="w3_close()"
			class="w3-button w3-hide-large w3-display-topleft"
			style="width: 100%; font-size: 22px">Close Menu</a>
		<div class="w3-container" style="margin-top: -80px">
			<h3 class="w3-padding-64">
				<b><%=nome%><br><%=cognome%></b>
			</h3>
		</div>
		<div class="w3-bar-block" style="margin-top: -35px">

			<%
			if(tipo == 1 || tipo == 2){%>
	    	<a href="redirectHome" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Home</a> 
	    <%
	    }
	    if(tipo == 2){
	    	%>
	    	<a href="RedirectToCreaCorsoServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Crea un nuovo corso</a> 
		    <a href="RedirectToModificaCorsoServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Modifica corso</a> 
		    <a href="RedirectToEliminaCorsoServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Elimina corso</a> 
		    <a href="RedirectToVisualizzaCorsiServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Visualizza dati corsi</a>
	    	<a href="RedirectToConsultaLezioniServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Consulta lezioni</a> 
	    	<%
	    }
	    if(tipo == 1 || tipo == 2){%>
	    	<a href="redirectToPrenotazionePartita" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Prenota partita</a> 
	    <a href="redirectToSottoscriviAbbonamento" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Iscriviti a un corso</a> 
	    <a href="redirectToVisualizzaPrenotazioni" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Le tue prenotazioni</a> 
	    <a href="redirectToVisualizzaAbbonamenti" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">I tuoi abbonamenti</a> 
	        <a href="logout" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Logout</a>
	    	<%
	    }
	    if(tipo == 3){
	    	%>
	    	<a href="redirectHome" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Home</a> 
	    	<a href="RedirectToModificaCorsoServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Modifica corso</a> 
	    	<a href="RedirectToVisualizzaCorsiServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Visualizza dati corsi</a> 
	    	<a href="RedirectToCreaIstruttoreServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Inserisci un nuovo istruttore</a> 
	    	<a href="RedirectToModificaIstruttoreServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Modifica i dati di un istruttore</a> 
	    	<a href="RedirectToEliminaIstruttoreServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Elimina un istruttore</a> 
	    	<a href="RedirectToVisualizzaIstruttoriServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Visualizza dati istruttori</a> 
	    	<a href="RedirectToVisualizzaPrenotazioniAdminServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Visualizza prenotazioni</a> 
	    	<a href="RedirectToVisualizzaAbbonamentiAdminServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Visualizza abbonamenti</a> 
	    	<a href="RedirectToVisualizzaIncassiServlet" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Visualizza incassi</a> 
	    	<a href="logout" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white" style="font-size: 13px">Logout</a>
	    	
	    	<%
	    }
	    %>
    
    
  </div>
</nav>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">☰</a> <span>Company Name</span>
	</header>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>


	<%
	if(mod == "1"){
	String dataPartita = (String) session.getAttribute("data");
	String sport = (String) session.getAttribute("sport");
	int oraMaxPrenotazione, campo1, campo2;
	oraMaxPrenotazione = 23;
	campo1 = 1;
	campo2 = 3;
	if (sport.equals("calcio")) {
		oraMaxPrenotazione = 23;
		campo1 = 1;
		campo2 = 2;
	} else if (sport.equals("tennis")) {
		oraMaxPrenotazione = 20;
		campo1 = 5;
		campo2 = 6;
	} else if (sport.equals("paddel")) {
		oraMaxPrenotazione = 20;
		campo1 = 3;
		campo2 = 4;
	}
	@SuppressWarnings("unchecked")
	List<Prenotazione> prenotazioniDisponibili = (List<Prenotazione>) session.getAttribute("prenotazioniDisponibili");
	String campoOra;
	%>

	<!-- !PAGE CONTENT! -->

	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">

      <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
        <div class="section-center" style="height: 100%;">
          <div class="container" style="height: 100%;">
            <div class="row" style="height: 577px;">
              <div class="booking-form" style="height: 100%;">
                <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Cerca un campetto</p>
                <div style="margin-left: 70px;">
                  <form action="<%=request.getContextPath()%>/prenotazionePartita" method="post">
                    <input type="hidden" id="azione" name="azione"
                      value="prenotaPartita">
                    <div class="row" style="margin-bottom: 30px;">
                      <div class="col-md-3">
                        <div class="form-group">
                          <span class="form-label">Data partita</span> <input
                            class="form-control" type="date" name="dataPartita"
                            value="<%=dataPartita%>" readonly>
                        </div>
                      </div>
                      <div class="col-md-6" style="margin-left: 60px;">
                        <div class="form-group">
                          <span class="form-label">Sport</span> <input
                            class="form-control" style="width: 220px" type="text"
                            name="sport" value="<%=sport%>" readonly>
                        </div>
                      </div>
                    </div>
    
                    <div class="row" style="margin-bottom: 30px;">
                      <div class="col-md-3">
                        <div class="form-group">
                          <span class="form-label">Scegli campo e ora</span> <select
                          class="form-control" id="orariDisponibili"
                          name="orariDisponibili">
                          <%
                          for (Prenotazione p : prenotazioniDisponibili) {
                            campoOra = "Campo " + p.getCampo().getId() + " ore " + p.getOraInizio() + "-" + p.getOraFine();
                            System.out.println(p);
                          %>
                          <option value="<%=campoOra%>"><%=campoOra%></option>
  
                          <%
                          }
                          %>
                        </select> <span class="select-arrow"></span>
                        </div>
                      </div>
                      <div class="col-md-6" style="margin-left: 60px;">
                        <div class="form-group">
                          <span class="form-label">Opzione campo fisso</span> <input
                            type="checkbox" value="campoFisso" name="campoFisso"
                            placeholder="Campo fisso"
                            style="margin-left: 30px; width: 25px; height: 50px; margin-top: -1px;">
                        </div>
                      </div>
                    </div>
    
    
    
                    <div class="row" style="margin-left: -2px;">
                      <div class="form-group">
                        <div class="form-checkbox">
                          <span class="form-label">Metodo di pagamento</span> <label
                            for="inPresenza"> <input type="radio"
                            id="inPresenza" name="metodoPagamento" value="inPresenza"
                            checked> <span></span>In presenza
                          </label> <label for="online"> <input type="radio" id="online"
                            name="metodoPagamento" value="online"> <span></span>Online
                          </label>
    
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-btn">
                          <button class="submit-btn">Prenota</button>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
                
              </div>
            </div>
          </div>
        </div>
      </div>
  
  
  
  
      <!-- End page content -->
    </div>

<%}
	
	else if (mod == "0"){
		%>
		<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">

<%
String alert = (String) session.getAttribute("alert");
if(alert == "5"){ %>
					   <div class="alert alert-danger" role="alert"  style="top: 60px; width: 1000px; margin-left: -40px; margin-bottom: -1px;">
						 <p style="margin-left: 50px;"> ERRORE! Inserire una data!.</p>
						</div>
					<%
					}
					
else if(alert == "6"){ %>
						    <div class="alert alert-danger" role="alert"  style="top: 60px; width: 1000px; margin-left: -40px; margin-bottom: -1px;">
							 <p style="margin-left: 50px;"> ERRORE! Inserire una data valida</p>
							</div>
					<%
					}
					else if (alert == "7"){%>
						
					    <div class="alert alert-danger" role="alert"  style="top: 60px; width: 1000px; margin-left: -40px; margin-bottom: -1px;">
						 <p style="margin-left: 50px;">CAMPI PIENI NEL GIORNO INSERITO! Per favore scegliere un'altra data</p>
						</div>
					<%
					}
					
					else if (alert == "8"){%>
						
					   <div class="alert alert-danger" role="alert"  style="top: 60px; width: 1000px; margin-left: -40px; margin-bottom: -1px;">
						 <p style="margin-left: 50px;"> SIAMO SPIACENTI MA IL COMPLESSO E' CHIUSO A DICEMBRE.</p>
						</div>
					<%
					}
					
					%>
      <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
        <div class="section-center" style="height: 100%;">
          <div class="container" style="height: 100%;">
            <div class="row" style="height: 577px;">
              <div class="booking-form" style="height: 100%;">
                <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Cerca un campetto</p>
                <form action="<%=request.getContextPath()%>/prenotazionePartita" method="post">
                <input type="hidden" id="azione" name="azione" value="cercaCampetti">
                  <div class="form-group" style="margin-left: 70px;">
                    <div class="form-checkbox">
                      <label for="calcio">
                        <input type="radio" id="calcio" name="sport" value="calcio" checked> 
                        <span></span>Calcio
                      </label>
                      <label for="paddel">
                        <input type="radio" id="paddel" name="sport" value="paddel">
                        <span></span>Paddel
                      </label>
                      <label for="tennis">
                        <input type="radio" id="tennis" name="sport" value="tennis">
                        <span></span>Tennis
                      </label>
                    </div>
                  </div>
                  
                  <div class="row" style="margin-left: 50px;">
                    <div class="col-md-3">
                      <div class="form-group">
                        <span class="form-label">Quando vuoi giocare?</span>
                        <input class="form-control" type="date" name="dataPartita" required>
                      </div>
                    </div>
                  </div>
                  <div class="row" style="margin-left: 50px;">
                    
                    <div class="col-md-6">
                      <div class="form-btn">
                        <button class="submit-btn">Verifica disponibilita'</button>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    
      
    
      
    
    
    <!-- End page content -->
    </div>
		<%
	}
	
	%>

	<!-- W3.CSS Container -->

	<script>
		// Script to open and close sidebar
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
			document.getElementById("myOverlay").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
			document.getElementById("myOverlay").style.display = "none";
		}

		// Modal Image Gallery
		function onClick(element) {
			document.getElementById("img01").src = element.src;
			document.getElementById("modal01").style.display = "block";
			var captionText = document.getElementById("caption");
			captionText.innerHTML = element.alt;
		}
	</script>

</body>
</html>