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
			<div id="logo">
				<form>
					<input type="submit" value="LOGOUT" id="submit">
				</form>
			</div>
			<div id="menu">
				<ul>
					<li><a href="../home/HomepageLog.jsp">Area Personale</a></li>
					<li><a href="../recipe/GlobalRecipe.jsp">Ricette</a></li>
					<li><a href="../home/WhatIShould.html">What Should I Brew
							today</a></li>
					<li><a href="../recipe/YourRecipe.html">Le tue ricette
							personali</a></li>
					<li class="current_page_item">Le tue birre personali</li>
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
					<span class="byline"> Tieni traccia delle birre che hai gia 
						prodotto </span>
				</div>
				<div id="featured">
					<span class="byline">Le tue birre</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>