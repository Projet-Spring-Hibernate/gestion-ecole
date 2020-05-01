<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!-- Taglib -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%--Ajout de la taglib de spring mvc 'form' --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<<!-- ============================================================================ -->
<!-- Lien vers .css -->
<!-- construction du chemin vers ma feuille de style -->
<spring:url value="/assets/styles/bootstrap.css" var="bootstrapCss" />
<spring:url value="/assets/styles/style_perso.css" var="StylePerso" />

<link rel="stylesheet" href="${bootstrapCss}" />
<link rel="stylesheet" href="${StylePerso}" />

<!-- Lien vers .js -->
<!-- construction du chemin vers ma feuille de style -->
<spring:url value="/assets/scripts/bootstrap.js" var="bootstrapJS" />
<spring:url value="/assets/scripts/jquery-3.4.1.js" var="jquery" />
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrapJS}"></script>

<!-- ============================================================================ -->

<title>Admin - ajouter cours</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<br /> <br />
		<h1>Formulaire d'ajout d'un cours</h1>

		<form:form modelAttribute="enseignantCommand" method="POST"
			action="${pageContext.request.contextPath}/enseignants/add">

			<%-- 			<form:errors path="*" cssClass="error_validation" element="div"/> --%>

			<table class="table table-striped">

				<tr>
					<td><form:label path="nom">Nom :</form:label></td>
					<td><form:input path="nom" /></td>
					<td><form:errors path="nom" /></td>
				</tr>
				<tr>
					<td><form:label path="prenom">Prenom :</form:label></td>
					<td><form:input path="prenom" /></td>
					<td><form:errors path="prenom" /></td>
				</tr>
				<tr>
					<td><form:label path="email">Email :</form:label></td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" /></td>
				</tr>

				<tr>
					<td><form:label path="rue">Rue :</form:label></td>
					<td><form:input path="rue" /></td>
					<td><form:errors path="rue" /></td>
				</tr>

				<tr>
					<td><form:label path="codePostal">Code postal :</form:label></td>
					<td><form:input path="codePostal" /></td>
					<td><form:errors path="codePostal" /></td>
				</tr>
				
				<tr>
					<td><form:label path="ville">Ville :</form:label></td>
					<td><form:input path="ville" /></td>
					<td><form:errors path="ville" /></td>
				</tr>

<!-- 				<tr> -->
<!-- 					<td>Promotion</td> -->
<%-- 					<td><c:forEach items="${attribut_listePromo}" var="promo">${promo.libelle} </c:forEach> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
			</table>
		</form:form>
	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>