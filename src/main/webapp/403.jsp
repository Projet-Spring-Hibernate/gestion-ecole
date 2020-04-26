<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Taglib de spring security -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Statu HTTP 403 - </h1>
	
	<br/>
	
	<s:authorize access="hasRole('ROLE_USER')"> 
		<h2>
			Vous n'avez pas les autorisations requises pour cette action
		</h2>
		<h3>
			Veuillez vous connecter en tant qu'admin : <a href="<c:url value='/login.jsp'/>">Se connecter</a>
		</h3>
	
	</s:authorize>

</body>
</html>