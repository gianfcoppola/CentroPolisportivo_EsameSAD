<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Corso"%>
<%@page import="model.ComplessoSportivo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%
int tipo = Integer.parseInt((String) session.getAttribute("tipologiaUtente"));
%>

<title>Elimina corso</title>
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
    color: aliceblue;
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
	
	
	
	String infoCorso, infoIstruttore;
	List<Corso> listaCorsi = ComplessoSportivo.getInfoCorsi();
	
	
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
			onclick="w3_open()">???</a> <span>Company Name</span>
	</header>

	<!-- Overlay effect when opening sidebar on small screens -->
	
	<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer; height: 100%;" title="close side menu"
    id="myOverlay"></div>

   <div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">
   
   <%
   String alert = (String)session.getAttribute("alert");
 //se si verifica un qualche errore durante l'operazione di delete corso
   if (alert == "42") {
   %>
   <div class="alert alert-danger" role="alert" style="top: 60px">
   <p>
   ATTENZIONE! Si ?? verificato un qualche errore che ha impedito di cancellare il corso. Riprovare.

   </p>
   </div>
   <%
   session.setAttribute("alert", "0");
   }
   %>

    <div id="booking" class="section" style="margin-left: -40px; height: 100%;">
      <div class="section-center" style="height: 100%;">
        <div class="container" style="height: 100%;">
          <div class="row" style="height: 777px;">
            <div class="booking-form" style="height: 100%;">
              <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Elimina corso</p>
              
              <form action="<%=request.getContextPath()%>/GestioneCorsiServlet" method="post">
               <input type="hidden" id="azioneCorso" name="azioneCorso" value="elimina">
                <div class="row" style="margin-left: 30px;">

                  <div class="col-md-6" style="margin-left: 20px; margin-top: 20px;">
                    <div class="form-group">
                      <span class="form-label">Scegli il corso che vuoi eliminare</span> 
                      <select class="form-control" id="corso" name="corso">
                        <%
						for (Corso c: listaCorsi) {
							infoCorso = c.getId() + " - " + c.getSport() + " (" + c.getDescrizione() +")";
							
						%>
						<option value="<%=infoCorso%>"><%=infoCorso%></option>
				
					<%
							}
					
							%>
				</select>
                      <span class="select-arrow"></span>
                    </div>
                  </div>
                </div>
                <div class="row" style="margin-left: 30px;">
  
                  <div class="col-md-3" style="margin-left: 20px; margin-top: 5px;">
                    <div class="form-btn">
                      <button class="submit-btn">Elimina</button>
                    </div>
                  </div>
                </div>
                <div class="row" style="margin-left: 30px; font-size: 15px" >
               
              <p style="margin-left: -60px; margin-top:30px; width: 1000px; color: aliceblue; font-size: 25px; text-align: center;">Info corsi</p>
                <table>


                  
                  <tr>
			<td><b>ID</b></td>
			<td><b>Sport</b></td>
			<td><b>Istruttore</b></td>
			<td><b>Descrizione</b></td>
			<td><b>Numero lezioni a settimana</b></td>
			<td><b>Prezzo</b></td>
			
		</tr>

		<%
					
		for (Corso c: listaCorsi){
			infoIstruttore = c.getIstruttore().getId() + " - " + c.getIstruttore().getNome() + " " + c.getIstruttore().getCognome();
			%>
<tr>
<td><%=c.getId() %></td>
<td><%=c.getSport() %></td>
<td><%=infoIstruttore %></td>
<td><%=c.getDescrizione() %></td>
<td><%=c.getNumeroLezioniSettimanali() %></td>
<td><%=c.getPrezzoMensile() %></td>
</tr>
<% 
			}
			%>



</table>

              </div>
              </form>
              

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