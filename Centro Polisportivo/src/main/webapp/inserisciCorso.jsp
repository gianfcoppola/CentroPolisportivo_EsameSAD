<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Istruttore"%>

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
    color: aliceblue;
    font-size: 14px;
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
	
	<%
	String infoIstruttore;
	
	List<Istruttore> listaIstruttori = (List<Istruttore>)session.getAttribute("listaIstruttori");
	%>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>



	<!-- !PAGE CONTENT! -->

	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">
	
	<%
				
				String alert = (String) session.getAttribute("alert");
				if(alert == "16"){ %>
					<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">Descrizione obbligatoria!</p>
						</div>
					    
				<% }else if(alert == "17"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px"> ERRORE! Inserire un valore consentito nel campo prezzo mensile!</p>
						</div>
				<% }else if(alert == "18"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px"> ERRORE! Qualcosa è andato storto...</p>
						</div>
					    
				<% }else if(alert == "19"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">ERRORE! Non sei un istruttore dello sport selezionato.</p>
						</div>
					    
				<% }else if(alert == "20"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px"> ERRORE! Assicurati di selezionare il numero corretto di lezioni settimanali </p>
						</div>
						
				<% }
				else if(alert == "21"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">ERRORE! Selezionare almeno un giorno!</p>
						</div>
						
				<% }
				else if(alert == "22"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px"> ERRORE! Cambiare giorni e orari delle lezioni  </p>
						</div>
						
				<% }
					alert = "0";
					%>

    <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
        <div class="section-center" style="height: 100%;">
          <div class="container" style="height: 100%;">
            <div class="row" style="height: 1077px;">
              <div class="booking-form" style="height: 100%;">
             <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Crea un nuovo corso</p>
             <form action="<%=request.getContextPath()%>/GestioneCorsiServlet" method="post">
                 <input type="hidden" id="azioneCorso" name="azioneCorso" value="crea">
                <div class="row">
                <div style="margin-left: 50px; margin-top: 10px;">
                    <p>Inserisci i dati del corso. Quando hai finito clicca su "Crea corso" e tornerai alla home</p>
                  </div>
                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
                    <div class="form-group">
                      <span class="form-label">Sport</span> <select class="form-control" id="sport" name ="sport">

						<option value="tennis">Tennis</option>
						<option value="paddel">Paddel</option>



                      </select> <span class="select-arrow"></span>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group" style="margin-left: 40px; margin-top: 20px;">
                      <span class="form-label">Inserisci una descrizione del corso</span>
                      <textarea class="form-control" style="height: 100px; border-radius: 20px;" id="descrizioneCorso"
                        name="descrizioneCorso" rows="4" cols="50" required></textarea>

                    </div>
                  </div>

                </div>

                <div class="row">
                  <div class="col-md-3" style="margin-left: 20px;">
                    <div class="form-group">
                      <span class="form-label">Numero lezioni a settimana</span>

                      <input class="form-control" style="width: 100px;" type="number" id="numeroLezioni"
                        name="numeroLezioni" min="1" max="5" value="1">


                    </div>
                  </div>

                </div>
                <div class="row">
                  <div class="col-md-3" style="margin-left: 20px;">
                    <div class="form-group">
                      <span class="form-label">Scegli giorni e ora delle lezioni</span>
                      <table>
                        <tr>
                          <td>
                            <input type="checkbox" id="giorno" name="giorno" value="lunedi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 5px;" checked>
                          </td>
                          <td>&nbsp;&nbsp;&nbsp;Lunedi'</td>
                        </tr>
                        <tr>
                          <td>
                            <input type="checkbox" id="giorno" name="giorno" value="martedi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 5px;">
                          </td>
                          <td>&nbsp;&nbsp;&nbsp;Martedi'</td>
                        </tr>
                        <tr>
                          <td>
                            <input type="checkbox" id="giorno" name="giorno" value="mercoledi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 5px;">
                          </td>
                          <td>&nbsp;&nbsp;&nbsp;Mercoledi'</td>
                        </tr>
                        <tr>
                          <td>
                            <input type="checkbox" id="giorno" name="giorno" value="giovedi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 5px;">
                          </td>
                          <td>&nbsp;&nbsp;&nbsp;Giovedi'</td>
                        </tr>
                        <tr>
                          <td>
                            <input type="checkbox" id="giorno" name="giorno" value="venerdi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 5px;">
                          </td>
                          <td>&nbsp;&nbsp;&nbsp;Venerdi'</td>
                        </tr>



                      </table>






                    </div>
                  </div>

                  <div class="col-md-3" style="margin-left: 20px; width: 130px;">
                    <div class="form-group">
                      <span style="width: 300px;" class="form-label"></span> 
                      <select
                      style="height: 40px; width: 100px;  margin-bottom: 21px; margin-top: 14px;"
                      class="form-control" id="orarioLunedi" name="orarioLunedi">
                      <option value="15-16">15:00</option>
                      <option value="16-17">16:00</option>
                      <option value="17-18">17:00</option>
                      <option value="18-19">18:00</option>
                      <option value="19-20">19:00</option>
                      </select> <span class="select-arrow"></span>
                    </div>
                    <div class="form-group">
                      <select
                      style="height: 40px; width: 100px;  margin-bottom: 21px; margin-top: 14px;"
                      class="form-control" id="orarioMartedi" name="orarioMartedi">
                        <option value="15-16">15:00</option>
                        <option value="16-17">16:00</option>
                        <option value="17-18">17:00</option>
                        <option value="18-19">18:00</option>
                        <option value="19-20">19:00</option>
                      </select> <span class="select-arrow"></span>
                    </div>
                    <div class="form-group">
                      <select
                      style="height: 40px; width: 100px;  margin-bottom: 21px; margin-top: 14px;"
                      class="form-control" id="orarioMercoledi" name="orarioMercoledi">
                        <option value="15-16">15:00</option>
                        <option value="16-17">16:00</option>
                        <option value="17-18">17:00</option>
                        <option value="18-19">18:00</option>
                        <option value="19-20">19:00</option>
                      </select> <span class="select-arrow"></span>
                    </div>
                    <div class="form-group">
                      <select
                      style="height: 40px; width: 100px;  margin-bottom: 21px; margin-top: 14px;"
                      class="form-control" id="orarioGiovedi" name="orarioGiovedi">
                        <option value="15-16">15:00</option>
                        <option value="16-17">16:00</option>
                        <option value="17-18">17:00</option>
                        <option value="18-19">18:00</option>
                        <option value="19-20">19:00</option>
                      </select> <span class="select-arrow"></span>
                    </div>
                    <div class="form-group">
                      <select
                      style="height: 40px; width: 100px;  margin-bottom: 21px; margin-top: 14px;"
                      class="form-control" id="orarioVenerdi" name="orarioVenerdi">
                        <option value="15-16">15:00</option>
                        <option value="16-17">16:00</option>
                        <option value="17-18">17:00</option>
                        <option value="18-19">18:00</option>
                        <option value="19-20">19:00</option>
                      </select> <span class="select-arrow"></span>
                    </div>
                  </div>
                  


                </div>
                <div class="row">
                  <div class="col-md-3" style="margin-left: 20px; width: 130px;">
                    <div class="form-group">
                      <span style="width: 300px;" class="form-label">Posti disponibili</span> <select style="width: 100px; margin-left: 10px;" class="form-control" 
                        name="postiDisponibili">


                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        <option value="25">25</option>



                      </select> <span class="select-arrow"></span>
                    </div>
                  </div>
                  <div class="col-md-3" style="margin-left: 20px;">
                    <div class="form-group" style="margin-left: 40px;">
                      <span class="form-label">Prezzo mensile</span>
                      <input class="form-control" style="width: 100px; margin-left: 10px;" type="text"
                        name="prezzoMensile" required>
                    </div>
                  </div>

                </div>



                <div class="row">

                  <div class="col-md-6">
                    <div class="form-btn">
                      <button class="submit-btn">Crea corso</button>
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