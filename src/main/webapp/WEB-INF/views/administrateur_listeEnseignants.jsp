<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- ============================================================================ -->
<!-- Taglib -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<title>Admin - liste enseignants</title>
</head>
<body>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<h1>Liste des enseignants de l'�cole</h1>

		<a
			href="${pageContext.request.contextPath}/enseignants/add-enseignant-form"
			class="btn btn-primary btn-sm" role="button">Ajouter un
			enseignant</a> <br /> <br />

		<table class="table table-striped">


			<tr>
				<th>ID enseignant</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Email</th>
				<th></th>
			</tr>

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

		</table>

	</div>


	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />



</body>
</html>