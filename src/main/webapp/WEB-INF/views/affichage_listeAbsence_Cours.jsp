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
<title>Affichage absence</title>

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

		<h1>Feuille de présence pour le cours ${attribut_cours.libelle}</h1>

		<a class="btn btn-primary"
			href="${pageContext.request.contextPath}/absences/update-form/${attribut_cours.idCours}">Modifier/ajouter</a>
			
		<br /> <br />
		<table class="table table-striped">

			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Absence</th>
				<th>Motif</th>

			</tr>

			<c:forEach items="${attribut_liste_absences}" var="absence">
				<tr>
					<td>${absence.id}</td>
					<td><a href="${pageContext.request.contextPath}/etudiants/afficher/${absence.etudiant.identifiant}">${absence.etudiant.nom}</a></td>
					<td><a href="${pageContext.request.contextPath}/etudiants/afficher/${absence.etudiant.identifiant}">${absence.etudiant.prenom}</a></td>
					<td>${absence.absence  ? "Absent" : "Present"}</td>
					<td>${absence.motif}</td>


				</tr>
			</c:forEach>
		</table>
	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>