<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!---------------------- MENU ---------------------->

<div class="recipe_menu">

	<c:if test="${logged != null}">
		<div class="logout">
			<div class="log_user">
				<img src="images/logos/account.svg" alt="Logo">
				<h4 class="name_user">${user.name}&nbsp;${user.surname}</h4>
			</div>
			<a href="./account">ACCOUNT</a> <a href="./logout">ESCI</a>
		</div>
	</c:if>
	<a href="">CREA</a>
	<a href="">MISCELE</a>
	
</div>


