<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>What&nbsp;should&nbsp;I&nbsp;brew&nbsp;today&nbsp;-&nbsp;HomeBrew</title>
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
	<div class="row page recipe">

		<div class="row recipe_img"
			style="background-image: url(${recipe.imagePath})">
			<div class="recipe_titCont">
				<div class="recipe_title">
					<h3>${recipe.name}</h3>
					<c:set var="date_recipe" value="${fn:split(recipe.creation,'-')}" />
					<p>${date_recipe[2]}/${date_recipe[1]}/${date_recipe[0]}</p>
					<p>${userRecipe.name}&nbsp;${userRecipe.surname}</p>
					<c:if test="${logged != null}">
						<a class="recipe_action rec_brew" href="./new_brew">
							<button name="newBrew" type="submit">
								<img title="Crea Miscela" alt="new_brew"
									src="images/logos/new_brew.svg">
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
								<td class="rightIngr">${item.quantity}&nbsp;${item.measure}</td>
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
					<table class="list_brews">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Brewer</th>
								<th>Data</th>
								<th>Quantita'</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${brews}" var="item">
								<c:set var="date_brew" value="${fn:split(item.brewDate,'-')}" />
								<tr class="button_brew" data-toggle="modal"
									data-target="#brew_modal">
									<td id="brew_name">${item.name}</td>
									<td id="brew_user">${item.userName}&nbsp;${item.userSurname}</td>
									<td id="brew_date">${date_brew[2]}/${date_brew[1]}/${date_brew[0]}</td>
									<td id="brew_quantity">${item.quantity}l</td>
									<td id="brew_desc" style="display: none;">${item.description}</td>
									<td id="brew_note" style="display: none;">${item.tasteNote}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</div>
		<div class="modal fade" id="brew_modal" tabindex="-1" role="dialog"
		aria-labelledby="alert_modal_label" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<div class="modal_field modal_Bname"><h6>Nome</h6><p></p></div>
					<div class="modal_field modal_Buser"><h6>Brewer</h6><p></p></div>
					<div class="modal_field modal_Bdate"><h6>Data</h6><p></p></div>
					<div class="modal_field modal_Bquantity"><h6>Quantita'</h6><p></p></div>
					<div class="modal_field modal_Bdesc"><h6>Descrizione</h6><p></p></div>
					<div class="modal_field modal_Bnote"><h6>Nota di degustazione</h6><p></p></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


