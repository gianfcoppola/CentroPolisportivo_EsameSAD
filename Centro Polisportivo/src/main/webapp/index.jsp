<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Complesso Sportivo Coppola Evangelista</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">

<style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
    .fa-anchor,.fa-coffee {font-size:200px}
    </style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="redirectToLogin" class="w3-bar-item w3-button"> Login</a>
    <a href="redirectToRegistration" class="w3-bar-item w3-button"> Registrati</a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="#servizi" class="w3-bar-item w3-button">Servizi</a>
      <a href="#orari" class="w3-bar-item w3-button">Orari</a>
      <a href="#contatti" class="w3-bar-item w3-button">Contatti</a>

    </div>
  </div>
</div>

<!-- Header -->
<header class="w3-display-container w3-content w3-wide" style="max-width:1500px;" id="home">
  <img class="w3-image" src="img/sfondoIndex.png" alt="Architecture" width="1500" height="800">
  <div class="w3-display-middle w3-margin-top w3-center">
    <h1 class="w3-xxlarge w3-text-white"><span class="w3-padding w3-black w3-opacity-min"><b>Centro polisportivo</b></span></h1>
    <h1 style="margin-top: -7px;" class="w3-xxlarge w3-text-white"><span class="w3-padding w3-black w3-opacity-min"><b>Coppola &amp Evangelista</b></span></h1>
  </div>
</header>

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">

  
     
     

<!-- Servizi Section -->
<div class="w3-container w3-padding-32" id="servizi">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Servizi</h3>
    <p>Il centro polisportivo Coppola &amp; Evangelista mette a disposizione dei propri clienti dei magnifici campetti di calcio, tennis e paddel. 
        I campi da calcio sono disponibili tutti i giorni dalle 15:00 alle 24:00 e hanno un costo di 50 &euro; l'ora, mentre quelli da tennis e 
        paddel dalle 15:00 alle 21:00 al costo di 40 &euro; l'ora.</p>
        <p>Cerca subito un campetto! Per vedere gli orari disponibili clicca <a href="RedirectToCercaCampettiDisponibiliServlet">qui</a>. </p>
        
        <br>
        <div class="w3-row-padding">
            <img src="img/campetti.png" style="width:100%" onclick="onClick(this)" alt="Concrete meets bricks">
          
        
            
          </div>
    <p>Sei nuovo o vuoi migliorare in sport come il tennis o il paddel? No problem, il centro polisportivo propone diversi corsi di insegnamento 
         per ogni fascia di eta', secondo i programmi delle varie Federazioni sportive nazionali, seguiti da uno 
        staff di tecnici altamente qualificati in un ambiente unico, moderno, tranquillo e funzionale. 
    <p>I corsi si tengono dal lunedi' al venerdi' dalle 15:00 alle 19:00. <a href="redirectToLogin">Scopri</a> subito il corso che fa per te</p>
    
    
  </div>

  <br>
<!-- Orari Section -->
<div class="w3-container w3-padding-32" id="orari">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Orari di apertura</h3>
    <p><b>Campi di calcio</b>: tutti i giorni dalle <b>15:00</b> alle <b>24:00</b></p>
    <p><b>Campi di tennis e paddel</b>: tutti i giorni dalle <b>15:00</b> alle <b>21:00</b></p>
    
  </div>

  <!-- Contact Section -->
  <div class="w3-container w3-padding-32" id="contatti">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Contatti</h3>
    <p>Per maggiori informazioni o per evetuali problemi in caso di prenotazioni o registrazioni, 
        rivolgersi direttamente ai numeri <b>3664479949</b> e <b>3341200412</b> oppure contattare
        il seguente indirizzo e-mail: <b>gianf.coppola@gmail.com</b>.</p>
    
  </div>
  


<!-- End page content -->
</div>


<!-- Footer -->
<footer class="w3-center w3-black w3-padding-16">
  <p>Coppola Gianfranco &amp Evangelista Alessio</p>
</footer>

</body>
</html>
