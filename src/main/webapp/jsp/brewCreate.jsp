<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Nuova&nbsp;Miscela&nbsp;-&nbsp;HomeBrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/brews.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/main.js"></script>
<script src="js/form_control.js"></script>

</head>

<body>
	<jsp:include page="includer/alert.jsp" />
	<div class="header">
		<jsp:include page="includer/menu.jsp" />
	</div>
	<div class="row page new-brew">
		<div class="row menu-top-color">
			<a class="back_arr" href="./recipe?n=${recipe.recipeID}">&larr;</a>
			<h4>Nuova miscela</h4>
		</div>

		<form action="./new_brew" method="post" id="create_brew_form"
			class="recipe_edit_form">
			<div class="row">
				<div class="col-9">
					<h6>Titolo</h6>
					<input type="text" name="name" placeholder="Nome">
				</div>
			</div>
			<div>
				<h6>Descrizione</h6>
				<textarea name="description" placeholder="Descrizione"></textarea>
			</div>
			<div>
				<h6>Note di degustazione</h6>
				<textarea name="tasteNote" placeholder="Note di degustazione"></textarea>
			</div>
			<div class="">
				<h6>Quantita' prodotta:</h6>
				<input type="number" name="quantity" min="0">
			</div>
			<h6>BatchSize: ${batchSize}l</h6>

			<div class="row">
				<div class="col-6">
					<h6>Ingredienti richiesti</h6>
					<table>
						<tbody>
							<c:forEach items="${ingredientsRecipe}" var="item">
								<tr>
									<td class="leftIngr">${item.ingredientName}</td>
									<c:choose>
										<c:when test="${item.measure == '%'}">
											<c:set var="measure" value="l" />
										</c:when>
										<c:when test="${item.measure == 'g/l'}">
											<c:set var="measure" value="g" />
										</c:when>
									</c:choose>
									<td class="rightIngr">${(item.quantity)*batchSize}&nbsp;${measure}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-6 create_recipe_steps">
					<h6>Ingredienti in dispensa</h6>
					<table>
						<tbody>
							<c:forEach items="${pantries}" var="item">
								<tr>
									<td class="leftIngr">${item.ingredientName}</td>
									<td class="rightIngr">${item.availability}&nbsp;${item.measure}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<c:choose>
				<c:when test="${brewable == true}">
					<button name="addRecipe" type="submit">INVIA</button>
				</c:when>
				<c:otherwise>
					<span>INGREDIENTI INSUFFICIENTI</span>
				</c:otherwise>
			</c:choose>

		</form>
	</div>


</body>
</html>


