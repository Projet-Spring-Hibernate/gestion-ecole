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

<title>Bienvenue</title>
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

		<h1>Bienvenue ${attribut_personneConnecte.prenom }
			${attribut_personneConnecte.nom }</h1>


		<%-- 		<s:authentication property="authorities" var="authorites" /> --%>
		<!-- 		<ul> -->
		<%-- 			<c:forEach items="${authorites }" var="auth"> --%>
		<%-- 				<li>${auth.authority}</li> --%>
		<%-- 			</c:forEach> --%>
		<!-- 		</ul> -->

		<br />

		<hr />
		<br />

		<h4 style="font-size: 20px;">Rechercher une personne : (étudiant, enseignant ou
			administrateur)</h4>

		<form action="${pageContext.request.contextPath}/personnes/chercher-par-nom">
			<input type="text" name="pNom"/>
			<input class="btn btn-primary btn-sm" type="submit" value="Chercher" />
		</form>
		<hr/>
		
		<c:if test="${ not empty attribut_liste_etudiants || not empty attribut_liste_admins || not empty attribut_liste_enseignants}">
		<table class="table table-striped">
			<tr>
				<th>ID personne</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Email</th>
				<th></th>
			</tr>
			<c:forEach items="${attribut_liste_etudiants}" var="etudiant">
				<tr>
					<td>${etudiant.identifiant}</td>
					<td>${etudiant.nom}</td>
					<td>${etudiant.prenom}</td>
					<td>${etudiant.email}</td>

					<!-- colonne pour afficher l'etudiant -->
					<td><a
						href="${pageContext.request.contextPath}/etudiants/afficher/${etudiant.identifiant}">Afficher</a></td>
				</tr>
			</c:forEach>
			<c:forEach items="${attribut_liste_enseignants}" var="enseignant">
				<tr>
					<td>${enseignant.identifiant}</td>
					<td>${enseignant.nom}</td>
					<td>${enseignant.prenom}</td>
					<td>${enseignant.email}</td>

					<!-- colonne pour afficher l'enseignant -->
					<td><a
						href="${pageContext.request.contextPath}/enseignants/afficher/${enseignant.identifiant}">Afficher</a></td>
				</tr>
			</c:forEach>
			<c:forEach items="${attribut_liste_admins}" var="admin">
				<tr>
					<td>${admin.identifiant}</td>
					<td>${admin.nom}</td>
					<td>${admin.prenom}</td>
					<td>${admin.email}</td>

					<!-- colonne pour afficher l'enseignant -->
					<td><a
						href="${pageContext.request.contextPath}/administrateurs/afficher/${enseignant.identifiant}">Afficher</a></td>
				</tr>
			</c:forEach>
		</table>
</c:if>

	</div>

	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>