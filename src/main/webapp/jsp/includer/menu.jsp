<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!---------------------- LOGIN ---------------------->

<div class="top_menu">
	<c:if test="${logged == null}">
		<div class="login">
			<form method="post" action="./home">
				<div class="login_input">
					<c:if test="${errorLogin != null}">
						<input class="wrong" type="text" name="username"
							placeholder="username" autocomplete="on">
						<input class="wrong" type="password" name="password"
							placeholder="password" autocomplete="on">
					</c:if>
					<c:if test="${errorLogin == null}">
						<input type=text name="username" placeholder="username"
							autocomplete="on">
						<input type="password" name="password" placeholder="password"
							autocomplete="on">
					</c:if>
				</div>
				<c:if test="${page == null || page != 'register'}">
					<a id="register_link" href="./register">Registrati</a>
				</c:if>
				<button type="submit" id="login_submit">login</button>
			</form>
		</div>
	</c:if>
	<c:if test="${logged != null}">
		<div class="logout">
			<div class="log_user">
				<img src="images/logo_account.svg" alt="Logo">
				<h4 class="name_user">${user.name}&nbsp;${user.surname}</h4>
			</div>
			<a href="./account">ACCOUNT</a> <a href="./logout">ESCI</a>
		</div>
	</c:if>
</div>



<!---------------------- MENU ---------------------->

<div class="menu_list">
	<c:if test="${logged == null}">
		<ul>
			<c:choose>
				<c:when test="${page == 'login'}">
					<li class="menu_item active">HOME</li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
				</c:when>
				<c:when test="${page == 'recipes'}">
					<li class="menu_item"><a href="./login">HOME</a></li>
					<li class="menu_item active">RICETTE</li>
				</c:when>
				<c:otherwise>
					<li class="menu_item"><a href="./login">HOME</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</c:if>
	<c:if test="${logged != null}">
		<ul>
			<c:choose>
				<c:when test="${page == 'home'}">
					<li class="menu_item active">HOME</li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
					<li class="menu_item"><a href="./equipment">ATTREZZI</a></li>
				</c:when>
				<c:when test="${page == 'wsibt'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item active">WHAT SHOULD I BREW TODAY?</li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
					<li class="menu_item"><a href="./equipment">ATTREZZI</a></li>
				</c:when>
				<c:when test="${page == 'recipes'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item active">RICETTE</li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
					<li class="menu_item"><a href="./equipment">ATTREZZI</a></li>
				</c:when>
				<c:when test="${page == 'brews'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item active">MISCELE</li>
					<li class="menu_item"><a href="./equipment">ATTREZZI</a></li>
				</c:when>
				<c:when test="${page == 'tools'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
					<li class="menu_item active">ATTREZZI</li>
				</c:when>
				<c:otherwise>
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
					<li class="menu_item"><a href="./equipment">ATTREZZI</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</c:if>
</div>




