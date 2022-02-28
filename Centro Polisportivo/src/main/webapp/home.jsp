<%@page import="model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%
int tipo = Integer.parseInt((String)session.getAttribute("tipologiaUtente"));
String tipoUtente;
if(tipo == 1)
	tipoUtente = "User";
else if (tipo == 2)
	tipoUtente = "Istruttore";
else
	tipoUtente = "Admin";
%>

<title><%=tipoUtente %> Home</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
<style>
body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
body {font-size:16px;}
.w3-half img{margin-bottom:-6px;margin-top:16px;cursor:pointer}
.w3-half img:hover{opacity:1}

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
Utente utente = (Utente)session.getAttribute("utente");
String nome = utente.getNome();
String cognome = utente.getCognome();

session.setAttribute("creaIstruttoreDaZero", "0");
%>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:1;width:300px;font-weight:bold;" id="mySidebar"><br>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
  <div class="w3-container" style="margin-top: -80px">
  <%
  if(tipo == 1 || tipo == 2){
  %>
    <h3 class="w3-padding-64"><b><%=nome %><br><%=cognome %></b></h3>
    <%
  }
  else{
    %>
    <h3 class="w3-padding-64"><b>admin</b></h3>
    <%
  }
    %>
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
<header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
  <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
  <span>Company Name</span>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>



<!-- !PAGE CONTENT! -->

<!-- GESTIONE DEGLI ALERT -->
<%
					String alert = (String) session.getAttribute("alert");
					
					//PRENOTAZIONE AVVENUTA CON SUCCESSO (PROVENGO DA CONFERMAPRENOTAZIONEPARTITA.JSP)
					if(alert == "1"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-left: 350px"> Prenotazione della partita avvenuta con successo!</p>
						</div>
		<%
					}
					else if (alert == "2"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-left: 350px"> Abbonamento effettuato con successo! </p>
						</div>
		<%
					}
					
					else if (alert == "3"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-left: 350px"> Prenotazione annullata con successo! </p>
						</div>
		<%
					}
					
					else if (alert == "4"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-left: 350px"> Abbonamento annullato con successo! </p>
						</div>
		<%
					}
					
					else if (alert == "28"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-left: 350px"> Corso modificato con successo! </p>
						</div>
		<%
					}
					
					
									
					if(alert == "14"){%>
				    <div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p style="margin-left: 350px"> Corso creato con successo</p>
					</div>
	<%
					}
					
					else if(alert == "15"){%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-left: 350px"> ERRORE! Negato tentativo di accesso a un corso appertenente a un altro istruttore</p>
						</div>
		<%
					}
					
					else if (alert == "29"){%>
					<div class="alert alert-danger" role="alert" style="top: 60px">
					<p style="margin-left: 350px">
						Nuovo istruttore creato con successo!
					</p>
				</div>
				<%
				}
					else if (alert == "30"){%>
					<div class="alert alert-danger" role="alert" style="top: 60px">
					<p style="margin-left: 350px">Corso modificato con successo!
					</p>
				</div>
				<%
					}
				else if (alert == "31"){%>
				<div class="alert alert-danger" role="alert" style="top: 60px">
				<p style="margin-left: 350px">Corso eliminato con successo!
				</p>
				</div>
				<%
				}
				else if (alert == "32"){%>
				<div class="alert alert-danger" role="alert" style="top: 60px">
				<p style="margin-left: 350px">Istruttore modificato con successo!
				</p>
				</div>
				<%
				}
				else if (alert == "33"){%>
				<div class="alert alert-danger" role="alert" style="top: 60px">
				<p style="margin-left: 350px">Istruttore eliminato con successo!
				</p>
				</div>
				<%
				}
					%>



<div class="w3-main" style="margin-left:340px;margin-right:40px">



  <!-- Header -->
  <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b>Cosa puoi fare</b></h1>
    
    <%
    if(tipo == 2){
    	session.setAttribute("modificaCorso", "0");
    	
    	%>
    	<h1 class="w3-xxxlarge w3-text-red"><b>Gestisci i tuoi corsi</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
  </div>
    	 <p>Se sei un istruttore, il centro polisportivo ti da' la possibilita' di creare nuovi corsi di paddel e tennis.</p>
    	 <p>Crea subito il tuo corso! Scegli quante volte alla settimana e in quali giorni tenere le tue 
    	 lezioni. Per farlo, clicca <a href="RedirectToCreaCorsoServlet">qui</a>. </p>
    	 <p>Altrimenti se ha gia' creato un corso, hai la possibilita' di modificarne le informazioni 
    	 cliccando <a href="RedirectToModificaCorsoServlet">qui</a>.</p>
        
        
        
        
        
        <div class="w3-container" id="designers" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Consulta il calendario delle tue lezioni</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <p>Consulta il calendario delle lezioni che devi tenere in questo mese. Per farlo, clicca 
   <a href="RedirectToConsultaLezioniServlet">qui</a> </p>
    
   
   
    
  </div>
        
    	
    	<%
    }
    %>
    
    
    
    <%
    if(tipo == 1 || tipo == 2){
    %>
  <div class="w3-container" id="designers" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Prenota una partita</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
  </div>
  
  <!-- Photo grid (modal) -->
  <div class="w3-row-padding">
    <img src="img/campetti.png" style="width:100%" onclick="onClick(this)" alt="Concrete meets bricks">
  

    
  </div>

  <!-- Modal for full size images on click-->
  <div id="modal01" class="w3-modal w3-black" style="padding-top:0" onclick="this.style.display='none'">
    <span class="w3-button w3-black w3-xxlarge w3-display-topright">×</span>
    <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
      <img id="img01" class="w3-image">
      <p id="caption"></p>
    </div>
  </div>

  <!-- Services -->
  
   
    <p>Il centro polisportivo Coppola &amp; Evangelista mette a disposizione dei propri clienti dei magnifici campetti di calcio, tennis e paddel. 
        I campi da calcio sono disponibili tutti i giorni dalle 15:00 alle 24:00, mentre quelli da tennis e paddel dalle 15:00 alle 21:00.</p>
        <p>Prenotare e' semplice, clicca <a href="redirectToPrenotazionePartita">qui</a>. </p>
   
 
  
  <!-- Designers -->
  <div class="w3-container" id="designers" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Iscriviti a un corso</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <p>Il centro polisportivo mette a disposizione diversi corsi di paddel e tennis per ogni fascia di eta'. </p>
    <p>I corsi si tengono dal lunedi' al venerdi' dalle 15:00 alle 19:00.
    </p>
    
    <p><a href="redirectToSottoscriviAbbonamento">Scopri subito il corso che fa per te.</a>
    </p>
    
  </div>
  <%
    }
    
    if(tipo == 3){
    	%>
    	<h1 class="w3-xxxlarge w3-text-red"><b>Gestisci i corsi del centro sportivo</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
  </div>
  <ul>
    
    	
    	<li><a href="RedirectToModificaCorsoServlet">Modifica i dati di un corso</a></li>
    	<li><a href="RedirectToEliminaCorsoServlet">Elimina un corso</a></li>
    	<li><a href="RedirectToVisualizzaCorsiServlet">Visualizza i dati di tutti i vari corsi</a></li>
    </ul>
    	 
        
        
        
        
        
        <div class="w3-container" id="designers" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Gestisci i profili degli istruttori del centro sportivo</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <ul>
        <li><a href="RedirectToCreaIstruttoreServlet">Inserisci un nuovo istruttore</a></li>
    	<li><a href="RedirectToModificaIstruttoreServlet">Modifica i dati di un istruttore</a></li>
    	<li><a href="RedirectToEliminaIstruttoreServlet">Rimuovi un istruttore</a></li>
    	<li><a href="RedirectToVisualizzaIstruttoriServlet">Visualizza i dati di tutti gli istruttori</a></li>
    </ul>
   
        <div class="w3-container" id="designers" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Visualizza i dettagli di tutte le prenotazioni e gli abbonamenti
    effettuati dagli utenti</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <ul>
    <li>Visualizza i dettagli relativi a tutte le <a href="RedirectToVisualizzaPrenotazioniAdminServlet">prenotazioni</a> effettuate presso il centro sportivo</li>
    <li>Visualizza i dettagli relativi a tutti gli <a href="RedirectToVisualizzaAbbonamentiAdminServlet">abbonamenti</a> che sono stati registrati presso il centro</li>
       
	</ul>
    
    
        <div class="w3-container" id="designers" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Visualizza incassi</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <ul>
    
        <li><a href="RedirectToVisualizzaIncassiServlet">Visualizza incassi</a></li>
	</ul>
   
    
  </div>
    	
    	
    	<%
    }
  %>

  

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