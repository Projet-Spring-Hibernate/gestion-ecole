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
<title>Enseignant - liste absence</title>
</head>
<body>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<h1>Liste des absences des étudints de l'enseignant</h1>
	  

			
			
		<table class="table table-striped">
			<!-- Ajout d'un employe -->

			<tr>
				<th>ID cours</th>
				<th>absence</th>
				<th>motif </th>
				<th>Nom</th>
				<th>Prenom</th>
				<th> </th>
							</tr>

			<c:forEach items="${attribut_liste_absence}" var="absence">
				<tr>
			
					<td>${absence.id}</td>
				
					<td>${absence.absence}</td>
					
					<td>${absence.motif}</td>
	
					<td>${absence.etudiant.nom}</td>
				
					<td>${absence.etudiant.prenom}</td>
						<td><a
						href="${pageContext.request.contextPath}/etudiantCours/afficher/${etudiantCours.id}">Afficher</a></td>
					
				</tr>
			</c:forEach>



		</table>






	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>