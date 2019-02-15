<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dati anagrafici&nbsp;-&nbsp;HomeBrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/account.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
<script src="js/account.js"></script>

</head>

<body>
	<jsp:include page="includer/alert.jsp" />
	<div class="header">
		<jsp:include page="includer/menu.jsp" />
	</div>
	<div class="row page account">
		<div class="row menu-top-color">
			<h4>ACCOUNT</h4>
		</div>
		<div class="row account-cont">
			<div class="col-4 account-menu">
				<ul>
					<li class="active" data-selector="account-general">Generali</li>
					<li data-selector="account-email">Email</li>
					<li data-selector="account-password">Password</li>
				</ul>
			</div>
			<div class="col-8 account-form-cont">
				<div class="row account-form account-general active">
					<h5>Generali</h5>
					<p>Per modificare i tuoi dati inserisci la password attuale.</p>
					<form action="./account" method="post" name="user_form">
						<input name="type" type="hidden" value="general">
						<div>
							<h6>Nome</h6>
							<input name="name" type="text" value="${user.name}">
						</div>
						<div>
							<h6>Cognome</h6>
							<input name="surname" type="text" value="${user.surname}">
						</div>
						<div>
							<h6>Data di nascita</h6>
							<input name="dateOfBirth" id="dateOfBirth" type="date"
								value="${user.dateOfBirth}" required
								pattern="[0-9]{4}/[0-1][0-9]/[0-3][0-9]">
						</div>
						<div>
							<h6>Password</h6>
							<input name="password" type="password" autocomplete="off">
						</div>

						<button name="updateGeneral" type="submit" id="updateGeneral" disabled>AGGIORNA</button>
					</form>
				</div>
				<div class="row account-form account-email">
					<h5>Email</h5>
					<p>Per modificare l'email inserisci la password attuale.</p>
					<form action="./account" method="post" name="user_form">
						<input name="type" type="hidden" value="email">
						<div>
							<h6>Email</h6>
							<input name="email" type="email" value="${user.email}">
						</div>
						<div>
							<h6>Password</h6>
							<input name="password" type="password" autocomplete="off">
						</div>
						<c:if test="${errorEmail != null}">
							<div class="error_email">
								<p>L'email &eacute; gi&agrave; presente nel sistema</p>
							</div>
						</c:if>

						<button name="updateEmail" type="submit" id="updateEmail" disabled>AGGIORNA</button>
					</form>
				</div>
				<div class="row account-form account-password">
					<h5>Password</h5>
					<p>Per modificare la password inserisci la password attuale.</p>
					<p>La nuova password deve contenere almeno 8 caratteri.</p>

					<form action="./account" method="post" name="user_form">
						<input name="type" type="hidden" value="password">
						<div>
							<h6>Password attuale</h6>
							<input id="old_password" name="old_password" type="password"
								autocomplete="off">
						</div>
						<div>
							<h6>Nuova password</h6>
							<input id="new_password" name="new_password" type="password"
								autocomplete="off">
						</div>
						<div>
							<h6>Conferma password</h6>
							<input name="confirm_password" type="password" autocomplete="off">
						</div>


						<button name="updatePassword" type="submit" id="updatePassword" disabled>AGGIORNA</button>
					</form>
				</div>
			</div>
		</div>
	</div>






</body>
</html>