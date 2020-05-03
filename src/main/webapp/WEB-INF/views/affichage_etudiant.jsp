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
<title>Affichage etudiant</title>

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
	<s:authorize access="hasRole('ROLE_ENSEIGNANT')">
		<jsp:include page="/WEB-INF/fragments/entete_enseignant.jsp" />
	</s:authorize>
	<s:authorize access="hasRole('ROLE_ETUDIANT')">
		<jsp:include page="/WEB-INF/fragments/entete_etudiant.jsp" />
	</s:authorize>

	<div class="mainContent">
		<br />
		<div class="row">
			<div class="col-12 col-sm-9 col-md-9 col-lg-9 col-xl-9" style="">
				<h1 style="margin-top: 70px">Fiche de
					${attribut_etudiant.prenom} ${attribut_etudiant.nom}</h1>
			</div>
			<div class="col-12 col-sm-3 col-md-3 col-lg-3 col-xl-3"
				style="height: 150px;">
				<img id="photo_etudiant" alt="La photo ${attribut_etudiant.photo} n'est pas disponible." style="height: 150px;"
					src="/01_gestion_ecoles/assets/images/${attribut_etudiant.photo}">
			</div>
		</div>

		<br />

		<table class="table">

			<tr>
				<td>ID étudiant</td>
				<td>${attribut_etudiant.identifiant}</td>
			</tr>
			<tr>
				<td>Nom</td>
				<td>${attribut_etudiant.nom}</td>
			</tr>
			<tr>
				<td>Prenom</td>
				<td>${attribut_etudiant.prenom}</td>
			</tr>
			<tr>
				<td>Date de naissance</td>
				<td>${attribut_etudiant.dateDeNaissance}</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${attribut_etudiant.email}</td>
			</tr>

			<tr>
				<td>Adresse</td>
				<td>${attribut_adresse.rue}${attribut_adresse.codePostal}
					${attribut_adresse.ville}</td>
			</tr>

			<tr>
				<td>Promotion</td>
				<td><c:forEach items="${attribut_listePromo}" var="promo">
						<a
							href="${pageContext.request.contextPath}/promotions/afficher/${promo.idPromotion}">${promo.libelle}
						</a>
						<br />
					</c:forEach></td>
			</tr>

			<!-- Affichage des boutons supprimer et modifier uniquement pour l'admin  -->
			<s:authorize access="hasRole('ROLE_ADMINISTRATEUR')">
				<tr>
					<td><a class="btn btn-warning"
						href="${pageContext.request.contextPath}/etudiant/update-form/${attribut_etudiant.identifiant}">Modifier</a>
						<a class="btn btn-danger"
						href="${pageContext.request.contextPath}/etudiants/delete/${attribut_etudiant.identifiant}">Supprimer</a></td>
					<td></td>
				</tr>
			</s:authorize>

		</table>

	</div>


	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />



</body>
</html>