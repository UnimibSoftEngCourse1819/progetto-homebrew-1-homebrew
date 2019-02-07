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
			
			<form action="./updateEquipment" method="post" name="equipment_form">
					<table>
						<tr>
							<td><h3>PENTOLA PER BOLLITURA:</h3></td>
							<td><h3>
									<input type="number" name="boilingCauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>PENTOLA PER MASHING:</h3></td>
							<td><h3>
									<input type="number" name="mashingCauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>PENTOLA PER LIQUIDI CALDI:</h3></td>
							<td><h3>
									<input type="number" name="hotLiquidsCauldron" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>BOLLITORE:</h3></td>
							<td><h3>
									<input type="number" name="kettle" id="testoformdue"
										placeholder=" litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>FERMENTATORE:</h3></td>
							<td><h3>
									<input type="number" name="fermenter" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>FILTRO:</h3></td>
							<td><h3>
									<input type="number" name="filter" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
						<tr>
							<td><h3>BILANCIA:</h3></td>
							<td><h3>
									<input type="number" name="weightScale" id="testoformdue"
										placeholder="kg" required>
								</h3>
						</tr>
						<tr>
							<td><h3>TERMOMETRI:</h3></td>
							<td><h3>
									<input type="number" name="thermometers" id="testoformdue"
										placeholder="quantità" required>
								</h3>
						</tr>
						<tr>
							<td><h3>DENSIMETRO:</h3></td>
							<td><h3>
									<input type="number" name="densimetro" id="testoformdue"
										placeholder="quantità" required>
								</h3>
						</tr>
						<tr>
							<td><h3>MESTOLI:</h3></td>
							<td><h3>
									<input type="number" name="ladles" id="testoformdue"
										placeholder=" quantità" required>
								</h3>
						</tr>

						<tr>
							<td><h3>TUBO:</h3></td>
							<td><h3>
									<input type="number" name="tube" id="testoformdue"
										placeholder="litri" required>
								</h3>
						</tr>
					</table>
					<input type="submit" value="AGGIORNA" name="action" id="submitdue"
						onClick="return(controlForm());">

				</form>
			
			
		</div>
	</div>

</body>
</html>

