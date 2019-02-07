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
			<jsp:include page="includer/menu.jsp" />
		</div>
		<div class="row main">
			<div class="main_shadow">
				<div></div>
			</div>
			<div class="content_main">
				<c:if test="${section == 'general'}">
					<a class="back_recipes" href="./recipes">&larr;RICETTE</a>
				</c:if>
				<c:if test="${section == 'personal'}">
					<a class="back_recipes" href="./my_recipes">&larr;RICETTE</a>
				</c:if>


				<div class="row recipe_img"
					style="background-image: url(${recipe.imagePath})">
					<div class="recipe_titCont">
						<div class="recipe_title">
							<h3>${recipe.name}</h3>
							<c:set var="splitdate" value="${fn:split(recipe.creation,'-')}" />
							<p>${splitdate[2]}/${splitdate[1]}/${splitdate[0]}</p>
							<p>${userRecipe.name}${userRecipe.surname}</p>

							<c:if test="${editable != null}">
								<form class="recipe_to_edit" action="./recipe" method="post">
									<input type="hidden" name="recipeID" value="${recipe.recipeID}">
									<button name="editRecipe" type="submit">
										<img alt="editable" src="images/logos/edit.svg">
									</button>
								</form>
							</c:if>
						</div>
					</div>
				</div>
				<div class="recipe_cont">
					<div class="recipe_menu">
						<a href="./">CREA</a> <a href="">MISCELE</a>
					</div>
					<p class="recipe_description">${recipe.description}</p>
					<h5 class="recipe_ingredient">Ingredienti</h5>
					<table>
						<tbody>
							<c:forEach items="${ingredientsRecipe}" var="item">
								<tr>
									<td class="leftIngr">${item.ingredientName}</td>
									<td class="rightIngr">${item.quantity}${item.measure}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="recipe_steps">
						<h5>Procedimento</h5>
						<ul>
							<c:forEach items="${recipe.steps}" var="item">
								<li>${item.key}.&nbsp;${item.value}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


