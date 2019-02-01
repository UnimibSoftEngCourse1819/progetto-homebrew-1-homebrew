<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registrazione - Homebrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/register.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/form_control.js"></script>
</head>

<body>
	<div class="layout_left"></div>
	<div class="layout_right"></div>
	<div class="row page" class="container">
		<div class="header">
			<h2 class="title">BrewDay</h2>
			<jsp:include page="includer/menu.jsp" />
		</div>
		<div class="main">
			<div class="image_home">
				<img src="images/homepageBrew.jpg" alt="Image Home" />
			</div>
			<div class="home_desc">
				<h6>BrewDay ti permette di tenere traccia delle tue birre
					artigianali</h6>
				<p>
					Sarai in grado di creare le tue ricette e visualizzare quelle della
					community<br> Potrai gestire le tue miscele e provare quelle
					degli altri membri
				</p>
			</div>
			<div class="registration_cont">
				<h2>Iscriviti ora al portale</h2>

				<form id="registration_form" action="./register" method="post">
					<div>
						<h3>Nome*:</h3>
						<input name="name" type="text" placeholder="Nome">
					</div>
					<div>

						<h3>Cognome*:</h3>
						<input name="surname" type="text" placeholder="Cognome">
					</div>
					<div>
						<h3>Password*:</h3>
						<input id="new_password" name="new_password" type="password"
							autocomplete="off" placeholder="Password">
					</div>
					<div>
						<h3>Conferma password*:</h3>
						<input name="confirm_password" type="password" autocomplete="off" placeholder="Password">
					</div>
					<div>
						<h3>Data di nascita*:</h3>
						<input name="dateOfBirth" id="dateOfBirth" type="date" required
							pattern="[0-9]{4}/[0-9]{2}/[0-9]{2}">
					</div>
					<div>
						<h3>Email*:</h3>
						<input name="email" type="email" placeholder="Email">
					</div>
					<input name="registrationSubmit" type="submit" value="Iscriviti" id="addNewUser" disabled>
				</form>
			</div>

		</div>
	</div>
</body>

</html>

