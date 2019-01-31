<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registrazione - Homebrew</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/form_control.js"></script>
</head>
<body>
	<div id="page" class="container">
		<div id="header">
			<div id="logo">
			</div>
			<div id="menu">
				<ul>
					<li><a href="./home">Homepage</a></li>
					<li class="current_page_item">Registrati</li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<h2>brewDay!</h2>
					<span class="byline">Compila il form di iscrizione per
						diventare un birraio</span>
				</div>
			</div>


			<div id="featured">

				<form id="registration_form" action="./register" method="post">
					<table>
						<tr>
							<td><h3>Nome:</h3></td>
							<td><h3>
									<input name="name" type="text">
								</h3>
						</tr>
						<tr>
							<td><h3>Cognome:</h3></td>
							<td><h3>
									<input name="surname" type="text">
								</h3>
						</tr>
						<tr>
							<td><h3>Password:</h3></td>
							<td><h3>
									<input name="password" type="password">
								</h3>
						</tr>
						<tr>
							<td><h3>Conferma password:</h3></td>
							<td><h3>
									<input name="checkPassword" type="password">
								</h3>
						</tr>
						<tr>
							<td><h3>Data di nascita:</h3></td>
							<td><h3>
									<input name="dateOfBirth" type="date" placeholder="yyyy-mm-dd">
								</h3>
						</tr>
						<tr>
							<td><h3>Email:</h3></td>
							<td><h3>
									<input name="email" type="email">
								</h3>
						</tr>
					</table>
					<input name="registrationSubmit" type="submit" value="ISCRIVITI"
						id="addNewUser" disabled>

				</form>

			</div>
		</div>
	</div>
</body>
</html>