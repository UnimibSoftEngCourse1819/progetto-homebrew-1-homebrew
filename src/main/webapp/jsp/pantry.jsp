<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="../css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/fonts.css" rel="stylesheet" type="text/css" media="all" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body>
<div id="page" class="container">
	<div id="header">
	<div id="logo">
			<br>
			<br>
			<br>
			<br>
			<br>
		</div>
		<div id="menu">
			<ul>
				<li class="current_page_item"><a href="#" accesskey="1" title="">Area Personale</a></li>
				<li><a href="../recipe/GlobalRecipe.jsp" accesskey="3" title=""> Ricette </a></li>
				<li><a href="../home/WhatIShould.html" accesskey="4" title="">What Should I Brew today</a></li>
				<li><a href="../recipe/YourRecipe.html" accesskey="5" title="">Le tue ricette personali</a></li>
				<li><a href="../brew/YourBrew.html" accesskey="6" title="">Le tue birre personali</a></li>
				<li><a href="" accesskey="7" title="">Cerca Ricetta</a></li>
			</ul>
		</div>
	</div>
	<div id="main">
		<div id="banner">
			<img src="../images/homepageBrew.jpg" alt="" class="image-full" />
		</div>
		<div id="welcome">
			<div class="title">
				<span class="byline"><marquee>Mantieni sempre aggiornata la tua dispensa</marquee></span>
			</div>
		</div>
		<div id="featured">
			<ul class="style1">
				<li class="first">
					<center><form><table border="0">
					<tr>
					<td><h3>MALTO:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" grammi"></h3>
					</tr>
					<tr>
					<td><h3>ZUCCHERO:</h3></td>
					<td><h3> &nbsp &nbsp <input type="text" id="testoformdue" placeholder=" grammi"></h3>
					</tr>
					<tr>
					<td><h3>LIEVITO:</h3></td>
					<td><h3>&nbsp &nbsp <input type="password" id="testoformdue" placeholder=" grammi"></h3>
					</tr>
					<tr>
					<td><h3>ADDITIVI:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" grammi"></h3>
					</tr>
					<tr>
					<td><h3>LUPPOLO:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" grammi"></h3>
					</tr>
					</table> </center>
					<br>
					<br>
					<h3>
				    <center><font face="Source Sans Pro"> <input type ="submit" value="AGGIORNA" id="submitdue"></center></font>
				</form></h3>
				</li>
	</div>
</div>
</body>
</html>