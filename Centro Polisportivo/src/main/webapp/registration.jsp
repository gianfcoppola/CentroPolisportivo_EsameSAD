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

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">

   <form action="<%=request.getContextPath()%>/registration" method="post">
        <div class="container" style="padding-top: 80px;" >
            
            <div style="width: 300px;">
                <h1>Sign Up</h1>
          <p style="width: 800px">Per piacere compila i seguenti campi, dopodiche' clicca su "Sign Up" ed effettua il login.</p>
          
           <%
           String alert = (String) session.getAttribute("alert");
			
			if(alert == "1"){ %>
			    
				 <p style= "width: 500px"> Errore! Campi obbligatori non inseriti!</p>
				
		<% }else if(alert == "2"){ %>
			    
				  <p style= "width: 500px">Errore! Le due password non combaciano</p>
				
		<% }else if(alert == "3"){ %>
			  
				  <p style= "width: 500px">Attenzione! Utente già registrato</p>
				
		<% }else if(alert == "4"){ %>
			    
				  <p style= "width: 500px">Attenzione! Username già utilizzato, sceglierne uno diverso</p>
			
		<% }else if(alert == "5"){ %>
			  
				  <p style= "width: 500px">Errore! Numero di cellulare non valido!</p>
			
		<% }else if(alert == "6"){ %>
			  
				  <p style= "width: 500px">Errore! Email inserita non valida!</p>
			
		<% }else if(alert == "7"){ %>
			   
				  <p style= "width: 500px">Errore! Data di nascita inserita non valida!</p>
				
		<% 
			}
		%>
         
           
          <hr>
      
          <label for="username"><b>Username</b></label>
          <input type="text" placeholder="Username" name="username" required>

          <div style="height: 30px; width: 300px;">
            <div>
                <label for="password"><b>Password</b></label>
                <input type="password" name="password" placeholder="Password" required>
            </div>

            <div style="margin-top: -105px; margin-left: 450px; width: 300px;">
                <label for="confermaPassword"><b>Conferma password</b></label>
                <input type="password" name="confermaPassword" placeholder="Conferma Password" required>
            </div>

          </div>

          <div style="height: 30px; width: 300px; margin-top: 77px; margin-bottom: 77px;">
              <div>
                <label for="nome"><b>Nome</b></label>
                <input type="text" name="nome" placeholder="Nome" required>
              </div>

              <div style="margin-top: -105px; margin-left: 450px; width: 300px;">
                <label for="cognome"><b>Cognome</b></label>
                <input type="text" name="cognome" placeholder="Cognome" required>
            </div>
           <input type="hidden" name="ruolo" value="user">
  
           
          </div>
          
          

          

          <label for="dataNascita"><b>Data di nascita</b></label>
          <input type="date" name="dataNascita" placeholder="Data di nascita" required>

          <label for="email"><b>Email</b></label>
          <input type="email" name="email" placeholder="Email" required>

          <label for="cellulare"><b>Cellulare</b></label>
          <input type="text" name="cellulare" placeholder="Cellulare" required>

          
          
           
          
          
          
      
          <div class="clearfix">
            
            <button type="submit" class="signupbtn">Sign Up</button>
          </div>
            </div>
          
            <div style="margin-top: -850px; margin-left: 700px;">
                <img src="img/profile image.png" style="width:40%;"  onclick="onClick(this)" alt="Concrete meets bricks">
            </div>

        </div>
      </form>


<!-- End page content -->
</div>



<!-- Footer -->
<footer  class="w3-center w3-black w3-padding-16" style="background-color: #f44336; margin-top: 700px;">
  <p>Coppola Gianfranco &amp Evangelista Alessio</p>
</footer>

</body>
</html>
