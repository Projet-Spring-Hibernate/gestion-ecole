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
<title>Affichage matiere</title>

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

		<h1>Fiche de la matiere ${attribut_matiere.libelle}</h1>
		<table class="table">

			<tr>
				<td>ID matiere</td>
				<td>${attribut_matiere.idMatiere}</td>
			</tr>
			<tr>
				<td>Libelle</td>
				<td>${attribut_matiere.libelle}</td>
			</tr>
			<tr>
				<td>Enseignants</td>
				<td><c:forEach items="${attribut_enseignant}" var="enseignant"><a
						href="${pageContext.request.contextPath}/enseignants/afficher/${enseignant.identifiant}">${enseignant.nom} ${enseignant.prenom}</a> <br/></c:forEach>
			</tr>

			<!-- Affichage des boutons supprimer et modifier uniquement pour l'admin  -->
			<s:authorize access="hasRole('ROLE_ADMINISTRATEUR')">
				<tr>
					<td><a class="btn btn-warning"
						href="${pageContext.request.contextPath}/matieres/update-form?idMatiere=${attribut_matiere.idMatiere}">Modifier</a>
						<a class="btn btn-danger" href="${pageContext.request.contextPath}/matieres/delete/${attribut_matiere.idMatiere}">Supprimer</a> </td>
					<td></td>
				</tr>
			</s:authorize>
		</table>






	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />



</body>
</html>