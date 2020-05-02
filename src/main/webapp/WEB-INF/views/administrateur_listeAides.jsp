<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<spring:url value="/assets/styles/bootstrap.css" var="bootstrapCss"/>
<spring:url value="/assets/styles/style_perso.css" var="StylePerso"/>

<link rel="stylesheet" href="${bootstrapCss}" />
<link rel="stylesheet" href="${StylePerso}" />

<spring:url value="/assets/scripts/bootstrap.js" var="bootstrapJS"/>
<spring:url value="/assets/scripts/jquery-3.4.1.js" var="jquery"/>
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrapJS}"></script>
<title>Admin - liste pages d'aide</title>
</head>
<body>
	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<h1>Liste des pages d'aide disponibles</h1>
		
		<a href="${pageContext.request.contextPath}/cours/add-aide-form"
			class="btn btn-primary btn-sm" role="button">Mettre à jour la rubrique "Aides"</a>
	</div>
	 <%-- inclusion dynamique du fragment entete.jsp --%>
	 <jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
	  
</body>
</html>