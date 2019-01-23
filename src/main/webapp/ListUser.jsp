<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>List Users</title>
</head>
<body>


<c:forEach var="columnHeader" items="${columnHeaders}">
        <c:out value="${columnHeader}" />
</c:forEach>


<br><br>
<form action="./LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>