<%@page import="java.util.*"%>
<%@page import="model.Utente"%>
<%@page import="model.Campo"%>
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
String dataPartita = (String)session.getAttribute("dataPartita");

Utente u = (Utente)session.getAttribute("utente");
String username = u.getUsername();
String nome, cognome;

nome = u.getNome();
cognome = u.getCognome();
String campo = String.valueOf(session.getAttribute("campo")) ;
String oraInizioPartita = String.valueOf(session.getAttribute("oraInizioPartita"));
oraInizioPartita = oraInizioPartita + ":00";
String oraFinePartita = String.valueOf(session.getAttribute("oraFinePartita"));
oraFinePartita = oraFinePartita + ":00";
Campo c = (Campo)session.getAttribute("campo");
String sport = c.getSport();
String prezzo = String.valueOf(c.getPrezzo());
String campoFisso = (String)session.getAttribute("campoFisso");
String pagamento = (String)session.getAttribute("metodoPagamento");
String siNo;
if(campoFisso == null)
	siNo = "No";
else 
	siNo = "Si";
String metodoPagamento;
if(pagamento.equalsIgnoreCase("inPresenza"))
	metodoPagamento = "In presenza";
else
	metodoPagamento = "Online";
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
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">

      <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
        <div class="section-center" style="height: 100%;">
          <div class="container" style="height: 100%;">
            <div class="row" style="height: 577px;">
              <div class="booking-form" style="height: 100%;">
                <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Riepilogo prenotazione</p>
                <form action="<%=request.getContextPath()%>/prenotazionePartita"
                  method="post">
                  <input type="hidden" id="azione" name="azione"
                    value="confermaPrenotazione">
                    <div style="margin-left: 60px;">
                      <table>
                        <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Nome:
                          </td>
                          <td style="height: 20px;"><td style="color: aliceblue; font-size: 18px;"><%=nome%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Cognome: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=cognome%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Data partita: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=dataPartita%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Sport: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=sport%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Campo: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=c.getId() %></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Dalle ore: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=oraInizioPartita%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Alle ore: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=oraFinePartita%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Campo fisso: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=siNo%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px; padding-right: 50px;">Metodo di pagamento: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=metodoPagamento%></td>
                        </tr>
                      <tr style="height: 20px;">
                          <td
                            style="font-weight: bold; color: aliceblue; font-size: 18px;">Prezzo: </td>
                          <td style="height: 20px;">
                          <td style="color: aliceblue; font-size: 18px;"><%=prezzo%></td>
                        </tr>
                        
      
                      </table>
                      <br>
                      <%
                      if(metodoPagamento.equalsIgnoreCase("in presenza")){
                      %>
                      
                      <button style="width: 600px; margin-left: 100px; margin-top: 40px;" class="submit-btn">Conferma prenotazione</button>
                                          
                      <%
                      }
                      else{
                      %>	
                      <button style="width: 600px; margin-left: 100px; margin-top: 40px;" class="submit-btn">Vai al pagamento</button>
                                            
                                          
                      <%	
                      }
                      %>	
                    </div>
                  
                      
  

  
                </form>              
            </div>
          </div>
        </div>
      </div>
    </div>
    
  
    
  
  <!-- End page content -->
  </div>

	
											<!-- W3.CSS Container --> <script>
												// Script to open and close sidebar
												function w3_open() {
													document
															.getElementById("mySidebar").style.display = "block";
													document
															.getElementById("myOverlay").style.display = "block";
												}

												function w3_close() {
													document
															.getElementById("mySidebar").style.display = "none";
													document
															.getElementById("myOverlay").style.display = "none";
												}

												// Modal Image Gallery
												function onClick(element) {
													document
															.getElementById("img01").src = element.src;
													document
															.getElementById("modal01").style.display = "block";
													var captionText = document
															.getElementById("caption");
													captionText.innerHTML = element.alt;
												}
											</script>


										</body>
</html>