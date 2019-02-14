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
<script src="js/recipes.js"></script>

</head>

<body>
	<jsp:include page="includer/alert.jsp" />
	<div class="header">
		<jsp:include page="includer/menu.jsp" />
	</div>
	<div class="row page recipe ">
		<div class="row edit-recipe-top">
			<a class="back_recipes" href="./recipe?n=${recipe.recipeID}">&larr;&nbsp;Ricetta</a>
			<h4>Modifica la ricetta</h4>
			<form action="./remove_recipe" method="post">
				<input type="hidden" name="recipeID" value="${recipe.recipeID}">
				<button name="deleteRecipe" type="submit">Elimina</button>
			</form>
		</div>
		<div class="row recipe-edit-form">
			<form action="./edit_recipe" method="post">
				<input type="hidden" name="recipeID" value="${recipe.recipeID}">
				<div class="row">
					<div class="col-10">
						<h6>Titolo</h6>
						<input type="text" name="name" value="${recipe.name}">
					</div>
					<div class="col-2 recipe-edit-visibility">
						<h6>Visibilit&agrave;</h6>
						<select name="visibility">
							<option value="public">Pubblica</option>
							<option value="private">Privata</option>
						</select>
					</div>
				</div>
				<div class="row">
					<h6>Descrizione</h6>
					<textarea name="description">${recipe.description}</textarea>
				</div>
				<div class="row">
					<div class="col-5">
						<h6>Ingredienti</h6>
						<table>
							<tbody>
								<c:forEach items="${ingredients}" var="item">
									<tr>
										<td class="leftIngr">${item.name}</td>
										<td class="rightIngr"><c:set var="valueIngredient"
												value="0"></c:set> <c:forEach items="${ingredientsRecipe}"
												var="itemRecipe">
												<c:if test="${itemRecipe.ingredientID == item.ingredientID}">
													<c:set var="valueIngredient" value="${itemRecipe.quantity}"></c:set>
													<c:set var="measureIngredient"
														value="${itemRecipe.measure}"></c:set>
												</c:if>
											</c:forEach> <input type="number" name="valueIngr-${item.ingredientID}"
											value="${valueIngredient}" min="0"> <input
											type="text" name="measureIngr-${item.ingredientID}"
											value="${measureIngredient}"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-7 edit-recipe-steps">
						<h6>Passaggi</h6>
						<div class="buttons-adder-steps">
							<button name="removeStep" type="button">-</button>
							<button name="addStep" type="button">+</button>
						</div>

						<table>
							<tbody>
								<c:forEach items="${recipe.steps}" var="item">
									<tr>
										<td class="leftStep">${item.key}</td>
										<td class="rightStep"><textarea name="step-${item.key}">${item.value}</textarea>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<button name="editRecipe" type="submit">MODIFICA</button>
			</form>
		</div>

	</div>


</body>
</html>


