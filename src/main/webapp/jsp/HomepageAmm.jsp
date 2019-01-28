<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />
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
					<li class="current_page_item"><a href="#" accesskey="1"
						title="">Area Personale</a></li>
					<li><a href="ListaUtentiAmm.html" accesskey="3" title="">Lista
							Utenti</a></li>
					<li><a href="RicetteAmm.html" accesskey="4" title="">Ricette</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<h2>Brew Day!</h2>
					<span class="byline"> Bentornato nella tua area da
						amministratore </span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
