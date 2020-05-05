<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- ============================================================================ -->
<!-- Taglib de spring security -->
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%--Ajout de la taglib de spring mvc 'form' --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- ============================================================================ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modification feuille de pr�sence</title>

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
	<div class="mainContent">
		<h1>Mise � jour de la feuille de pr�sence pour le cours
			${attribut_cours.libelle}</h1>


		<form:form modelAttribute="absenceForm" method="POST"
			action="${pageContext.request.contextPath}/absences/update/${attribut_cours.idCours}">

			<table class="table table-striped">

				<tr>
					<th>ID</th>
					<th>Etudiant</th>
					<th>absence</th>
					<th>motif</th>

				</tr>
				<c:forEach items="${absenceForm.listeEtudiantCours }" var="etudiantCours"
					varStatus="i">
					<tr>
					<form:hidden path="listeEtudiantCours[${i.index}].id" />
					<form:hidden path="listeEtudiantCours[${i.index}].etudiant.identifiant" />
					<form:hidden path="listeEtudiantCours[${i.index}].cours.idCours" />
						<td>${etudiantCours.id}</td> 			
						<td>${etudiantCours.etudiant.prenom} ${etudiantCours.etudiant.nom}</td>
						<td><form:select class="custom-select"
								path="listeEtudiantCours[${i.index}].absence">
								<option value="${etudiantCours.absence }">${etudiantCours.absence  ? "Absent" : "Present"}</option>
								<option value="false">Present</option>
								<option value="true">Absent</option>
							</form:select></td>
						<td><form:input path="listeEtudiantCours[${i.index}].motif" /></td>
					</tr>

				</c:forEach>
				<tr>
					<td colspan="4"><input class="btn btn-primary" type="submit"
						value="Modifier" /></td>
				</tr>
			</table>
		</form:form>
	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>