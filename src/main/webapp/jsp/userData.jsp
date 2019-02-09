<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dati anagrafici&nbsp;-&nbsp;HomeBrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/recipes.css" rel="stylesheet" type="text/css" />
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
		<div class="row main">
			<div class="main_shadow">
				<div></div>
			</div>
			<div class="content_main">
				<c:set var = "item" value = "${user}"/>
					<div class="recipes_element">
						<div class="row recipes_inner">
							<div class="col-9">
								<table>
									<tr>
										<td><h3>NOME:</h3></td>
										<td><h3>${item.name}</h3></td>
									</tr>
									<tr>
										<td><h3>COGNOME:</h3></td>
										<td><h3>${item.surname}</h3></td>
									</tr>
									<tr>
										<td><h3>DATA DI NASCITA:</h3></td>
										<td><h3>${item.dateOfBirth}</h3></td>
									</tr>
									<tr>
										<td><h3>MAIL:</h3></td>
										<td><h3>${item.email}</h3></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				<a href="./edit_account">AGGIORNA DATI ANAGRAFICI</a>
			</div>
		</div>
	</div>
</body>
</html>