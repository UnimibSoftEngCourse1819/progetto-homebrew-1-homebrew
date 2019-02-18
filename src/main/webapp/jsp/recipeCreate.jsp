<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Crea Ricetta&nbsp;-&nbsp;HomeBrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/recipes.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
<script src="js/recipes.js"></script>
<!-- <script src="js/form_control.js"></script> -->

</head>

<body>

	<jsp:include page="includer/alert.jsp" />
	<div class="header">
		<jsp:include page="includer/menu.jsp" />
	</div>
	<div class="row page create-recipe">
		<div class="row menu-top-color">
			<h4>NUOVA RICETTA</h4>
		</div>
		<div class="row create-recipe-form">
			<form action="./new_recipe" method="post">
				<div class="row">
					<div class="col-10">
						<h6>Titolo</h6>
						<input type="text" name="name"
							placeholder="Il nome della tua ricetta">
					</div>
					<div class="col-2 create-recipe-visibility">
						<h6>Visibilit&agrave;</h6>
						<select name="visibility">
							<option value="public">Pubblica</option>
							<option value="private">Privata</option>
						</select>
					</div>
				</div>
				<div class="row">
					<h6>Descrizione</h6>
					<textarea name="description" placeholder="Descrivi la tua ricetta"></textarea>
				</div>
				<div class="row">
					<div class="col-5 create-recipe-ingred">
						<h6>Ingredienti</h6>
						<table>
							<tbody>
								<c:forEach items="${ingredients}" var="item">
									<tr>
										<td class="leftIngr">${item.name}</td>
										<td class="rightIngr"><c:set var="valueIngredient"
												value="0"></c:set><input type="number"
											name="valueIngr-${item.ingredientID}" min="0">
											<p>${item.measure}</p></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-7 create-recipe-steps">
						<h6>Passaggi</h6>
						<div class="buttons-adder-steps">
							<button name="removeStep" type="button">-</button>
							<button name="addStep" type="button">+</button>
						</div>

						<table>
							<tbody>
								<tr>
									<td class="leftStep">1</td>
									<td class="rightStep"><textarea name="step-1"></textarea></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<button name="createRecipe" type="submit">CREA</button>
			</form>
		</div>

	</div>


</body>
</html>


