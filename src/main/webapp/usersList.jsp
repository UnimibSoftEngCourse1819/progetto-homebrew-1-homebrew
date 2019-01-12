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
		ArrayList<Birraio> birrai = (ArrayList<Birraio>)request.getAttribute("birrai");
		for(int i=0; i<birrai.size(); i++){
			Birraio birraio= birrai.get(i);
			%>
			<li><%= birraio.getNome() %>
			<% 
		}
	%>
	</ul>
</body>
</html>