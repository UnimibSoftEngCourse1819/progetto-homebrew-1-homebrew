<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aggiornamento dati anagrafici&nbsp;-&nbsp;Homebrew</title>
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
		<div class="main_shadow"><div></div></div>
			<div class="row main">
				<c:set var = "item" value = "${user}"/>
				<form action="./edit_account" method="post" name="user_form">
					<div>
						<h3>Nome:</h3>
						<input name="name" type="text" value = "${item.name}">
					</div>
					<div>

						<h3>Cognome:</h3>
						<input name="surname" type="text" value = "${item.surname}">
					</div>
					<div>
						<h3>Data di nascita:</h3>
						<input name="dateOfBirth" id="dateOfBirth" type="date"
							value = "${item.dateOfBirth}" required
							pattern="[0-9]{4}/[0-1][0-9]/[0-3][0-9]">
					</div>
					<div>
						<h3>Email:</h3>
						<input name="email" type="email"
							value = "${item.email}">
					</div>
					<div>
						<h3>Password Attuale:</h3>
						<input id="old_password" name="old_password" type="password"
							autocomplete="off" placeholder="Inserisci Password attuale">
					</div>
					<div>
						<h3>Nuova Password [minimo 8 caratteri]:</h3>
						<input id="new_password" name="new_password" type="password"
							autocomplete="off" placeholder="Inserisci una nuova Password">
					</div>
					<div>
						<h3>Conferma password:</h3>
						<input name="confirm_password" type="password" autocomplete="off"
							placeholder="Conferma la nuova Password">
					</div>
					<c:if test="${errorEmail != null}">
						<div class="error_email">
							<p>L'email e' gia' presente nel sistema</p>
						</div>
					</c:if>

					<button name="userUpdateButton" type="submit" id="updateUser">AGGIORNA</button>
				</form>
			</div>

		</div>
	</div>
</body>

</html>

