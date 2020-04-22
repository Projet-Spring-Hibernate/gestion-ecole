<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Lien vers bootstrap.css -->
<!-- construction du chemin vers ma feuille de style -->
<spring:url value="/assets/styles/bootstrap.css" var="bootstrapCss"/>

<link rel="stylesheet" href="${bootstrapCss}"/>

<!-- Lien vers bootstrap.js -->
<!-- construction du chemin vers ma feuille de style -->
<spring:url value="/assets/scripts/bootstrap.css" var="bootstrapJS"/>

<script type="text/javascript" src="${bootstrapJS}"></script>

</head>
<body>
	 <%-- inclusion dynamique du fragment entete.jsp --%>
	 <jsp:include page="/WEB-INF/fragments/entete.jsp" />
	 
	 <br/><br/>
</body>
</html>