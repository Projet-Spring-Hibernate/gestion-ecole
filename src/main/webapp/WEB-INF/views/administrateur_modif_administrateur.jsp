<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- ============================================================================ -->
<!-- Taglib -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%--Ajout de la taglib de spring mvc 'form' --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- ============================================================================ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- ============================================================================ -->
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

<title>Admin - modifier administrateur</title>
<body>
	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<br /> <br />
		<h1>Formulaire de modification d'un administrateur</h1>

		<form:form modelAttribute="administrateurForm" method="POST"
			action="${pageContext.request.contextPath}/administrateurs/update">

			<%-- 			<form:errors path="*" cssClass="error_validation" element="div"/> --%>
			<form:hidden path="administrateur.identifiant" />
			<form:hidden path="administrateur.motdepasse" />
		  <form:hidden path="adresse.adresse_id" />  
			<table class="table">

				<tr>
					<td><form:label path="administrateur.nom">Nom :</form:label></td>
					<td><form:input path="administrateur.nom" /></td>
					<td><form:errors path="administrateur.nom" /></td>
				</tr>
				<tr>
					<td><form:label path="administrateur.prenom">Prenom :</form:label></td>
					<td><form:input path="administrateur.prenom" /></td>
					<td><form:errors path="administrateur.prenom" /></td>
				</tr>

		
				<tr>
					<td><form:label path="administrateur.email">Email :</form:label></td>
					<td><form:input path="administrateur.email" /></td>
					<td><form:errors path="administrateur.email" /></td>
				</tr>

			
				
					<tr>
					<td><form:label path="adresse.rue">Rue :</form:label></td>
					<td><form:input path="adresse.rue" /></td>
					<td><form:errors path="adresse.rue" /></td>
				</tr>

				<tr>
					<td><form:label path="adresse.codePostal">Code postal :</form:label></td>
					<td><form:input path="adresse.codePostal" /></td>
					<td><form:errors path="adresse.codePostal" /></td>
				</tr>


				<tr>
					<td><form:label path="adresse.ville">Ville :</form:label></td>
					<td><form:input path="adresse.ville" /></td>
					<td><form:errors path="adresse.ville" /></td>
				</tr>


				<td colspan="3"><input class="btn btn-primary" type="submit"
					value="Modifier" /></td>

			</table>
		</form:form>
	</div>


	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>