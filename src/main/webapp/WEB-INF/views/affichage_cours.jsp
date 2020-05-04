<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- ============================================================================ -->
<!-- Taglib de spring security -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- ============================================================================ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Affichage cours</title>

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
</head>
<body>

	<s:authorize access="hasRole('ROLE_ADMINISTRATEUR')">
		<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />
	</s:authorize>
	<s:authorize access="hasRole('ROLE_ENSEIGNANT')">
		<jsp:include page="/WEB-INF/fragments/entete_enseignant.jsp" />
	</s:authorize>
	<s:authorize access="hasRole('ROLE_ETUDIANT')">
		<jsp:include page="/WEB-INF/fragments/entete_etudiant.jsp" />
	</s:authorize>

	<div class="mainContent">

		<br /> <br />
		<table class="table">

			<tr>
				<td>ID cours</td>
				<td>${attribut_cours.idCours}</td>
			</tr>
			<tr>
				<td>Libellé</td>
				<td>${attribut_cours.libelle}</td>
			</tr>
			<tr>
				<td>Date</td>
				<td>${attribut_cours.date}</td>
			</tr>
			<tr>
				<td>Durée</td>
				<td>${attribut_cours.duree}</td>
			</tr>
			<tr>
				<td>Description</td>
				<td>${attribut_cours.description}</td>
			</tr>
			<tr>
				<td>Matière</td>
				<td>${attribut_cours.matiere.libelle}</td>
			</tr>
			<tr>
				<td>Promotion</td>
				<td>${attribut_cours.promotion.libelle}</td>
			</tr>

			<!-- 			<tr> -->
			<!-- 				<td><a class="btn btn-warning" -->
			<%-- 					href="${pageContext.request.contextPath}/cours/update-form/${attribut_enseignant.identifiant}">Modifier</a> --%>
			<!-- 					<a class="btn btn-danger" -->
			<%-- 					href="${pageContext.request.contextPath}/cours/delete/${attribut_enseignant.identifiant}">Supprimer</a></td> --%>
			<!-- 			</tr> -->

			<s:authorize access="hasRole('ROLE_ADMINISTRATEUR')">
				<td><a class="btn btn-warning"
					href="${pageContext.request.contextPath}/cours/update-cours-form/${attribut_cours.idCours}">Modifier</a>
			</s:authorize>
			<s:authorize access="hasRole('ROLE_ENSEIGNANT')">
				<td><a class="btn btn-warning"
					href="${pageContext.request.contextPath}/cours/update-cours-formEnseignant/${attribut_cours.idCours}">Modifier</a>
			</s:authorize>
			<s:authorize access="hasRole('ROLE_ENSEIGNANT')">
				<td><a class="btn btn-success"
					href="${pageContext.request.contextPath}/etudiantCours/ajout-pour-le-cours/${attribut_cours.idCours}">Modifier</a>
			</s:authorize>
		</table>
	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>