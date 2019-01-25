<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
				<li class="current_page_item"><a href="#" accesskey="1" title="">Area Personale</a></li>
				<li><a href="<%=request.getContextPath()%>/RicetteGlobali.jsp" accesskey="3" title=""> Ricette </a></li>
				<li><a href="WhatIShould.html" accesskey="4" title="">What Should I Brew today</a></li>
				<li><a href="LeTueRicette.html" accesskey="5" title="">Le tue ricette personali</a></li>
				<li><a href="LeTueBirre.html" accesskey="6" title="">Le tue birre personali</a></li>
				<li><a href="" accesskey="7" title="">Cerca Ricetta</a></li>
			</ul>
		</div>
	</div>
	<div id="main">
		<div id="banner">
			<img src="homepageBrew.jpg" alt="" class="image-full" />
		</div>
		<div id="welcome">
			<div class="title">
				<span class="byline"><marquee>Mantieni sempre aggiornato il tuo equipaggiamento</marquee></span>
			</div>
		</div>
		<div id="featured">
			<ul class="style1">
				<li class="first">
					<center> <form></form><table border="0">
					<tr>
					<td><h3>Bilancia:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" pesatura"></h3>
					</tr>
					<tr>
					<td><h3>PENTOLONE:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" litri"></h3>
					</tr>
					<tr>
					<td><h3>FERMENTATORE:</h3></td>
					<td><h3>&nbsp &nbsp <input type="password" id="testoformdue" placeholder=" capacità"></h3>
					</tr>
					<tr>
					<td><h3>MESTOLI:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" quantità"></h3>
					</tr>
					<tr>
					<td><h3>TERMOMETRI:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" quantità"></h3>
					</tr>
					<tr>
					<td><h3>Tubo:</h3></td>
					<td><h3>&nbsp &nbsp <input type="text" id="testoformdue" placeholder=" cm"></h3>
					</tr>
					</table> </center>
					<br>
					<br>
				    <center><font face="Source Sans Pro"> <input type ="submit" value="AGGIORNA" id="submitdue"></center></font>
				</form>
				</li>
	</div>
</div>
</body>
</html>