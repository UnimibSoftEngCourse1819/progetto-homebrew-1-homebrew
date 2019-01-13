<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action = "<%=request.getContextPath()%>/UserController" method="POST">
		Name: <input name="name" placeholder="insert name"/><br/>
		Surname: <input name="surname" placeholder="insert surname"/><br/>
		Date of birth: <input name="dateOfBirth" placeholder="AAAA-MM-GG"/><br/>
		Email: <input name="email" placeholder="***@***.**"/><br/>
		Password: <input name="password" placeholder="********"/><br/>
		<button type="submit">Enter</button>
	</form>

</body>
</html>