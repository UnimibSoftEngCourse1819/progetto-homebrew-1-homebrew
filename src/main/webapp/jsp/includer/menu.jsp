<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="title">
	<a href="./home"> <img src="images/logos/account.svg" alt="Logo">
		<span>brewday</span>
	</a>
</div>

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
		<div class="account">
			<div class="log_user">
				<c:if test="${section == 'general'}">
					<a href="./my_recipes"><span>${user.name}&nbsp;${user.surname}</span>
					</a>
				</c:if>
				<c:if test="${section == 'personal'}">
					<a href="./home"><span>Home</span> </a>
				</c:if>
			</div>
		</div>
	</c:if>
</div>

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
				</c:when>
				<c:when test="${page == 'wsibt'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item active">WHAT SHOULD I BREW TODAY?</li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
				</c:when>
				<c:when test="${page == 'recipes'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item active">RICETTE</li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
				</c:when>
				<c:when test="${page == 'brews'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item active">MISCELE</li>
				</c:when>
				<c:when test="${page == 'my_recipes'}">
					<li class="menu_item active">LE MIE RICETTE</li>
					<li class="menu_item"><a href="./my_brews">LE MIE MISCELE</a></li>
					<li class="menu_item"><a href="./account">ACCOUNT</a></li>
					<li class="menu_item"><a href="./pantry">DISPENSA</a></li>
					<li class="menu_item"><a href="./equipment">EQUIPAGGIAMENTO</a></li>
				</c:when>
				<c:when test="${page == 'my_brews'}">
					<li class="menu_item"><a href="./my_recipes">LE MIE
							RICETTE</a></li>
					<li class="menu_item active">LE MIE MISCELE</li>
					<li class="menu_item"><a href="./account">ACCOUNT</a></li>
					<li class="menu_item"><a href="./pantry">DISPENSA</a></li>
					<li class="menu_item"><a href="./equipment">EQUIPAGGIAMENTO</a></li>
				</c:when>
				<c:when test="${page == 'account'}">
					<li class="menu_item"><a href="./my_recipes">LE MIE
							RICETTE</a></li>
					<li class="menu_item"><a href="./my_brews">LE MIE MISCELE</a></li>
					<li class="menu_item active">ACCOUNT</li>
					<li class="menu_item"><a href="./pantry">DISPENSA</a></li>
					<li class="menu_item"><a href="./equipment">EQUIPAGGIAMENTO</a></li>
				</c:when>
				<c:when test="${page == 'pantry'}">
					<li class="menu_item"><a href="./my_recipes">LE MIE
							RICETTE</a></li>
					<li class="menu_item"><a href="./my_brews">LE MIE MISCELE</a></li>
					<li class="menu_item"><a href="./account">ACCOUNT</a></li>
					<li class="menu_item active">DISPENSA</li>
					<li class="menu_item"><a href="./equipment">EQUIPAGGIAMENTO</a></li>
				</c:when>
				<c:when test="${page == 'equipment'}">
					<li class="menu_item"><a href="./my_recipes">LE MIE
							RICETTE</a></li>
					<li class="menu_item"><a href="./my_brews">LE MIE MISCELE</a></li>
					<li class="menu_item"><a href="./account">ACCOUNT</a></li>
					<li class="menu_item"><a href="./pantry">DISPENSA</a></li>
					<li class="menu_item active">EQUIPAGGIAMENTO</li>
				</c:when>

				<c:when test="${section == 'general'}">
					<li class="menu_item"><a href="./home">HOME</a></li>
					<li class="menu_item"><a href="./wsibt">WHAT SHOULD I BREW
							TODAY?</a></li>
					<li class="menu_item"><a href="./recipes">RICETTE</a></li>
					<li class="menu_item"><a href="./brews">MISCELE</a></li>
				</c:when>

				<c:when test="${section == 'personal'}">
					<li class="menu_item"><a href="./my_recipes">LE MIE
							RICETTE</a></li>
					<li class="menu_item"><a href="./my_brews">LE MIE MISCELE</a></li>
					<li class="menu_item"><a href="./account">ACCOUNT</a></li>
					<li class="menu_item"><a href="./pantry">DISPENSA</a></li>
					<li class="menu_item"><a href="./equipment">EQUIPAGGIAMENTO</a></li>
				</c:when>
			</c:choose>
			<li class="menu_item"><a href="./logout">ESCI</a></li>
		</ul>
	</c:if>
</div>




