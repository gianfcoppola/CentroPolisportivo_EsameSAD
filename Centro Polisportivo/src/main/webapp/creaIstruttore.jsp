<%@page import="model.Utente"%>
<%@page import="java.util.*"%>
<%@page import="model.Corso"%>
<%@page import="model.Istruttore"%>
<%@page import="database.CorsoDao"%>
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
</style>
<body>

	<%
	Utente utente = (Utente) session.getAttribute("utente");
	String nome = utente.getNome();
	String cognome = utente.getCognome();
	
	List<Corso> listaCorsi = CorsoDao.selectCorsi();
	String mod = (String) session.getAttribute("creaIstruttoreDaZero");
	String isIstruttore = (String)session.getAttribute("istruttore");
	
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
              <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Inserisci un nuovo istruttore</p>
              
              <form action="<%=request.getContextPath()%>/GestioneIstruttoriServlet" method="post">
              <input type="hidden" id="azioneIstruttore" name="azioneIstruttore" value="crea">
                <div class="row" style="margin-left: 30px;">

                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px; width: 350px">
                    <div class="form-group">
                      <span class="form-label" style="width: 450px">In che modo vuoi creare il nuovo istruttore</span> 
                      <select class="form-control" id="tipoInserimento" name="tipoInserimento">
                        <option value="utenteRegistrato">Eleva utente a	istruttore</option>
						<option value="nuovoUtente">Crea un nuovo istruttore</option>
                      </select> 
                      <span class="select-arrow"></span>
                    </div>
                  </div>
                </div>
                <div class="row" style="margin-left: 30px;">
  
                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px;">
                    <div class="form-btn">
                      <button class="submit-btn">Vai</button>
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

					else if (mod.equals("1")){
						mod = "3";
						session.setAttribute("creaIstruttoreDaZero", mod);
						List<Utente> utentiRegistrati = (List<Utente>) session.getAttribute("utentiRegistrati");
						String infoUtente;
						%>
						
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">
	<%
				
				String alert = (String) session.getAttribute("alert");
						if(alert == "34"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p> ERRORE! Non lasciare campi vuoti, inserire tutti i campi obbligatori.</p>
						</div>
					<%
					}
					else if(alert == "35"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Assicurarsi di aver inserito due volte la stessa password.</p>
					</div>
					<%
					}
					else if(alert == "36"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Utente già esistente.</p>
					</div>
					<%
					}
					else if(alert == "37"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Username già utilizzato da un altro utente, sceglierne un altro.</p>
					</div>
					<%
					}
					else if(alert == "38"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Numero di telefono non valido.</p>
					</div>
					<%
					}
					else if(alert == "39"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Email non valida.</p>
					</div>
					<%
					}
					else if(alert == "40"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Data di nascita non valida.</p>
					</div>
					<%
					}
					else if (alert == "41"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> Utente già esistente! Eleva i suoi permessi da "user" a ""istruttore"</p>
					</div>
					<%
					}
					alert = "0";
					%>
    	<div id="booking" class="section" style="margin-left: -40px; height: 100%;">
      		<div class="section-center" style="height: 100%;">
		        <div class="container" style="height: 100%;">
		          <div class="row" style="height: 850px;">
		            <div class="booking-form" style="height: 100%;">
		              <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Inserisci un nuovo istruttore</p>
		              <form action="<%=request.getContextPath()%>/GestioneIstruttoriServlet" method="post">
              <input type="hidden" id="azioneIstruttore" name="azioneIstruttore" value="crea"> 
		                <div class="row" style="margin-left: 30px;">

		                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px; width: 350px">
		                    <div class="form-group">
		                      <span class="form-label" style="width: 450px">Scegli utente da elevare a ruolo di istruttore</span> 
		                      <select class="form-control" id="listaUtenti" name="listaUtenti">
						                        <%
										for (Utente u : utentiRegistrati) {
											infoUtente = u.getId() + " - " + u.getNome() + " " + u.getCognome();
											
										%>
										<option value="<%=infoUtente%>"><%=infoUtente%></option>
								
									<%
										}
									
											%>
								</select>
		                      <span class="select-arrow"></span>
		                    </div>
		                  </div>
		                </div>
		                
		                <div class="row" style="margin-left: 30px;">

		                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px; width: 350px">
		                    <div class="form-group">
		                      <span class="form-label" style="width: 450px">Sport</span> 
		                      <select class="form-control" id="sport" name="sport">
		                        <option value="tennis">Tennis</option>
								<option value="paddel">Paddel</option>
		                      </select> 
		                      <span class="select-arrow"></span>
		                    </div>
		                  </div>
		                </div>
		                
		                <div class="row" style="margin-left: 30px;">
											<div class="col-md-6" style="margin-left: 20px; margin-top: 20px;">
												<div class="form-group">
													<span class="form-label">Inserisci una descrizione</span>
								                      <textarea class="form-control" style="height: 100px; border-radius: 20px;" id="descrizioneCorso"
								                        name="descrizioneCorso" rows="4" cols="50" required></textarea>
												</div>
											</div>
											
						</div>
		                
		                
		                
						<div class="row" style="margin-left: 30px;">

		                  <div class="col-md-6">
		                    <div class="form-btn">
		                      <button class="submit-btn">Insert</button>
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

					else if (mod.equals("2")){
						mod = "4";
						session.setAttribute("creaIstruttoreDaZero", mod);
						%>
						
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px height: 100%;">
	<%
				
				String alert = (String) session.getAttribute("alert");
						if(alert == "34"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p> ERRORE! Non lasciare campi vuoti, inserire tutti i campi obbligatori.</p>
						</div>
					<%
					}
					else if(alert == "35"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Assicurarsi di aver inserito due volte la stessa password.</p>
					</div>
					<%
					}
					else if(alert == "36"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Utente già esistente.</p>
					</div>
					<%
					}
					else if(alert == "37"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Username già utilizzato da un altro utente, sceglierne un altro.</p>
					</div>
					<%
					}
					else if(alert == "38"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Numero di telefono non valido.</p>
					</div>
					<%
					}
					else if(alert == "39"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Email non valida.</p>
					</div>
					<%
					}
					else if(alert == "40"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> ERRORE! Data di nascita non valida.</p>
					</div>
					<%
					}
					else if (alert == "41"){%>
					<div class="alert alert-danger" role="alert"  style="top: 60px">
					 <p> Utente già esistente! Eleva i suoi permessi da "user" a ""istruttore"</p>
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
		              <p style="margin-left: -60px; width: 1000px; color: aliceblue; font-size: 50px; text-align: center; margin-bottom: 30px;">Inserisci un nuovo istruttore</p>
		              <form action="<%=request.getContextPath()%>/GestioneIstruttoriServlet" method="post">
              <input type="hidden" id="azioneIstruttore" name="azioneIstruttore" value="crea">
		                		                
		                <div class="row" style="margin-left: 30px;">

		                  <div class="col-md-3" style="margin-left: 20px; margin-top: 20px; width: 350px">
		                    <div class="form-group">
		                      <span class="form-label" style="width: 450px">Sport</span> 
		                      <select class="form-control" id="sport" name="sport">
		                        <option value="tennis">Tennis</option>
								<option value="paddel">Paddel</option>
		                      </select> 
		                      <span class="select-arrow"></span>
		                    </div>
		                  </div>
		                </div>
		                
		                <div class="row" style="margin-left: 30px;">
											<div class="col-md-6" style="margin-left: 20px; margin-top: 20px;">
												<div class="form-group">
													<span class="form-label">Inserisci una descrizione</span>
								                      <textarea class="form-control" style="height: 100px; border-radius: 20px;" id="descrizioneCorso"
								                        name="descrizioneCorso" rows="4" cols="50" required></textarea>
												</div>
											</div>
											
						</div>
						
						<div class="row" style="margin-left: 30px;">
                          <div class="col-md-3"  style="width: 300px; margin-left: 20px; margin-top: 20px;">
                            <div class="form-group">
                              <input class="form-control" style="margin-bottom: 15px;" type="text" name="nome" placeholder="Nome" required>
                              
                              <input class="form-control" style="margin-bottom: 15px;" type="text" name="username" placeholder="Username" required>
                              <input class="form-control" style="margin-bottom: 15px;" type="password" name="password" placeholder="Password" required>
                             
                              
                              <input class="form-control" style="margin-bottom: 15px;" type="date" name="dataNascita" placeholder="Data di nascita" required>
                              <input class="form-control" style="margin-bottom: 15px;" type="text" name="email" placeholder="Email" required>
                              <input class="form-control" style="margin-bottom: 15px;" type="text" name="cellulare" placeholder="Cellulare" required>
                            </div>
                          </div>

                          <div class="col-md-3"  style="width: 300px; margin-left: 20px; margin-top: 20px;">
                            <div class="form-group">
                              
                              <input class="form-control" style="margin-bottom: 79px;" type="text" name="cognome" placeholder="Cognome" required>
                             
                             
                              <input class="form-control" style="margin-bottom: 15px;" type="password" name="confermaPassword" placeholder="Conferma Password" required>
                              
                              
                            </div>
                          </div>
                          
                </div>
		                
		                
		                
						<div class="row" style="margin-left: 30px;">

		                  <div class="col-md-6">
		                    <div class="form-btn">
		                      <button class="submit-btn">Insert</button>
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