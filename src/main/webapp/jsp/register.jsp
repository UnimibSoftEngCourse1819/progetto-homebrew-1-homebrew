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
			<div class="registration_cont">
				<h2>Iscriviti ora al portale</h2>

				<form id="registration_form" action="./register" method="post">
					<div>
						<h3>Nome:</h3>
						<input name="name" type="text" placeholder="Inserisci il tuo Nome">
					</div>
					<div>

						<h3>Cognome:</h3>
						<input name="surname" type="text"
							placeholder="Inserisci il tuo Cognome">
					</div>
					<div>
						<h3>Data di nascita:</h3>
						<input name="dateOfBirth" id="dateOfBirth" type="date" required
							pattern="[0-9]{4}/[0-9]{2}/[0-9]{2}">
					</div>
					<div>
						<h3>Email:</h3>
						<input name="email" type="email"
							placeholder="Inserisci la tua Email">
					</div>
					<div>
						<h3>Password [minimo 8 caratteri]:</h3>
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

					<input name="registrationSubmit" type="submit" value="Iscriviti"
						id="addNewUser" disabled>
				</form>
			</div>

		</div>
	</div>
</body>

</html>

