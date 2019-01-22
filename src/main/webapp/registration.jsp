<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

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
				<li><a href="index.jsp" accesskey="1" title="">Homepage</a></li>
				<li class="current_page_item"><a href="#" accesskey="2" title=""> Registrati </a></li>
				<li><a href="#" accesskey="3" title=""> Ricette </a></li>
				<li><a href="#" accesskey="4" title=""></a></li>
				<li><a href="#" accesskey="5" title=""></a></li>
			</ul>
		</div>
	</div>
	<div id="main">
		<div id="banner">
			<img src="images/birra1.jpg" alt="" class="image-full" />
		</div>
		<div id="welcome">
			<div class="title">
				<span class="byline"> Compila il form di iscrizione per diventare un birraio </span>
			</div>
		</div>
		<div id="featured">
			<ul class="style1">
				<li class="first">
					<table border="0">
					<tr>
					<td><h3>Nome:</h3></td>
					<td><h3><form action = "<%=request.getContextPath()%>/UserController" method="POST"> &nbsp &nbsp <input name="nome" type="text" id="testoform" placeholder="Inserisci nome"></form></h3>
					</tr>
					<tr>
					<td><h3>Cognome:</h3></td>
					<td><h3><form action = "<%=request.getContextPath()%>/UserController" method="POST"> &nbsp &nbsp <input name ="cognome" type="text" id="testoform" placeholder ="Inserisci cognome"></form></h3>
					</tr>
					<tr>
					<td><h3>Password:</h3></td>
					<td><h3><form action = "<%=request.getContextPath()%>/UserController" method="POST"> &nbsp &nbsp <input name="password" type="password" id="testoform" placeholder ="*********"></form></h3>
					<td><h3>&nbsp &nbsp &nbsp Conferma password:</h3></td>
					<td><h3><form action = "<%=request.getContextPath()%>/UserController" method="POST"> &nbsp &nbsp <input name ="confermapassword" type="password" id="testoform" placeholder ="*********"></form></h3>
					</tr>
					<tr>
					<td><h3>nascita:</h3></td>
					<td><h3><form action = "<%=request.getContextPath()%>/UserController" method="POST"> &nbsp &nbsp <input name ="datanascita" type="text" id="testoform" placeholder="AAAA-MM-GG"></form></h3>
					</tr>
					<tr>
					<td><h3>mail:</h3></td>
					<td><h3><form action = "<%=request.getContextPath()%>/UserController" method="POST"> &nbsp &nbsp <input name ="mail" type="text" id="testoform" placeholder="***@***.**"></form></h3>
					</tr>
					</table>
					<br>
					<br>
				    <center><font face="Source Sans Pro"> <input name ="action" type ="submit" value="ISCRIVITI" id="submitdue"></center></font>
				</li>
		<div id="copyright">
			<span>&copy; Untitled. All rights reserved.</span>
			<span>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</span>
		</div>
	</div>
</div>
</body>
</html>