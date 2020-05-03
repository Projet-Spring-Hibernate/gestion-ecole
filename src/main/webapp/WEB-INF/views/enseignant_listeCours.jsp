<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <!-- ============================================================================ -->
<!-- Taglib -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<title>Enseignant - liste cours</title>
</head>
<body>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_enseignant.jsp" />

	<div class="mainContent">

		<h1>Liste des cours de l'enseignant</h1>
		
		<a href="${pageContext.request.contextPath}/cours/add-cours-formEnseignant"
			class="btn btn-primary btn-sm" role="button">Ajouter un cours</a>
	  
			
			
		<table class="table table-striped">
			<!-- Ajout d'un employe -->

			<tr>
				<th>ID cours</th>
				<th>libelle</th>
				<th>Date</th>
				<th>Durée</th>
				<th>Description</th>
				<th>Matière</th>
				<th>Promotion</th>
				<th></th>
			</tr>

			<c:forEach items="${attribut_cours_enseignant}" var="cours">
				<tr>
					<td>${cours.idCours}</td>
					<td>${cours.libelle}</td>
					<td>${cours.date}</td>
					<td>${cours.duree}</td>
					<td>${cours.description}</td>
					<td>${cours.matiere.libelle}</td>
					<td>${cours.promotion.libelle}</td>


					<!-- colonne pour afficher l'étudiant -->
					<td><a
						href="${pageContext.request.contextPath}/cours/afficher/${cours.idCours}">Afficher</a></td>

					<!-- colonne pour la modif de l'employe => envoi d'une requete http en get (url :http://localhost:8080/10_advanced_framework_spring-mvc/employes/update-employe-form?idemp=1
				vers la méthode afficherFormulaireUpdate() du controleur EmployesController -->
					<%-- 				<td><a href="${pageContext.request.contextPath}/employes/update-employe-form?idemp=${employe.idEmploye}">Modifier</a></td> --%>
				</tr>
			</c:forEach>



		</table>






	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>