<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="page" class="container">
		<div id="header">
			<div id="logo"></div>
			<div id="menu">
				<ul>
					<li class="current_page_item">Area Personale</li>
					<li><a href="../recipe/GlobalRecipe.jsp">Ricette </a></li>
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
					<span class="byline">Mantieni sempre aggiornata la tua
						dispensa</span>
				</div>
			</div>
			<div id="featured">

				<form method="post" name="pantry_form">
					<table>
						<tr>
							<td><h3>MALTO:</h3></td>
							<td><h3>
									<input type="number" name="malt" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>ZUCCHERO:</h3></td>
							<td><h3>
									<input type="number" name="sugar" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>LIEVITO:</h3></td>
							<td><h3>
									<input type="number" name="yeast" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>ADDITIVI:</h3></td>
							<td><h3>
									<input type="number" name="additives" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>LUPPOLO:</h3></td>
							<td><h3>
									<input type="number" name="hop" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>ACQUA:</h3></td>
							<td><h3>
									<input type="number" name="water" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
					</table>
					<input type="submit" name="action" value="AGGIORNA" id="submitdue"
						onClick="return(controlForm ());">
				</form>
			</div>

		</div>
	</div>
</body>
</html>