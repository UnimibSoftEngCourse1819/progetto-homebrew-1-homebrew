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
							<c:set var="date_recipe" value="${fn:split(recipe.creation,'-')}" />
							<p>${date_recipe[2]}/${date_recipe[1]}/${date_recipe[0]}</p>
							<p>${userRecipe.name}&nbsp;${userRecipe.surname}</p>
							<c:if test="${logged != null}">
								<c:if test="${editable != null}">
									<form class="recipe_action rec_edit" action="./recipe"
										method="post">
										<input type="hidden" name="recipeID"
											value="${recipe.recipeID}">
										<button name="editRecipe" type="submit">
											<img alt="editable" src="images/logos/edit.svg">
										</button>
									</form>
								</c:if>
								<a class="recipe_action rec_brew" href="./new_brew">
									<button name="newBrew" type="submit">
										<img alt="new_brew" src="images/logos/new_brew.svg">
									</button>
								</a>
							</c:if>
						</div>
					</div>
				</div>
				<div class="recipe_cont">
					<div class="recipe_menu">
						<button class="active" name="recipe_desc" type="button">RICETTA</button>
						<c:if test="${logged != null}">
							<button name="recipe_brews" type="button">MISCELE</button>
						</c:if>
					</div>
					<div class="recipe_sect" id="recipe_desc">
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
					<c:if test="${logged != null}">
						<div class="row recipe_sect" id="recipe_brews"
							style="display: none;">
							<c:forEach items="${brews}" var="item">
								<div class="col-4">
									<p>${item.name}</p>
									<p>${item.userName}&nbsp;${item.userSurname}</p>
									<c:set var="date_brew" value="${fn:split(item.brewDate,'-')}" />
									<p>${date_brew[2]}/${date_brew[1]}/${date_brew[0]}</p>
									<p>${item.quantity}l</p>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


