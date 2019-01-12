<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="dao.*"%>
<%@ page import ="model.user.*" %> 
<%@ page import ="java.util.ArrayList" %>
<%! @SuppressWarnings("unchecked") %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>	
	<%	
		ArrayList<Brewer> brewers = (ArrayList<Brewer>)request.getAttribute("brewers");
		for(int i=0; i<brewers.size(); i++){
			Brewer brewer= brewers.get(i);
			%>
			<li><%= brewer.getName() %>
			<% 
		}
	%>
	</ul>
</body>
</html>