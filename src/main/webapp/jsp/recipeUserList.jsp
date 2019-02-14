<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Le&nbsp;Mie&nbsp;Ricette&nbsp;-&nbsp;HomeBrew</title>
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
	<div class="row page recipe_list">
		<div class="recipes_menu_top">
			<div class="recipes_menu_top_shad"></div>
			<div class="recipes_menu_ins">
				<c:if test="${logged != null}">
					<a class="new_recipe" href="./new_recipe">CREA NUOVA</a>
				</c:if>
			</div>

		</div>
		<c:forEach items="${recipes}" var="item">
			<div class="recipes_element">
				<div class="row recipes_inner">
					<a href="./recipe?n=${item.recipeID}"></a>
					<div class="col-3">
						<img class="recipes_img" alt="Beer Image" src="${item.imagePath}">
					</div>
					<div class="col-9">
						<h3>${item.name}</h3>
						<c:if test="${item.visibility == 'private'}">
							<img class="recipes_lock" src="images/logos/lock.svg" alt="lock">
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

</body>
</html>


