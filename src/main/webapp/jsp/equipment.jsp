<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/form_control_equipment.js"></script>
</head>

<body>
	<div id="page" class="container">
		<div id="header">
			<div id="logo">

			</div>
			<div id="menu">
				<ul>
					<li class="current_page_item">Area Personale</li>
					<li><a href="../recipe/GlobalRecipe.jsp"> Ricette </a></li>
					<li><a href="../home/WhatIShould.html">What Should I Brew
							today</a></li>
					<li><a href="../recipe/YourRecipe.html">Le tue ricette
							personali</a></li>
					<li><a href="../brew/YourBrew.html">Le tue birre personali</a></li>
					<li><a href="">Cerca Ricetta</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<span class="byline"><marquee>Mantieni sempre
							aggiornato il tuo equipaggiamento</marquee></span>
				</div>
			</div>
			<div id="featured">

				<form method="post" name="equipment_form">
					<table>
						<tr>
							<td><h3>Bilancia:</h3></td>
							<td><h3>
									<input type="number" name="weightScale" id="testoformdue"
										placeholder=" pesatura" required>
								</h3>
						</tr>
						<tr>
							<td><h3>PENTOLONE:</h3></td>
							<td><h3>
									<input type="number" name="cauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>FERMENTATORE:</h3></td>
							<td><h3>
									<input type="number" name="fermenter" id="testoformdue"
										placeholder=" capacitÃ " required>
								</h3>
						</tr>
						<tr>
							<td><h3>MESTOLI:</h3></td>
							<td><h3>
									<input type="number" name="ladles" id="testoformdue"
										placeholder=" quantitÃ " required>
								</h3>
						</tr>
						<tr>
							<td><h3>TERMOMETRI:</h3></td>
							<td><h3>
									<input type="number" name="thermometers" id="testoformdue"
										placeholder=" quantitÃ " required>
								</h3>
						</tr>
						<tr>
							<td><h3>Tubo:</h3></td>
							<td><h3>
									<input type="number" name="tube" id="testoformdue"
										placeholder=" cm" required>
								</h3>
						</tr>
					</table>
					<input type="submit" value="AGGIORNA" name="action" id="submitdue"
						onClick="return(controlForm());">

				</form>
			</div>
			<c:if test="${success}">
				<div class="success">
					<p>L'aggiornamento è avvenuto con successo</p>
				</div>
			</c:if>
			<c:if test="${failure}">
				<div class="failure">
					<p>L'aggiornamento non è avvenuto con successo</p>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>