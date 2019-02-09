<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ricette&nbsp;-&nbsp;HomeBrew</title>
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
				<div class="recipes_menu">
					<c:if test="${logged != null}">
						<a href="./new_recipe">CREA NUOVA</a>
					</c:if>
					<button type="submit" name="action" value="CERCA" id="">
						<img alt="" src=""> cer
					</button>
					<div id="open_search">
						<a href="./recipes">MOSTRA TUTTE</a>

						<form action="./recipes" method="post" name="recipes_search_form">
							<input type="hidden" name="typeSearch" value="searchName">
							<p>NOME RICETTA:</p>
							<input type="text" name="nameRecipe" placeholder="Nome">
							<button type="submit" name="action">CERCA</button>
						</form>

						<form action="./recipes" method="post" name="recipes_search_form">
							<input type="hidden" name="typeSearch" value="searchIngredients">

							<table>
								<c:forEach items="${ingredient}" var="item">
									<tr>
										<td>${item.name}:</td>
										<td><input type="number"
											name="ingrName-${item.ingredientID}" placeholder="0" min="0">${item.measure}
										</td>
									</tr>
								</c:forEach>
							</table>
							<button type="submit" name="action">CERCA</button>
						</form>
					</div>
				</div>
				<c:forEach items="${recipes}" var="item">
					<div class="recipes_element">
						<div class="row recipes_inner">
							<a href="./recipe?n=${item.recipeID}"></a>
							<div class="col-3">
								<img class="recipes_img" alt="Beer Image"
									src="${item.imagePath}">
							</div>
							<div class="col-9">
								<h3>${item.name}</h3>
								<c:if test="${item.visibility == 'private'}">
									<img class="recipes_lock" src="images/logos/lock.svg"
										alt="lock">
								</c:if>

								<c:if test="${(fn:length(item.description)) > 300}">
									<c:set var="text"
										value="${fn:trim(fn:substring(item.description, 0, 300))}%&%&%" />
									<c:set var="splittext" value="${fn:split(text,' ')}" />
									<c:set var="index"
										value="${fn:indexOf(text, splittext[fn:length(splittext)-1])}" />
									<p>${fn:substring(text, 0, index-1)}...</p>
								</c:if>

								<c:if test="${(fn:length(item.description)) <= 300}">
									<p>${item.description}</p>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>


