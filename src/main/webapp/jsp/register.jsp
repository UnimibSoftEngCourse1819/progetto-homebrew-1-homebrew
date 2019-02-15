<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registrazione&nbsp;-&nbsp;Homebrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/register.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/form_control.js"></script>
</head>

<body>
	<jsp:include page="includer/alert.jsp" />
	<div class="header">
		<jsp:include page="includer/menu.jsp" />
	</div>
	<div class="row page">
		<div class="registration_cont">
			<h2>ISCRIVITI</h2>

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
					<input name="dateOfBirth" id="dateOfBirth" type="date"
						placeholder="AAAA-MM-DD" required
						pattern="[0-9]{4}/[0-1][0-9]/[0-3][0-9]">
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

				<button name="registrationSubmit" type="submit" id="addNewUser"
					disabled>Iscriviti</button>
			</form>
		</div>

	</div>
</body>

</html>

