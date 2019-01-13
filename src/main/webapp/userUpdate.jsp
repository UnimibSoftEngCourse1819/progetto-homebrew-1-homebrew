<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="dao.*"%>
<%@ page import ="model.user.*" %> 
<%@ page import ="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
</head>
<body>
	<% Brewer brewer = (Brewer)request.getAttribute("brewer");  
		int id = -1;
		String name = null;
		String surname = null;
		Date dateOfBirth = null;
		String mail = null;
		String password = null;
		String kind = null;
		
		if(brewer != null){
			id = brewer.getId();
			name = brewer.getName();
			surname = brewer.getSurname();
			dateOfBirth = brewer.getDateOfBirth();
			mail = brewer.getMail();
			password = brewer.getPassword();
			kind = brewer.getKind();
		}
	%>
	
	<form action = "<%=request.getContextPath()%>/UserController" method="POST">
		<input name="id" value="<%= id %>" type = "hidden"/>
		Name: <input name="name" value="<%= name %>"/><br/>
		Surname: <input name="surname" value="<%= surname %>"/><br/>
		Date of birth: <input name="dateOfBirth" value="<%= dateOfBirth %>"/><br/>
		Email: <input name="mail" value="<%= mail %>"/><br/>
		Password: <input name="password" value="<%= password %>"/><br/>
		Kind: <input name="kind" value="<%= kind %>"/><br/>
		<button type="submit" name="action" value="update">Update</button>
	</form>
</body>
</html>