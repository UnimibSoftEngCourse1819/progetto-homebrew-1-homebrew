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
			
			<form action="./updateEquipment" method="post" name="equipment_form">
					<table>
					<c:forEach items="${tool}" var="item">
						<tr>
							<td><h3>${item.toolName}:</h3></td>
							<td><h3>
									<input type="number" name="${item.toolID}"
										id="testoformdue" placeholder="${item.measure}" min = "0">
								</h3></td>
						</tr>
					</c:forEach>
				</table>
					<input type="submit" value="AGGIORNA" name="action" id="submitdue"
						onClick="return(controlForm());">

				</form>
			
			
		</div>
	</div>

</body>
</html>

