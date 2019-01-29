<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Accesso - Homebrew</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div id="page" class="container">
		<div id="header">
			<div id="logo">
				<c:if test="${logged == null}">
					<form method="post" action="./home">
						<table>
							<tr>
								<td>User:</td>
								<td><input type="text" name="user" /></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input type="password" name="pass"></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;"><input
									type="submit" value="Accedi" id="submit"></td>
							</tr>
							<c:if test="${errorLogin != null}">
								<tr>
									<td><span id="errorLogin"> <c:out
												value="${errorLogin}" default=""></c:out>
									</span></td>
								</tr>
							</c:if>

						</table>
					</form>

				</c:if>
				<c:if test="${logged != null}">
					<form action="./logout" method="get">
						<input type="submit" value="Logout">
					</form>
				</c:if>
			</div>
			<div id="menu">
				<c:if test="${logged == null}">
					<ul>
						<li class="current_page_item">Homepage</li>
						<li><a href="./register">Registrati</a></li>
					</ul>
				</c:if>
				<c:if test="${logged != null}">
					<ul>
						<li><c:out value="${user.name}" default="nullo"></c:out></li>
						<li class="current_page_item">Area Personale</li>
						<li><a href="./ricette">Ricette</a></li>
						<li><a href="../home/WhatIShould.html">What Should I Brew
								today</a></li>
						<li><a href="../recipe/YourRecipe.html">Le tue ricette
								personali</a></li>
						<li><a href="../brew/YourBrew.html">Le tue birre
								personali</a></li>
						<li><a href="">Cerca Ricetta</a></li>
					</ul>
				</c:if>

			</div>
		</div>
		<div id="main">
			<div id="banner">
				<img src="images/homepageBrew.jpg" alt="" class="image-full" />
			</div>
			<div id="welcome">
				<div class="title">
					<h2>brewDay!</h2>
					<span class="byline"> Il tuo ricettario di birra personale </span>
				</div>
			</div>
			<div id="featured">
				<c:forEach items="${recipes}" var="item">
					<c:out value="${item.name}" default=""></c:out>
					<br>
					<br>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>


