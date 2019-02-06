<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Miscele&nbsp;-&nbsp;HomeBrew</title>
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
			<h2 class="title">BrewDay</h2>
			<jsp:include page="includer/menu.jsp" />
		</div>
		<div class="row main">
			<div class="main_shadow">
				<div></div>
			</div>

			<c:forEach items="${brews}" var="item">
				<div class="recipes_element">
					<div class="row recipes_inner">
						<a href="./brew?n=${item.recipeID}"></a>

						<div class="col-9">
							<c:if test="${(fn:length(item.description)) > 300}">
								<c:set var="text"
									value="${fn:substring(item.description, 0, 300)}" />
								<c:set var="splittext" value="${fn:split(text,' ')}" />
								<c:set var="index"
									value="${fn:indexOf(text, splittext[fn:length(splittext)-2])}" />
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
</body>
</html>


