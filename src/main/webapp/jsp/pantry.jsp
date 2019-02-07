<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${recipe.name}&nbsp;-&nbsp;HomeBrew</title>
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
			<h2 class="title">BrewDay</h2>
			<jsp:include page="includer/menu.jsp" />
		</div>
		<div class="row main">
			
			<form action="./updatePantry" method="post" name="pantry_form">
					<table>
						<tr>
							<td><h3>MALTO:</h3></td>
							<td><h3>
									<input type="number" name="malt" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>ZUCCHERO:</h3></td>
							<td><h3>
									<input type="number" name="sugar" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>LIEVITO:</h3></td>
							<td><h3>
									<input type="number" name="yeast" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>ADDITIVI:</h3></td>
							<td><h3>
									<input type="number" name="additives" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>LUPPOLO:</h3></td>
							<td><h3>
									<input type="number" name="hop" id="testoformdue"
										placeholder=" grammi" required>
								</h3>
						</tr>
						<tr>
							<td><h3>ACQUA:</h3></td>
							<td><h3>
									<input type="number" name="water" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
					</table>
					<input type="submit" name="action" value="AGGIORNA" id="submitdue"
						onClick="return(controlForm ());">
				</form>
			
			
		</div>
	</div>

</body>
</html>

