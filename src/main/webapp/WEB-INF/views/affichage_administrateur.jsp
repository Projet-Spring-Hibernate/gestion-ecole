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
<title>Affichage Administrarteur</title>

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

	<!-- Affichage de l'entête correspondant au role de la personne connectée  -->
	<s:authorize access="hasRole('ROLE_ADMINISTRATEUR')">
		<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />
	</s:authorize>


	<div class="mainContent">

		<h1>Fiche de
			${attribut_administrateur.prenom} ${attribut_administrateur.nom}</h1>


		<table class="table">

			<tr>
				<td>ID admin</td>
				<td>${attribut_administrateur.identifiant}</td>
			</tr>
			<tr>
				<td>Nom</td>
				<td>${attribut_administrateur.nom}</td>
			</tr>
			<tr>
				<td>Prenom</td>
				<td>${attribut_administrateur.prenom}</td>
			</tr>

			<tr>
				<td>Email</td>
				<td>${attribut_administrateur.email}</td>
			</tr>

			<tr>
				<td>Adresse</td>
				<td>${attribut_adresse.rue}${attribut_adresse.codePostal}
					${attribut_adresse.ville}</td>
			</tr>



			<!-- Affichage des boutons supprimer et modifier uniquement pour l'admin  -->
			<s:authorize access="hasRole('ROLE_ADMINISTRATEUR')">
				<tr>
					<td><a class="btn btn-warning"
						href="${pageContext.request.contextPath}/administrateurs/update-form/${attribut_administrateur.identifiant}">Modifier</a>
						<a class="btn btn-danger"
						href="${pageContext.request.contextPath}/administrateurs/delete/${attribut_administrateur.identifiant}">Supprimer</a></td>
					<td></td>
				</tr>
			</s:authorize>

		</table>

	</div>


	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>