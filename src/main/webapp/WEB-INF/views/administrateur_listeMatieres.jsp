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
<title>Admin - liste Matieres</title>
</head>
<body>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<h1>Liste des matieres de l'école</h1>

		<a href="${pageContext.request.contextPath}/matieres/add-form"
			class="btn btn-primary btn-sm" role="button">Ajouter une matière</a>	
			
			<br/><br/>
			
		<table class="table table-striped">
			<!-- Ajout d'un employe -->

			<tr>
				<th>ID Matiere</th>
				<th>Libelle</th>
				<th></th>
<!-- 				<th></th> -->
<!-- 				<th></th> -->
			</tr>

			<c:forEach items="${attribut_liste_matiere}" var="matiere">
				<tr>
					<td>${matiere.idMatiere}</td>
					<td>${matiere.libelle}</td>
					
				<td><a href="${pageContext.request.contextPath}/matieres/afficher/${matiere.idMatiere}">Afficher</a></td>

<!-- 					<td><a -->
<%-- 						href="${pageContext.request.contextPath}/matieres/delete/${matiere.idMatiere}">Supprimer</a> --%>
<!-- 					</td> -->
<!-- 					<td><a -->
<%-- 						href="${pageContext.request.contextPath}/matieres/update-form?idMatiere=${matiere.idMatiere}"> --%>

<!-- 							Modifier </a></td> -->

				</tr>
			</c:forEach>



		</table>






	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />



</body>
</html>