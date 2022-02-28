<%@page import="java.util.*"%>
<%@page import="model.Prenotazione"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Pagina di registrazione</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">

<style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
    .fa-anchor,.fa-coffee {font-size:200px}

    /* Full-width input fields */
input {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input {
  background-color: #ddd;
  outline: none;
}

table {
  border-collapse: collapse;
 
}

th, td {
  padding: 8px;
  text-align: center;
  border-bottom: 1px solid #ddd;
}


hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
    </style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="redirectToLogin" class="w3-bar-item w3-button"> Login</a>
    <a href="redirectToRegistration" class="w3-bar-item w3-button"> Registrati</a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="redirectToIndex" class="w3-bar-item w3-button">Servizi</a>
      <a href="redirectToIndex" class="w3-bar-item w3-button">Orari</a>
      <a href="redirectToIndex" class="w3-bar-item w3-button">Contatti</a>

    </div>
  </div>
</div>

<% 
String cercaCampetti = (String)session.getAttribute("cercaCampetti");
session.setAttribute("utenteNonRegistrato", "1");

if(cercaCampetti == "0"){
%>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">

  <div>
    <form action="<%=request.getContextPath()%>/prenotazionePartita" method="post">
    <input type="hidden" id="azione" name="azione" value="cercaCampetti">
      <div class="container" style="padding-top: 80px; width: 100%" >
          
          <div style="width: 1200px;">
              <h1>Cerca un campetto disponibile</h1>
              <p style="width: 100%">Per piacere compila i seguenti campi, indicando quando vuoi giocare e a che sport. Ti mostreremo 
              quali sono i campetti disponibili nel giorno selezionato.</p>
               <%
           String alert = (String) session.getAttribute("alert");
			
			if(alert == "6"){ %>
			    
				 <p> ERRORE! Inserire una data valida</p>
				
		<% }else if(alert == "7"){ %>
			    
				  <p>CAMPI PIENI NEL GIORNO INSERITO! Per favore scegliere un'altra data</p>
				
		<% }else if(alert == "8"){ %>
			  
				  <p>SIAMO SPIACENTI MA IL COMPLESSO E' CHIUSO A DICEMBRE</p>
				
		<% }
			session.setAttribute("alert", "0");
		%>
          </div>
        <hr>
        
        <div style="height: 100px; width: 740px; margin-bottom: -25px; margin-left: 10px;">
          <div style="height: 25px; width: 130px;" >
            <label for="calcio">
              <input type="radio" id="calcio" name="sport" value="calcio" checked> 
              <p style="margin-top: -40px;">Calcio</p>
            </label>
          </div>
          <div style="height: 25px; width: 130px; margin-left: 150px; margin-top: -25px;" >
            <label for="paddel">
              <input type="radio" id="paddel" name="sport" value="paddel">
              <p style="margin-top: -40px;">Paddel</p>
            </label>
          </div>
          <div style="height: 25px; width: 130px; margin-left: 300px; margin-top: -25px;" >
            <label for="tennis">
              <input type="radio" id="tennis" name="sport" value="tennis">
              <p style="margin-top: -40px;">Tennis</p>
            </label>
          </div>
            
        </div>

        <div style="width: 300px; margin-bottom: 5px; margin-left: 10px;">
          <label for="dataNascita"><b>Quando vuoi giocare?</b></label>
          <input type="date" name="dataPartita"  required>
        </div>

        <div class="clearfix">
          <button type="submit" class="signupbtn">Verifica disponibilita'</button>
        </div>
        
        
          

      </div>
  </form>
  </div>

  
    


<!-- End page content -->
</div>


<%
}

else if (cercaCampetti == "1"){
	List<Prenotazione> prenotazioniDisponibili = (List<Prenotazione>) session.getAttribute("prenotazioniDisponibili");
%>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">

  <div>
    <form action="<%=request.getContextPath()%>/prenotazionePartita" method="post">
    <input type="hidden" id="azione" name="azione" value="cercaCampetti">
      <div class="container" style="padding-top: 80px; width: 100%" >
          
          <div style="width: 1200px;">
              <h1>Cerca un campetto disponibile</h1>
              <p style="width: 100%">Per piacere compila i seguenti campi, indicando quando vuoi giocare e a che sport. Ti mostreremo 
              quali sono i campetti disponibili nel giorno selezionato.</p>
              <%
           String alert = (String) session.getAttribute("alert");
			
			if(alert == "6"){ %>
			    
				 <p> ERRORE! Inserire una data valida</p>
				
		<% }else if(alert == "7"){ %>
			    
				  <p>CAMPI PIENI NEL GIORNO INSERITO! Per favore scegliere un'altra data</p>
				
		<% }else if(alert == "8"){ %>
			  
				  <p>SIAMO SPIACENTI MA IL COMPLESSO E' CHIUSO A DICEMBRE</p>
				
		<% }
			session.setAttribute("alert", "0");
			
		%>
          </div>
        <hr>
        
        <div style="height: 100px; width: 740px; margin-bottom: -25px; margin-left: 10px;">
          <div style="height: 25px; width: 130px;" >
            <label for="calcio">
              <input type="radio" id="calcio" name="sport" value="calcio" checked> 
              <p style="margin-top: -40px;">Calcio</p>
            </label>
          </div>
          <div style="height: 25px; width: 130px; margin-left: 150px; margin-top: -25px;" >
            <label for="paddel">
              <input type="radio" id="paddel" name="sport" value="paddel">
              <p style="margin-top: -40px;">Paddel</p>
            </label>
          </div>
          <div style="height: 25px; width: 130px; margin-left: 300px; margin-top: -25px;" >
            <label for="tennis">
              <input type="radio" id="tennis" name="sport" value="tennis">
              <p style="margin-top: -40px;">Tennis</p>
            </label>
          </div>
            
        </div>

        <div style="width: 300px; margin-bottom: 5px; margin-left: 10px;">
          <label for="dataNascita"><b>Quando vuoi giocare?</b></label>
          <input type="date" name="dataPartita"  required>
        </div>

        <div class="clearfix">
          <button type="submit" class="signupbtn">Verifica disponibilita'</button>
        </div>
        
        
          

      </div>
  </form>
  </div>

  <div style="margin-top: -290px; margin-left: 750px;">
    <table>
      <tr>
        <td><b>Orario</b></td>
        <td style="margin-left: 20px"><b>Campo</b></td>
      </tr>
     
      <%
					String orario;
            		for (Prenotazione p : prenotazioniDisponibili){
            			orario = p.getOraInizio() + " - " + p.getOraFine();
            			%>
		<tr>
			<td><%=orario %></td>
			<td><%=p.getCampo().getId() %></td>
		</tr>
		<% 
            			}
            			%>
      

    </table>
  </div>
    


<!-- End page content -->
</div>

<%

}
%>


<!-- Footer -->
<footer  class="w3-center w3-black w3-padding-16" style="background-color: #f44336; margin-top: 30px;">
  <p>Coppola Gianfranco &amp Evangelista Alessio</p>
</footer>

</body>
</html>
