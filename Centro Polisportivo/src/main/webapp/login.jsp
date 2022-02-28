<!DOCTYPE html>
<html>
<title>Login Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
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
  <div class="w3-bar w3-white w3-wide w3-padding w3-card" style="height: 54px;">
    <div style="margin-top: -15px;">
      <a href="redirectToLogin" class="w3-bar-item w3-button" style="font-size: 15px;"> Login</a>
      <a href="redirectToRegistration" class="w3-bar-item w3-button"  style="font-size: 15px;"> Registrati</a>
    </div>
    
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a href="redirectToIndex" class="w3-bar-item w3-button">Servizi</a>
      <a href="redirectToIndex" class="w3-bar-item w3-button">Orari</a>
      <a href="redirectToIndex" class="w3-bar-item w3-button">Contatti</a>

    </div>
  </div>
</div>


<div class="w3-content w3-padding" style="max-width:1564px; margin-top: 110px;" >
  <div class="container" id="container"   >
    <div class="form-container sign-in-container"  >
        <form action="<%=request.getContextPath()%>/login" method="post">
            <h1>Login</h1>

            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>

            <button>Login</button>
        </form>
        		<%
					String alert = (String) session.getAttribute("alert");
					
					if(alert == "1"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						 <p style="margin-top: -390px; margin-left: 20px;"> Errore! Campi obbligatori non inseriti!</p>
						  
						</div>
				<% }else if(alert == "2"){ %>
					    <div class="alert alert-danger" role="alert"  style="top: 60px">
						  <p style="margin-top: -390px; margin-left: 20px;">Errore! Username o Password non esistenti!</p>
						  
						</div>
				<% }else if(alert == "8"){ %>
					    <div class="alert alert-success" role="alert" style="top: 60px" >
						 <p style="margin-top: -390px; margin-left: 20px;"> Registrazione effettuata con successo, ora effettua il login!</p>
						  
						</div>
				<% 
					}
				%>
        
    </div>
    <form action="<%=request.getContextPath()%>/redirectToRegistration" method="post">
        <div class="overlay-container">

            <div class="overlay">

                <div class="overlay-panel overlay-right">
                    <h1>Non sei ancora iscritto?</h1>
                    <p>Clicca qui per registrarti</p>
                    <div class="form-btn">
                      <button class="submit-btn" style="background-color: white; color: #f44336;">Registrati</button>
                    </div>
                </div>
                

            </div>

        </div>
    </form>
</div>
</div>




<!-- Footer -->



</body>
</html>
