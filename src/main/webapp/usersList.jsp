<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- Direttiva per impostare la libreria da utilizzare per i tag con prefisso c
In questo caso utilizziamo la libreria JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users List</title>
</head>
<body>


	<h1>Users</h1>
	<!-- All'interno di una ordered list (<ol></ol>), viene eseguito un ciclo
	sull'attributo 'users' con scope di richiesta. L'oggetto 'loop' al quale 
	viene assegnato lo status del ciclo tramite l'attributo varStatus,
	conterrà informazioni utili sul ciclo, ad esempio l'indice corrente.
	
	Ogni elemento della lista ordinata (<li></li>) contiene un link (<a></a>)
	nel quale viene aggiunto il parametro 'id' alla query string.
	Ciò che viene mostrato come link è l'email dell'utente. -->
	<ol>
		<c:forEach items="${requestScope.users}" var="user" varStatus="loop">
			<li>
					<c:out value="${user.id}"></c:out>
			</li>
		</c:forEach>
	</ol>
	<hr>
</body>
</html>