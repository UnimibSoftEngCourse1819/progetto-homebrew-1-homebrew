<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Equipaggiamento&nbsp;-&nbsp;HomeBrew</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/equipment.css" rel="stylesheet" type="text/css" />
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
	<div class="row page equipment">
		<div class="row menu-top-color">
			<h4>EQUIPAGGIAMENTO</h4>
		</div>
		<div class="row equipment-cont">
			<div class="col-5 my-equipment">
				<h5>Il mio equipaggiamento</h5>
				<span>La capacit&agrave; massima dell'equipaggiamento &eacute; la
					minore tra quelle inserite.</span>
				<div class="my-tools">
					<c:forEach items="${equipment}" var="item">
						<div class="equipment-tool">
							<h6>${item.toolName}:</h6>
							<p>${item.capacity}${item.measure}</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-7 edit-equipment">
				<h5>Modifica equipaggiamento</h5>
				<form action="./equipment" method="post" name="equipment_form">
					<c:forEach items="${tools}" var="item">
						<div class="tool">
							<h6>${item.toolName}</h6>
							<div class="input-equipment">
								<c:if test="${item.measure != 'available'}">
									<input type="number" name="${item.toolID}" min="0">
									<p>${item.measure}</p>
								</c:if>
								<c:if test="${item.measure == 'available'}">
									<input type="checkbox" name="${item.toolID}">
									<p>[aggiungi]</p>
								</c:if>
							</div>
						</div>
					</c:forEach>
					<button name="updateEquipment" type="submit" id="updateEquipment"
						disabled>AGGIORNA</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

