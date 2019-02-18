<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dispensa&nbsp;-&nbsp;HomeBrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/pantry.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
</head>

<body>
	<jsp:include page="includer/alert.jsp" />
	<div class="header">
		<jsp:include page="includer/menu.jsp" />
	</div>
	<div class="row page pantry">
		<div class="row menu-top-color">
			<h4>DISPENSA</h4>
		</div>
		<div class="row pantry-cont">
			<div class="col-5 my-pantry">
				<h5>La mia dispensa</h5>
				<div class="my-ingredients">
					<c:forEach items="${pantry}" var="item">
						<div class="ingredient-pantry">
							<h6>${item.ingredientName}:</h6>
							<p>${item.availability}${item.measure}</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-7 edit-pantry">
				<h5>Modifica dispensa</h5>
				<p>Inserisci la disponibilit&agrave; totale degli ingredienti da
					inserire in dispensa.</p>
				<form action="./pantry" method="post" name="pantry_form">
					<c:forEach items="${ingredients}" var="item">
						<div class="ingredient">
							<h6>${item.name}</h6>
							<div class="input-ingredient">
								<input type="number" name="${item.ingredientID}" min="0">
								<c:choose>
									<c:when test="${item.measure == '%'}">
										<p>l</p>
									</c:when>
									<c:when test="${item.measure == 'g/l'}">
										<p>g</p>
									</c:when>
								</c:choose>
							</div>
						</div>
					</c:forEach>
					<button name="updatePantry" type="submit" id="updatePantry"
						disabled>AGGIORNA</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>