<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Corso"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%
int tipo = Integer.parseInt((String) session.getAttribute("tipologiaUtente"));
%>

<title>Sottoscrivi abbonamento</title>
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
  max-width: 300px;
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
	
	List<Corso> listaCorsi = (List<Corso>)session.getAttribute("listaCorsi");
	String infoIstruttore, infoCorso;
	
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
	
	<!-- GESTIONE DEGLI ALERT -->
<%
					String alert = (String) session.getAttribute("alert");
List<Integer> mesiFiniti = (List<Integer>)session.getAttribute("mesiFiniti");
List<String> mesi = new ArrayList<String>();
mesi.add("Gennaio");
mesi.add("Febbraio");
mesi.add("Marzo");
mesi.add("Aprile");
mesi.add("Maggio");
mesi.add("Giugno");
mesi.add("Luglio");
mesi.add("Agosto");
mesi.add("Settembre");
mesi.add("Ottobre");
mesi.add("Novembre");
String mese="";
					
					//PRENOTAZIONE AVVENUTA CON SUCCESSO (PROVENGO DA CONFERMAPRENOTAZIONEPARTITA.JSP)
					if(alert == "9"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px; margin-bottom: 1px;">
						 <p style="margin-left: 350px">
			ERRORE! IMPOSSIBILE ABBONARSI AL CORSO SELEZIONATO NEI SEGUENTI MESI (POSTI FINITI):
		</p>
		<p style="margin-left: 350px">
		<%
			for(int i=0; i<mesiFiniti.size(); i++){
				
				mese = mese + " " + mesi.get(mesiFiniti.get(i)-1);
				
			}
			%>
			<%=mese %>
		</p>
						</div>
		<%
					}
					else if (alert == "10"){
						%>
					   <div class="alert alert-danger" role="alert"  style="top: 60px; margin-bottom: 1px;">
						 <p style="margin-left: 350px">ERRORE! Il mese di fine abbonamento non puo' essere precedente a quello di inizio.</p>
						</div>
		<%
					}
					
					else if (alert == "11"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px; margin-bottom: 1px;">
						 <p style="margin-left: 350px">IMPOSSIBILE! Stai cercando di abbonarti a un corso gestito da te stesso!</p>
						</div>
		<%
					}
					
					else if (alert == "12"){
						String [] mesiGiaAbbonati = (String[])session.getAttribute("mesiGiaAbbonati");
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px; margin-bottom: 1px;">
						 <p style="margin-left: 350px">Impossibile sottoscrivere un abbonamento nel periodo selezionato! Sei gia' abbonato al corso selezionato nel periodo (<%=mesiGiaAbbonati[0] %>, <%=mesiGiaAbbonati[1] %>).</p>
						</div>
		<%
					}
					
					%>
	

	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">

      <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
        <div class="section-center" style="height: 100%;">
          <div class="container" style="height: 100%;">
            <div class="row" style="height: 877px;">
              <div class="booking-form" style="height: 100%;">
                <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Iscriviti  a un corso</p>
           		 <div style="margin-left: 50px;">
           			<form action="<%=request.getContextPath()%>/GestioneAbbonamentiServlet" method="post">
            
             <input type="hidden" id="abb" name="abb" value="sottoscrivi">
                <div class="row">
                  <table>
                    <caption style="color: aliceblue;">INFO CORSI</caption>
                    <tr>
                      <td><b>ID</b></td>
                      <td><b>Sport</b></td>
                      <td><b>Istruttore</b></td>
                      <td><b>Descrizione</b></td>
                      <td><b>Numero lezioni a settimana</b></td>
                      <td><b>Prezzo mensile</b></td>
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
                  <br>
                </div>
              <div class="row">
                <div class="col-md-3">
                  <div class="form-group">
                    <span class="form-label">Scegli corso</span> <select
                      class="form-control" id="corso" name="corso">
                      <%
						for (Corso c: listaCorsi) {
							infoCorso = c.getId() + " - " + c.getSport();
							%>
							<option value="<%=infoCorso%>"><%=infoCorso%></option>
					<%
							
						}
							%>
                    </select> <span class="select-arrow"></span>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-3">
                  <div class="form-group">
                    <span class="form-label">Inizio abbonamento</span> <select
                      class="form-control" id="inizioAbbonamento"
                      name="inizioAbbonamento">
                      <%
				
				LocalDate gen = LocalDate.of(2022, 1, 15);
				LocalDate feb = LocalDate.of(2022, 2, 15);
				LocalDate mar = LocalDate.of(2022, 3, 15);
				LocalDate apr = LocalDate.of(2022, 4, 15);
				LocalDate mag = LocalDate.of(2022, 5, 15);
				LocalDate giu = LocalDate.of(2022, 6, 15);
				LocalDate lug = LocalDate.of(2022, 7, 15);
				LocalDate ago = LocalDate.of(2022, 8, 15);
				LocalDate set = LocalDate.of(2022, 9, 15);
				LocalDate ott = LocalDate.of(2022, 10, 15);
				LocalDate nov = LocalDate.of(2022, 11, 15);
				LocalDate oggi = LocalDate.now();
				if(gen.isAfter(oggi)){
				%>
					<option value="gennaio">Gennaio</option>
		        <%
				 }
				if(feb.isAfter(oggi)){
		        %>
		        	<option value="febbraio">Febbraio</option>
				<%
				}
				if(mar.isAfter(oggi)){
		        %>
		        	<option value="marzo">Marzo</option>
				<%
				}
				if(apr.isAfter(oggi)){
		        %>
		        	<option value="aprile">Aprile</option>
		        <%
				}
				if(mag.isAfter(oggi)){
		        %>
		        	<option value="maggio">Maggio</option>
		        	<%
				}
				if(giu.isAfter(oggi)){
		        %>
		        	<option value="giugno">Giugno</option>
		        	<%
				}
				if(lug.isAfter(oggi)){
		        %>
		        	<option value="luglio">Luglio</option>
		        	<%
				}
				if(ago.isAfter(oggi)){
		        %>
		        	<option value="agosto">Agosto</option>
		        	<%
				}
				if(set.isAfter(oggi)){
		        %>
		        	<option value="settembre">Settembre</option>
		        	<%
				}if(ott.isAfter(oggi)){
		        %>
		       	 <option value="ottobre">Ottobre</option>
		        <%
				}if(nov.isAfter(oggi)){
		        %>
		        <option value="novembre">Novembre</option>
				<%
				}
				%>

                      
                    </select> <span class="select-arrow"></span>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="form-group">
                    <span class="form-label">Fine abbonamento</span> <select
                      class="form-control" id="fineAbbonamento"
                      name="fineAbbonamento">
                      <%
                      if(gen.isAfter(oggi)){
          				%>
          					<option value="febbraio">Febbraio</option>
          		        <%
          				 }if(feb.isAfter(oggi)){
          		        %>
          		        	<option value="marzo">Marzo</option>
          				<%
          				 }if(mar.isAfter(oggi)){
          		        %>
          		        	<option value="aprile">Aprile</option>
          				<%
          				 }if(apr.isAfter(oggi)){
          		        %>
          		        	<option value="maggio">Maggio</option>
          		        <%
          				 }if(mag.isAfter(oggi)){
          		        %>
          		        	<option value="giugno">Giugno</option>
          		        	<%
          				 } if(giu.isAfter(oggi)){
          		        %>
          		        	<option value="luglio">Luglio</option>
          		        	<%
          				 }if(lug.isAfter(oggi)){
          		        %>
          		        	<option value="agosto">Agosto</option>
          		        	<%
          				 } if(ago.isAfter(oggi)){
          		        %>
          		        	<option value="settembre">Settembre</option>
          		        	<%
          				 } if(set.isAfter(oggi)){
          		        %>
          		        	<option value="ottobre">Ottobre</option>
          		        	<%
          				 } if(ott.isAfter(oggi)){
          		        %>
          		       	 <option value="novembre">Novembre</option>
          		        <%
          				 }if(nov.isAfter(oggi)){
          		        %>
          		        <option value="dicembre">Dicembre</option>
          		        <%
          		        }
          		        %>
                     
                    </select> <span class="select-arrow"></span>
                  </div>
                </div>
                <div class="col-md-3" style="width: 370px; margin-top: 20px; margin-left: 9px; font-size: small; color: aliceblue;">
                  <p><b>NB: </b>sia per la data di inizio che di fine abbonamento, come giorno si intende il primo del mese selezionato</p>
                </div>
              </div>



              <div class="row">
                
                <div class="col-md-3">
                  <div class="form-btn">
                    <button class="submit-btn">Iscriviti</button>
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