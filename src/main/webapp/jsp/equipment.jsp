<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Equipaggiamento - Homebrew</title>
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
				<form action="./LogoutServlet" method="get">
					<input type="submit" value="Logout">
				</form>
			</div>
			<div id="menu">
				<ul>
					<li class="current_page_item">Area Personale</li>
					<li><a href="./recipe"> Ricette </a></li>
					<li><a href="./wsibt">What Should I Brew today</a></li>
					<li><a href="./recipe">Le tue ricette personali</a></li>
					<li><a href="./brew">Le tue birre personali</a></li>
					<li><a href="./brew/create">Cerca Ricetta</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<span class="byline">Mantieni sempre
							aggiornato il tuo equipaggiamento</span>
				</div>
			</div>
			<div id="featured">
				<form>
					<table>
						<tr>
							<td><h3>BILANCIA:</h3></td>
							<td><h3>
									<input type="number" id="">
								</h3>
						</tr>
						<tr>
							<td><h3>PENTOLONE:</h3></td>
							<td><h3>
									<input type="number" id="">
								</h3>
						</tr>
						<tr>
							<td><h3>FERMENTATORE:</h3></td>
							<td><h3>
									<input type="number" id="">
								</h3>
						</tr>
						<tr>
							<td><h3>MESTOLI:</h3></td>
							<td><h3>
									<input type="number" id="">
								</h3>
						</tr>
						<tr>
							<td><h3>TERMOMETRI:</h3></td>
							<td><h3>
									<input type="number" id="">
								</h3>
						</tr>
						<tr>
							<td><h3>TUBO:</h3></td>
							<td><h3>
									<input type="number" id="">
								</h3>
						</tr>
					</table>

					<input type="submit" value="AGGIORNA" id="submitdue">
				</form>
			</div>
		</div>
	</div>
</body>
</html>