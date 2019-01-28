<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registrazione</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"
	rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" />


</head>
<body>
	<div id="page" class="container">
		<div id="header">
			<div id="logo">
				<form method="post" action="./Login">
					<table>
						<tr>
							<td>User:</td>
							<td><input type="text" name="user" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;"><input
								type="submit" value="Accedi" id="submit"></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="menu">
				<ul>
					<li><a href="../homebrew/index.html">Homepage</a></li>
					<li class="current_page_item">Registrati</li>
					<li><a href="./ricette">Ricette</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="../images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<h2>brewDay!</h2>
					<span class="byline">Compila il form di iscrizione per
						diventare un birraio</span>
				</div>
			</div>
			<h1>GESTIRE ERRORI PRIMA INVIO FORM</h1>
			<h2>- password coincidono</h2>
			<h2>- email corretta</h2>

			<h1>GESTIRE ERRORI DOPO INVIO FORM</h1>
			<h2>- email e' una chiave sul db e non puo' essere gia' 
				presente</h2>


			<div id="featured">
				<ul class="style1">
					<li class="first">
						<form action="./registrazione" method="post">
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
											<input name="dateOfBirth" type="date">
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
								id="addNewUser">

						</form>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>