<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="model.user.UserBrewer,java.util.ArrayList" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>	
	<%
		ArrayList<UserBrewer> users = (ArrayList<UserBrewer>)request.getAttribute("utenti");
		for(int i=0; i<users.size(); i++){
			UserBrewer user= users.get(i);
			%>
			<li><%= user.getNome() %>
			<% 
		}
	%>
	</ul>
</body>
</html>