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
<link href="css/brews.css" rel="stylesheet" type="text/css" />
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
	<div class="row page brew-list">
		<div class="menu-top-fix">
			<div class="menu-top-shad"></div>
			<div class="menu-top-ins">
				<h4>Miscele</h4>
			</div>
		</div>
		<c:if test="${not empty brews}">
			<c:forEach items="${brews}" var="item">
				<div class="brew-element">
					<div class="row brew-inner">
						<table>
							<tbody>
								<tr>
									<td>
										<h5>NOME:</h5>
										<p>${item.name}</p>
									</td>
									<td>
										<h5>DATA:</h5> <c:set var="date_brew"
											value="${fn:split(item.brewDate,'-')}" />
										<p>${date_brew[2]}/${date_brew[1]}/${date_brew[0]}</p>
									</td>
									<td>
										<h5>QUANTIT&Agrave;:</h5>
										<p>${item.quantity}</p>
									</td>
									<td>
										<h5>RICETTA:</h5> <a href="./recipe?n=${item.recipeID}">${item.recipeName}</a>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="brew-desc">
							<h5>DESCRIZIONE</h5>
							<p>${item.description}</p>
							<h5>NOTA DI DEGUSTAZIONE</h5>
							<p>${item.tasteNote}</p>

						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty brews}">
			<div class="empty-list">
				<p>Nessuna miscela trovata</p>
			</div>
		</c:if>
	</div>
</body>
</html>


