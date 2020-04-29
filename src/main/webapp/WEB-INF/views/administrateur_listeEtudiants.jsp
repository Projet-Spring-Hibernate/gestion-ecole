<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- ============================================================================ -->   
    <!-- Taglib de spring security -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!-- ============================================================================ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - liste etudiants</title>

<!-- ============================================================================ -->
<!-- Lien vers .css -->
<!-- construction du chemin vers ma feuille de style -->
<spring:url value="/assets/styles/bootstrap.css" var="bootstrapCss"/>
<spring:url value="/assets/styles/style_perso.css" var="StylePerso"/>

<link rel="stylesheet" href="${bootstrapCss}"/>
<link rel="stylesheet" href="${StylePerso}"/>

<!-- Lien vers .js -->
<!-- construction du chemin vers ma feuille de style -->
<spring:url value="/assets/scripts/bootstrap.js" var="bootstrapJS"/>
<spring:url value="/assets/scripts/jquery-3.4.1.js" var="jquery"/>
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrapJS}"></script>

<!-- ============================================================================ -->
</head>
<body>

	 <%-- inclusion dynamique du fragment entete.jsp --%>
	 <jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />
	 
	 <div class="mainContent">
	 
	 	<h1>Liste des �tudiants de l'�cole</h1>

	<table class="table table-striped">
		<!-- Ajout d'un employe -->

		<tr>
			<th>ID �tudiant</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Date de naissance</th>
			<th>Email</th>
			<th></th>
		</tr>
		
		<c:forEach items="${attribut_liste_etudiants}" var="etudiant">
			<tr>
				<td>${etudiant.identifiant}</td>
				<td>${etudiant.nom}</td>
				<td>${etudiant.prenom}</td>
				<td>${etudiant.dateDeNaissance}</td>
				<td>${etudiant.email}</td>
				
				
				<!-- colonne pour afficher l'�tudiant -->
				<td><a href="${pageContext.request.contextPath}/etudiants/afficher/${etudiant.identifiant}">Afficher</a></td>
				
				<!-- colonne pour la modif de l'employe => envoi d'une requete http en get (url :http://localhost:8080/10_advanced_framework_spring-mvc/employes/update-employe-form?idemp=1
				vers la m�thode afficherFormulaireUpdate() du controleur EmployesController -->
<%-- 				<td><a href="${pageContext.request.contextPath}/employes/update-employe-form?idemp=${employe.idEmploye}">Modifier</a></td> --%>
			</tr>
		</c:forEach>
	
	
	
	</table>
	 
	 
	 
	 
	 
	 
	 </div>
	 
	 
	 <%-- inclusion dynamique du fragment entete.jsp --%>
	 <jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />



</body>
</html>