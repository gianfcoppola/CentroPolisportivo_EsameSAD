<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Abbonamento"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%
int tipo = Integer.parseInt((String) session.getAttribute("tipologiaUtente"));
%>

<title>Storico abbonamenti</title>
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

table {
  border-collapse: collapse;
 
}

th, td {
  color: #ddd;
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
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


	

	<!-- !PAGE CONTENT! -->
	
	
<%

List<Abbonamento> listaAbbonamentiUtente = (List<Abbonamento>)session.getAttribute("abbonamentiUtente");
String alert = (String) session.getAttribute("alert");
//PRENOTAZIONE AVVENUTA CON SUCCESSO (PROVENGO DA CONFERMAPRENOTAZIONEPARTITA.JSP)
if(alert == "13"){ %>
  <div class="alert alert-danger" role="alert"  style="top: 60px">
	 <p> Qualcosa è andato storto nella procedura di cancellazione dell'abbonamento, riprova.</p>
	</div>
<%
}
%>
					%>
	

<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%; margin-top: -23px;">

      <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
        <div class="section-center" style="height: 100%;">
          <div class="container" style="height: 100%;">
            <div class="row" style="height: 577px;">
              <div class="booking-form" style="height: 100%;">
<p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">I tuoi abbonamenti</p>

				
				<div style: margin-left: 17px">
						<div class="row">
                <div class="col-md-6" style="width: 470px;">
                  <div class="form-group">
                    <table>
                    <caption style="color: aliceblue; font-weight: 600; font-size: 1.2em;">Storico degli abbonamenti effettuati da <%=utente.getUsername() %> </caption>
                      <tr>
			<td><b>ID</b></td>
			<td><b>Corso</b></td>
			<td><b>Data inizio abbonamento</b></td>
			<td><b>Data fine abbonamento</b></td>
			<td><b>Prezzo totale</b></td>
			
		</tr>

		<%
					
		if(listaAbbonamentiUtente.size() == 0){%>
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<% 
		}
		else{
            		for (Abbonamento a: listaAbbonamentiUtente){
            			
            			%>
		<tr>
			<td><%=a.getId() %></td>
			<td><%=a.getCorso().getDescrizione() %></td>
			<td><%=a.getInizioAbbonamento() %></td>
			<td><%=a.getFineAbbonamento() %></td>
			<td><%=a.getPrezzoTotale() %></td>
		</tr>
		<% 
            			}
		}
            			%>



	</table>

 
<% 
	if(listaAbbonamentiUtente.size() == 0){%>
	<br>
		<div>
		<p style="color: aliceblue; font-weight: 600; font-size: 1.2em; width: 600px">
		NESSUN ABBONAMENTO ANCORA EFFETTUATO

		</p>
		</div>
	<%
	}
	else{
		%>
		<form action="<%=request.getContextPath()%>/GestioneAbbonamentiServlet" method="post">
		<input type="hidden" id="abb" name="abb" value="annulla">
                  </div>
                </div>
                <div class="col-md-6" style="width: 470px;">
                  <div class="form-group">
                    <span class="form-label">Vuoi annullare un abbonamento? Scegli quale: </span> <select
												class="form-control" id="abbonamento"
												name="abbonamento">
												
												<%
							String infoAbbonamento;
							String giorno, mese, anno;
							
							for (Abbonamento a: listaAbbonamentiUtente) {
								infoAbbonamento = a.getId() + " - " + a.getCorso().getDescrizione();
								
						        %>
								<option value="<%=infoAbbonamento%>"><%=infoAbbonamento%></option>
								<%
								
							}

								%>
							
						
						
				</select> <span class="select-arrow"></span>
                  </div>
                  <div class="form-btn">
                    <button class="submit-btn">Annulla abbonamento</button>
                    <%
						}
	
                    %>
                  </div>
                </div>
              </div>
				</div>
				
              
              
              
            </div>
          </div>
        </div>
      </div>
    </div>




    <!-- End page content -->
  </div>


	

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