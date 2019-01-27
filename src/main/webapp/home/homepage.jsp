<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"
	rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css"/>
<link href="css/fonts.css" rel="stylesheet" type="text/css"/>

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body>
	<div id="page" class="container">
		<div id="header">
			<div id="logo">

				<form action="./LogoutServlet" method="get">
					<input type="submit" value="Logout">
				</form>

			</div>
			<div id="menu">
				<ul>
					<li><c:out value="${userID}" default="User"></c:out></li>

					<li class="current_page_item">Area Personale</li>
					<li><a href="../recipe/GlobalRecipe.jsp">Ricette</a></li>
					<li><a href="../home/WhatIShould.html">What
							Should I Brew today</a></li>
					<li><a href="../recipe/YourRecipe.html">Le
							tue ricette personali</a></li>
					<li><a href="../brew/YourBrew.html">Le
							tue birre personali</a></li>
					<li><a href="">Cerca Ricetta</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="../images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<h2>Brew Day!</h2>
					<span class="byline"> Bentornato nella tua area personale </span>
				</div>
				<div id="featured">
					<span class="byline"><marquee> La tua dispensa</marquee></span>
					<table>
						<tr>
							<td>
								<form action="../pantry/Pantry.jsp">
									<input type="submit" value="AGGIORNA DISPENSA"
										id="submitvertre">
								</form>
							</td>
						</tr>
					</table>
					<br> <br> #tabella in cui si visualizza la dispensa
					dell'utente <br> <br> <br>
				</div>
				<div id="featured">
					<span class="byline"><marquee> Il tuo
							equipaggiamento </marquee></span>
					<table>
						<tr>
							<td>
								<form action="../equipment/Equipment.jsp">
									<input type="submit" value="AGGIORNA EQUIPAGGIAMENTO"
										id="submitverquattro">
								</form>
							</td>
						</tr>
					</table>
					<br> <br> #tabella in cui si visualizza l'equipaggiamento
					dell'utente <br> <br> <br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>