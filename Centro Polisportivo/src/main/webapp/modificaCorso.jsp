<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Corso"%>
<%@page import="model.Istruttore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<%
int tipo = Integer.parseInt((String) session.getAttribute("tipologiaUtente"));
%>

<title>Modifica corso</title>
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
	String ruolo = utente.getRuolo();
	
	List<Corso> listaCorsi = (List<Corso>)session.getAttribute("listaCorsi");
	String mod = (String) session.getAttribute("modificaCorso");
	
	
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
	
	<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer; height: 100%;" title="close side menu"
    id="myOverlay"></div>

<% 
				//la prima volta che accedo alla pagina
					if (mod.equals("0")){
						%>

   <div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">

    <div id="booking" class="section" style="margin-left: -40px; height: 100%; font-size: 14px;">
      <div class="section-center" style="height: 100%;">
        <div class="container" style="height: 100%;">
          <div class="row" style="height: 577px;">
            <div class="booking-form" style="height: 100%;">
              <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Modifica corso</p>
              
              <form action="<%=request.getContextPath()%>/GestioneCorsiServlet" method="post">
               <input type="hidden" id="azioneCorso" name="azioneCorso" value="modifica">
                <div class="row" style="margin-left: 30px;">

                  <div class="col-md-6" style="margin-left: 20px; margin-top: 20px;">
                    <div class="form-group">
                      <span class="form-label">Scegli corso da modificare</span> 
                      <select class="form-control" id="corso" name="corso">
                        <%
                        String infoCorso;
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
  
                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
                    <div class="form-btn">
                      <button class="submit-btn">Modifica corso</button>
                    </div>
                  </div>
                </div>
                <div class="row" style="margin-left: 30px;">
               
              <p style="margin-left: -60px; margin-top:30px; width: 1000px; color: aliceblue; font-size: 25px; text-align: center;">Info corsi</p>
                <table>


                  
                  <tr>
			<td><b>ID</b></td>
			<td><b>Sport</b></td>
			<td><b>Istruttore</b></td>
			<td><b>Nome corso</b></td>
			<td><b>N. lezioni a settimana</b></td>
			<td><b>Giorni e orari lezioni</b></td>
			<td><b>Prezzo</b></td>
		</tr>

		<%
					String infoIstruttore, giorniLezioni;
            		for (Corso c: listaCorsi){
            			giorniLezioni = "";
            			infoIstruttore = c.getIstruttore().getId() + " - " + c.getIstruttore().getNome() + " " + c.getIstruttore().getCognome();
            			%>
		<tr>
			<td><%=c.getId() %></td>
			<td><%=c.getSport() %></td>
			<td><%=infoIstruttore %></td>
			<td><%=c.getDescrizione() %></td>
			<td><%=c.getNumeroLezioniSettimanali() %></td>
			<%
			
			if(c.getGiorniLezioni()[0]==1)
				giorniLezioni = giorniLezioni + "lunedi " + c.getOrariLezioni()[0] + "\n";
			if(c.getGiorniLezioni()[1]==1)
				giorniLezioni = giorniLezioni + "martedi " + c.getOrariLezioni()[1] + "\n";
			if(c.getGiorniLezioni()[2]==1)
				giorniLezioni = giorniLezioni + "mercoledi " + c.getOrariLezioni()[2] + "\n";
			if(c.getGiorniLezioni()[3]==1)
				giorniLezioni = giorniLezioni + "giovedi " + c.getOrariLezioni()[3] + "\n";
			if(c.getGiorniLezioni()[4]==1)
				giorniLezioni = giorniLezioni + "venerdi " + c.getOrariLezioni()[4] + "\n";
			
			%>
			<td><%=giorniLezioni%></td>
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

	<%
					}

					else if (mod.equals("1")){
						mod = "3";
						Corso c = (Corso) session.getAttribute("datiCorso");
						List<Istruttore> listaIstruttori = (List<Istruttore>) session.getAttribute("listaIstruttori");
						String infoIstruttore;
						
						%>
						
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">
	<%
				
				String alert = (String) session.getAttribute("alert");
						if(alert == "23"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p> ERRORE! Inserire una descrizione.</p>
						</div>
					<%
					}
					
					else if (alert == "24"){
							%>
						    <div class="alert alert-danger" role="alert"  style="top: 60px">
							 <p> ERRORE! Inserire un valore consentito nel campo "Prezzo mensile"!</p>
							</div>
					<%
					}
					else if (alert == "25"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p> ERRORE! Inserire un valore consentito nel campo "Numero lezioni settimanali"!</p>
						</div>
					<%
					}
					
					else if (alert == "26"){
						%>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p> ATTENZIONE! Si è verificato un qualche errore durante la procedura di modifica del corso.</p>
						</div>
					<%
					}
					else if (alert == "27"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Assicurati di selezionare il numero corretto di lezioni settimanali.</p>
					</div>
					<%
					}
					alert = "0";
					%>
    	<div id="booking" class="section" style="margin-left: -40px; height: 100%;">
      		<div class="section-center" style="height: 100%;">
		        <div class="container" style="height: 100%;">
		          <div class="row" style="height: 1050px;">
		            <div class="booking-form" style="height: 100%;">
		              <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Modifica corso</p>
		              <form action="<%=request.getContextPath()%>/GestioneCorsiServlet" method="post">
               			<input type="hidden" id="azioneCorso" name="azioneCorso" value="modifica">
		                <div class="row" style="margin-left: 30px;">
									<div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
										<div class="form-group">
											<span class="form-label">ID Corso</span> 
                      						<input class="form-control"
						                        style="width: 130px; height: 40px; text-align: center; margin-left: 10px;" type="text"
						                        name="id" value="<%=c.getId() %>" readonly>
										</div>
									</div>
									<div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
										<div class="form-group">
											<span class="form-label">Sport</span> 
						                      <input class="form-control"
						                        style="width: 130px; height: 40px; text-align: center; margin-left: 10px;" type="text"
						                        name="sport" value="<%=c.getSport() %>" readonly>
										</div>
									</div>
						</div>
		                <%
						
		                //ADMIN	
						if (ruolo.equalsIgnoreCase("admin")){
							%>
							<div class="row" style="margin-left: 30px;">
									<div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
										<div class="form-group">
											<span class="form-label" style="width: 400px;">Scegli il nuovo istruttore del corso</span> 
						                      <select class="form-control" id="istruttore" name="istruttore" style="margin-left: 10px;">
											            	<%
												for (Istruttore i : listaIstruttori) {
													infoIstruttore = i.getId() + " - " + i.getNome() + " " + i.getCognome();
													if(i.getId() == c.getIstruttore().getId()){
												%>
												<option value="<%=infoIstruttore%>" selected><%=infoIstruttore%></option>
												
													<%
													}
													else{
														%>
														<option value="<%=infoIstruttore%>"><%=infoIstruttore%></option>
													<%
													}
												}%>
												</select> <span class="select-arrow"></span>
										</div>
									</div>
									
								</div>
							<%
						}
						
						if (ruolo.equalsIgnoreCase("istruttore")){
							%>
							
							<div class="row" style="margin-left: 30px;">
									<div class="col-md-6" style="margin-left: 20px; margin-top: 20px;">
										<div class="form-group">
											<span class="form-label">Inserisci una descrizione del corso</span>
						                      <textarea class="form-control" style="height: 100px; border-radius: 20px;" id="descrizioneCorso"
						                        name="descrizioneCorso" rows="4" cols="50" required><%=c.getDescrizione() %></textarea>
										</div>
									</div>
									
							</div>
							
							<div class="row" style="margin-left: 30px;">
			                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
			                    <div class="form-group">
			                      <span class="form-label">Numero lezioni a settimana</span>
			
			                      <input class="form-control" style="width: 100px; margin-left: 10px;" type="number" id="numeroLezioni"
			                        name="numeroLezioni" min="1" max="5" value="1">
			
			
			                    </div>
			                  </div>

			                  <div class="col-md-3" style="margin-left: 160px; margin-top: 20px; width: 130px;" >
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

			                  <div class="col-md-3" style="margin-left: 60px; margin-top: 20px;">
			                    <div class="form-group">
			                      <span class="form-label">Prezzo mensile</span>
			                      <input class="form-control" style="width: 100px; margin-left: 10px;" type="text" name="prezzoMensile" value="<%=c.getPrezzoMensile() %>">
			                     
			
			
			                    </div>
			                  </div>
               				</div>
               				
               				<div class="row" style="margin-left: 30px;">
               				
               					<div class="col-md-3" style="margin-left: 20px; margin-top: 20px; width: 200px;" >
                    <div class="form-group">
                      <span style="width: 300px;" class="form-label">Scegli giorni e ora delle lezioni</span>
                     
                            <input type="checkbox" id="giorno" name="giorno" value="lunedi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 3px; margin-bottom: 2px;" checked>
                              <div style="margin-top: -41px; margin-left: 75px; margin-bottom: 21px;">
                                Lunedi'
                              </div>
                          
                            <input type="checkbox" id="giorno" name="giorno" value="martedi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 3px; margin-bottom: 2px;">
                          
                              <div style="margin-top: -41px; margin-left: 75px; margin-bottom: 21px;">
                                Martedi'
                              </div>
                            <input type="checkbox" id="giorno" name="giorno" value="mercoledi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 3px; margin-bottom: 2px;">

                              <div style="margin-top: -41px; margin-left: 75px; margin-bottom: 21px;">
                                Mercoledi'
                              </div>
                          
                            <input type="checkbox" id="giorno" name="giorno" value="giovedi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 3px; margin-bottom: 2px;">
                              <div style="margin-top: -41px; margin-left: 75px; margin-bottom: 21px;">
                                Giovedi'
                              </div>
                          
                            <input type="checkbox" id="giorno" name="giorno" value="venerdi"
                              style="margin-left: 30px; width: 25px; height: 50px; margin-top: 3px; margin-bottom: 2px;">
                              <div style="margin-top: -41px; margin-left: 75px; margin-bottom: 5px;">
                                Venerdi'
                              </div>
                    </div>
                  </div>
               					
               					<div class="col-md-3" style="margin-left: 20px; width: 130px; margin-top: 20px;">
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
               				
               				
							<%
						
						}
						
						%>
						<div class="row">

		                  <div class="col-md-6">
		                    <div class="form-btn">
		                      <button class="submit-btn">Modifica corso</button>
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