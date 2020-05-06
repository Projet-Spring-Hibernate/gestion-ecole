<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- ============================================================================ -->
<!-- Taglib -->
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

<title>Admin - ajouter enseignant</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<h1>Formulaire d'ajout d'un enseignant</h1>

		<form:form modelAttribute="enseignantform" method="POST"
			action="${pageContext.request.contextPath}/enseignants/add">


			<table class="table">

				<tr>
					<td><form:label path="enseignant.nom">Nom :</form:label></td>
					<td><form:input path="enseignant.nom" /></td>
					<td><form:errors path="enseignant.nom" /></td>
				</tr>
				<tr>
					<td><form:label path="enseignant.prenom">Prenom :</form:label></td>
					<td><form:input path="enseignant.prenom" /></td>
					<td><form:errors path="enseignant.prenom" /></td>
				</tr>
				<tr>
					<td><form:label path="enseignant.email">Email :</form:label></td>
					<td><form:input path="enseignant.email" /></td>
					<td><form:errors path="enseignant.email" /></td>
				</tr>

				<tr>
					<td><form:label path="enseignant.motdepasse">Mot de passe :</form:label></td>
					<td><form:input path="enseignant.motdepasse" /></td>
					<td><form:errors path="enseignant.motdepasse" /></td>
				</tr>

				<tr>
					<td><form:label path="adresse.rue">Rue :</form:label></td>
					<td><form:input path="adresse.rue" /></td>
					<td><form:errors path="adresse.rue" /></td>
				</tr>

				<tr>
					<td><form:label path="adresse.codePostal">Code postal :</form:label></td>
					<td><form:input path="adresse.codePostal" /></td>
					<td><form:errors path="adresse.codePostal" /></td>
				</tr>


				<tr>
					<td><form:label path="adresse.ville">Ville :</form:label></td>
					<td><form:input path="adresse.ville" /></td>
					<td><form:errors path="adresse.ville" /></td>
				</tr>


				<c:forEach
					items="${enseignantform.listeEnseignantMatierePromotion }"
					var="enseignantMatierePromotion" varStatus="i">
					<tr>
						<td>Promotion et matière :</td>
						<td>Promotion : <form:select class="custom-select"
								path="listeEnseignantMatierePromotion[${i.index}].promotion.idPromotion">
								<option value="0">-- Choisir --</option>
								<c:forEach
									items="${enseignantform.listePromotionsExistantes }"
									var="promotion">
									<option value="${promotion.idPromotion}">${promotion.libelle}</option>
								</c:forEach>
							</form:select>
						</td>
						<td>Matière : <form:select class="custom-select"
								path="listeEnseignantMatierePromotion[${i.index}].matiere.idMatiere">
								<option value="0">-- Choisir --</option>
								<c:forEach items="${enseignantform.listeMatieresExistantes }"
									var="matiere">
									<option value="${matiere.idMatiere}">${matiere.libelle}</option>
								</c:forEach>
							</form:select>
						</td>
					</tr>

				</c:forEach>
				<td colspan="3"><input class="btn btn-primary" type="submit" value="Ajouter" /></td>

			</table>
		</form:form>
	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
</body>
</html>