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
<script src="js/form_control.js"></script>

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
			<div class="main_shadow">
				<div></div>
			</div>

			<div class="content_main recipe_cont">
				<a class="back_recipes" href="./recipes">ANNULLA</a>

				<form action="./new_recipe" method="post" id="create_recipe_form"
					class="recipe_edit_form">
					<input type="hidden" name="recipeID" value="${recipe.recipeID}">
					<div class="row">
						<div class="col-9">
							<h6>Titolo</h6>
							<input type="text" name="name" placeholder="Nome">
						</div>
						<div class="col-3">
							<h6>Visibilita'</h6>
							<select name="visibility">
								<option value="public">Pubblica</option>
								<option value="private">Privata</option>
							</select>
						</div>
					</div>
					<div>
						<h6>Descrizione</h6>
						<textarea name="description" placeholder="Descrizione"></textarea>
					</div>
					<div class="row">
						<div class="col-4">
							<h6>Ingredienti</h6>
							<table>
								<tbody>
									<c:forEach items="${ingredients}" var="item">
										<tr>
											<td class="leftIngr">${item.name}</td>
											<td class="rightIngr"><input type="number"
												name="valueIngr-${item.ingredientID}" min="0"
												placeholder="Valore" value="0"> <input type="text"
												name="measureIngr-${item.ingredientID}" placeholder="Misura"
												value="${item.measure}"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="col-8 create_recipe_steps">
							<h6>Passaggi</h6>
							<button name="removeStep" type="button">&times;</button>
							<button name="addStep" type="button">+</button>
							<table>
								<tbody>
									<tr>
										<td class="leftStep">1</td>
										<td class="rightStep"><textarea name="step-1"
												placeholder="Inserisci il procedimento"></textarea></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<button name="addRecipe" type="submit">INVIA</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>


