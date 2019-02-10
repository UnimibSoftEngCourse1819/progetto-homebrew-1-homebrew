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
			<jsp:include page="includer/menu.jsp" />
		</div>
		<div class="row main">
			<div class="main_shadow">
				<div></div>
			</div>

			<c:forEach items="${brews}" var="item">
				<div class="recipes_element">
					<div class="row recipes_inner">
						<div class="row">
							<div class="col-4">
								<h5>Nome</h5>
								<p>${item.name}</p>
							</div>
							<div class="col-4">
								<h5>Data</h5>

								<c:set var="date_brew" value="${fn:split(item.brewDate,'-')}" />
								<p>${date_brew[2]}/${date_brew[1]}/${date_brew[0]}</p>
							</div>
							<div class="col-4">
								<h5>Quantita'</h5>
								<p>${item.quantity}</p>
							</div>
						</div>
						<div>
							<h5>Descrizione</h5>
							<p>${item.description}</p>
							<h5>Note di degustazione</h5>
							<p>${item.tasteNote}</p>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
</body>
</html>


