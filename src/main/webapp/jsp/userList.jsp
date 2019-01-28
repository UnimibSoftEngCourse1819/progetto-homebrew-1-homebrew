<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>List Users</title>
</head>
<body>

<c:forEach items="${users}" var="item">
   ${item.name}<br><br>
</c:forEach>



<br><br>
<form action="./LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>