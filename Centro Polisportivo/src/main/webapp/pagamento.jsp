<%@page import="java.util.*"%>
<%@page import="model.Utente"%>
<%@page import="model.Campo"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<title>Payment page</title>
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
body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
body {font-size:16px;}
.w3-half img{margin-bottom:-6px;margin-top:16px;cursor:pointer}
.w3-half img:hover{opacity:1}

<%
int tipo = Integer.parseInt((String) session.getAttribute("tipologiaUtente"));
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
					
					
					Utente u = (Utente)session.getAttribute("utente");
					String username = u.getUsername();
					String nome, cognome;
					nome = u.getNome();
					cognome = u.getCognome();
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
<header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
  <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
  <span>Company Name</span>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->

	 <div class="w3-main" style=" margin-left: 340px; margin-right: 40px; height: 100%;">
    
    <%
				
				String alert = (String) session.getAttribute("alert");
					if(alert == "1"){ %>
					<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">  Errore! Assicurarsi di aver inserito correttamente il numero della carta!</p>
						</div>
					    
				<% }else if(alert == "2"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">  Errore! CVV o Card Number sono costituiti da sole cifre!</p>
						</div>
				<% }else if(alert == "3"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">  Errore! CVV o Card Number non hanno un formato corretto!</p>
						</div>
					    
				<% }else if(alert == "4"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">  Circuito di credito non riconosciuto.</p>
						</div>
					    
				<% }else if(alert == "5"){ %>
				<div class="alert alert-danger" role="alert"  style="top: 60px; width: 1200px; margin-bottom: 1px; margin-left: -100px;">
						 <p style="margin-left: 100px">   Carta di Credito scaduta.</p>
						</div>
						
				<% }
					alert = "0";
					%>

		 <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
          <div class="section-center" style="height: 100%;">
            <div class="container" style="height: 100%;">
              <div class="row" style="background-color: aqua;height: 577px;">
                <div class="booking-form" style="height: 100%;">
							<form class="form-signin " action="${pageContext.request.contextPath}/payment" method="POST" >
                  


								<div class="row">
                  <p style="width: 900px; color: aliceblue;">Si accettano i seguenti circuiti di credito presenti sul mercato internazionale: 
                    VISA, MasterCard, Diners Club, American Express, Discover Card.</p>
                    <table>
                      <tr style="height: 20px;">
                        <td
                          style="font-weight: bold; color: aliceblue; font-size: 18px;">Card number </td>
                          <td></td>
                        <td style="height: 40px;">
                        <td><input
                          class="form-control" style="width: 250px; height: 30px; font-size: 18px;" type="text"
                          name="cardNumber" placeholder="xxxx-xxxx-xxxx-xxxx" maxlength="16" required></td>
                      </tr>
                      <tr style="height: 40px;">
                        <td
                          style="font-weight: bold; color: aliceblue; font-size: 18px;">Card holder </td>
                          <td style="width: 50px;"></td>
                        <td style="height: 20px;">
                        <td><input
                          class="form-control" style="width: 220px; height: 30px; font-size: 18px;" type="text"
                          name="cardholder" placeholder="card holder" required></td>
                      </tr>
                      <tr style="height: 20px;">
                        <td
                          style="font-weight: bold; color: aliceblue; font-size: 18px;">Expiration date (mm/yyyy)</td>
                          <td></td>
                        <td style="height: 40px;">
                        <td>	
                          <div class="form-group" style="margin-top: 10px;" >
                            <select name="mouth" style="height: 30px; width: 100px;"
                              class="form-control" id="orariDisponibili">
                             
                              
                              <option>01</option>
                              <option>02</option>
                              <option>03</option>
                              <option>04</option>
                              <option>05</option>
                              <option>06</option>
                              <option>07</option>
                              <option>08</option>
                              <option>09</option>
                              <option>10</option>
                              <option>11</option>
                              <option>12</option>
      
                            </select> 
                            
                         
                          </div>
                        </td>

                        <td>	
                          <div class="form-group" style="margin-top: 10px;" >
                            <select style="height: 30px; width: 100px; margin-right: 50px;"
                              class="form-control" id="orariDisponibili"
                              name="year">
                             
                              
                              <option>2022</option>
				          <option>2023</option>
				          <option>2024</option>
				          <option>2025</option>
				          <option>2026</option>
				          <option>2027</option>
				          <option>2028</option>
				          <option>2029</option>
				          <option>2030</option>
      
                            </select> 
                            
                         
                          </div>
                        </td>
                            
                          </div>
                        </div></td>
                      </tr>
                      
                      <tr style="height: 20px;">
                        <td
                          style="font-weight: bold; color: aliceblue; font-size: 18px;">CVV </td>
                          <td></td>
                        <td style="height: 40px;">
                        <td><input
                          class="form-control" style="width: 100px; height: 30px; font-size: 18px;" placeholder=" CVV" type="text" id="card-ccv" name="CVV" maxlength="3" required></td>
                      </tr>
                    </table>
								</div>

								



								<div class="row">
									
									
									<div class="col-md-3">
										<div class="form-btn">
											<button class="submit-btn" style="margin-left: 300px;">Pay now</button>
										</div>
									</div>
								</div>
                <div class="row">
									
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
