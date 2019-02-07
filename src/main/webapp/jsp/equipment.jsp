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

				<form action="./updateEquipment" method="post" name="equipment_form">
					<table>
						<tr>
							<td><h3>PENTOLA PER BOLLITURA:</h3></td>
							<td><h3>
									<input type="number" name="boilingCauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>PENTOLA PER MASHING:</h3></td>
							<td><h3>
									<input type="number" name="mashingCauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>PENTOLA PER LIQUIDI CALDI:</h3></td>
							<td><h3>
									<input type="number" name="hotLiquidsCauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>BOLLITORE:</h3></td>
							<td><h3>
									<input type="number" name="kettle" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>FERMENTATORE:</h3></td>
							<td><h3>
									<input type="number" name="fermenter" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>FILTRO:</h3></td>
							<td><h3>
									<input type="number" name="filter" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>BILANCIA:</h3></td>
							<td><h3>
									<input type="number" name="weightScale" id="testoformdue"
										placeholder="kg" required>
								</h3>
						</tr>
						<tr>
							<td><h3>TERMOMETRI:</h3></td>
							<td><h3>
									<input type="number" name="thermometers" id="testoformdue"
										placeholder="quantità" required>
								</h3>
						</tr>
						<tr>
							<td><h3>DENSIMETRO:</h3></td>
							<td><h3>
									<input type="number" name="densimetro" id="testoformdue"
										placeholder="quantità" required>
								</h3>
						</tr>
						<tr>
							<td><h3>MESTOLI:</h3></td>
							<td><h3>
									<input type="number" name="ladles" id="testoformdue"
										placeholder=" quantità" required>
								</h3>
						</tr>

						<tr>
							<td><h3>TUBO:</h3></td>
							<td><h3>
									<input type="number" name="tube" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
					</table>
					<input type="submit" value="AGGIORNA" name="action" id="submitdue"
						onClick="return(controlForm());">

				</form>
			</div>
		</div>
	</div>
</body>
</html>