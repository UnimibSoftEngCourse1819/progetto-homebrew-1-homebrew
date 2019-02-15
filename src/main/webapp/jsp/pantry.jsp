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
<link href="css/recipes.css" rel="stylesheet" type="text/css" />
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
	<div class="row page account">
		<div class="row menu-top-color">
			<h4>DISPENSA</h4>
		</div>
			<div class="content_main">
				<c:forEach items="${pantry}" var="item">
					<div class="recipes_element">
						<div class="row recipes_inner">
							<div class="col-9">
								<h3>${item.ingredientName}${item.availability}</h3>
							</div>
						</div>
					</div>
				</c:forEach>
				<a href="./edit_pantry">AGGIORNA DISPENSA</a>
			</div>

			<form action="./edit_pantry" method="post" name="pantry_form">
				<table>
					<c:forEach items="${ingredients}" var="item">
						<tr>
							<td><h3>${item.name}:</h3></td>
							<td><h3>
									<input type="number" name="${item.ingredientID}"
										id="testoformdue" placeholder="${item.measure}" min="0">
								</h3></td>
						</tr>
					</c:forEach>
				</table>
				<button name="updatePantry" type="submit" id="updatePantry" disabled>AGGIORNA</button>
			</form>
		</div>
	</div>
</body>
</html>