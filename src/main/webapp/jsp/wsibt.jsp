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

					<form>
						<input type="submit" value="LOGOUT" id="submit">
					</form>
			</div>
			<div id="menu">
				<ul>
					<li><a href="../home/HomepageLog.jsp" accesskey="1" title="">Area
							Personale</a></li>
					<li><a href="../recipe/GlobalRecipe.jsp" accesskey="3"
						title="">Ricette</a></li>
					<li class="current_page_item"><a href="#" accesskey="4"
						title="">What Should I Brew today</a></li>
					<li><a href="../recipe/YourRecipe.html" accesskey="5" title="">Le
							tue ricette personali</a></li>
					<li><a href="../brew/YourBrew.html" accesskey="6" title="">Le
							tue birre personali</a></li>
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
					<span class="byline"><marquee>Ecco la ricetta a te
							consigliata!</marquee></span>
				</div>
			</div>
			<div id="featured">
				<ul class="style1">
					<li class="first">

						<center>#ricetta consigliata</center> <br> <br> <br>
						<center>
							<form>
								<h3>
									<input type="submit" id="submitverquattro" value="CREA RICETTA">
								</h3>
							</form>
						</center>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>