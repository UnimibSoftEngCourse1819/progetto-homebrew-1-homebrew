
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />

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
				<li><a href="./index.html" accesskey="1" title="">Homepage</a></li>
				<li class="current_page_item"><a href="#" accesskey="2" title=""> Registrati </a></li>
				<li><a href="./recipe/RicetteGlobali.jsp" accesskey="3" title=""> Ricette </a></li>
				<li><a href="#" accesskey="4" title=""></a></li>
				<li><a href="#" accesskey="5" title=""></a></li>
			</ul>
		</div>
	</div>
	<div id="main">
		<div id="banner">
			<img src="webapp/images/homepageBrew.jpg" alt="" class="image-full" />
		</div>
		<div id="welcome">
			<div class="title">
			<h2>brewDay!</h2>
				<span class="byline"> Compila il form di iscrizione per diventare un birraio </span>
			</div>
		</div>
		<div id="featured">
			<ul class="style1">
				<li class="first">
					<form action = "<%=request.getContextPath()%>/UserController" method ="POST">
					<table border="0">
					<tr>
					<td><h3>Nome:</h3></td>
					<td><h3>&nbsp &nbsp <input name="name" type="text" id="testoform" placeholder="Inserisci nome"></h3>
					</tr>
					<tr>
					<td><h3>Cognome:</h3></td>
					<td><h3>&nbsp &nbsp <input name ="surname" type="text" id="testoform" placeholder ="Inserisci cognome"></h3>
					</tr>
					<tr>
					<td><h3>Password:</h3></td>
					<td><h3>&nbsp &nbsp <input name="password" type="password" id="testoform" placeholder ="*********"></h3>
					<td><h3>&nbsp &nbsp &nbsp Conferma password:</h3></td>
					<td><h3>&nbsp &nbsp <input name ="password2" type="password" id="testoform" placeholder ="*********"></h3>
					</tr>
					<tr>
					<td><h3>nascita:</h3></td>
					<td><h3>&nbsp &nbsp <input name ="dateOfBirth" type="text" id="testoform" placeholder="AAAA-MM-GG"></h3>
					</tr>
					<tr>
					<td><h3>mail:</h3></td>
					<td><h3></h3> &nbsp &nbsp <input name ="mail" type="text" id="testoform" placeholder="***@***.**"></h3>
					</tr>
					</table>
					<br>
					<br>
				    <center><font face="Source Sans Pro"> <input name ="action" type ="submit" value="ISCRIVITI" id="submitdue"></center></font>
				</form>
				</li>
		<div id="copyright">
			<span>&copy; Untitled. All rights reserved.</span>
			<span>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</span>
		</div>
	</div>
</div>
</body>
</html>