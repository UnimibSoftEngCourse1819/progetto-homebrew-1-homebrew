<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home&nbsp;-&nbsp;Homebrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
</head>

<body>
	<jsp:include page="includer/alert.jsp" />
	<div class="layout_left"></div>
	<div class="layout_right"></div>
	<div class="row page" class="container">
		<div class="header">
			<jsp:include page="includer/menu.jsp" />
		</div>
		<div class="main">
			<div class="main_shadow">
				<div></div>
			</div>

			<div class="image_home">
				<img src="images/top_home.jpg" alt="Image Home" />
			</div>
			<div class="home_desc">
				<h6>BrewDay ti permette di tenere traccia delle tue birre
					artigianali</h6>
				<p>
					Sarai in grado di creare le tue ricette e visualizzare quelle della
					community<br> Potrai gestire le tue miscele e provare quelle
					degli altri membri
				</p>
			</div>
		</div>
	</div>
</body>
</html>


