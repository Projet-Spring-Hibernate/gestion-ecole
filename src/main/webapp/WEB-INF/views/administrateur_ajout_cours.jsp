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

<title>Admin - ajouter cours</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete_admin.jsp" />

	<div class="mainContent">

		<br /> <br />
		<h1>Formulaire d'ajout d'un cours</h1>
		<%--coursform --%>

		<form:form modelAttribute="coursform" method="POST"
			action="${pageContext.request.contextPath}/cours/add">



			<table class="table">

				<tr>
					<td><form:label path="cours.libelle">Libelle :</form:label></td>
					<td><form:input path="cours.libelle" /></td>
					<td><form:errors path="cours.libelle" /></td>
				</tr>
				<tr>
					<td><form:label path="cours.date">Date :</form:label></td>
					<td><form:input path="cours.date" /></td>
					<td><form:errors path="cours.date" /></td>
				</tr>
				<tr>
					<td><form:label path="cours.duree">Dur�e :</form:label></td>
					<td><form:input path="cours.duree" /></td>
					<td><form:errors path="cours.duree" /></td>
				</tr>
				<tr>
					<td><form:label path="cours.description">Description :</form:label></td>
					<td><form:input path="cours.description" /></td>
					<td><form:errors path="cours.description" /></td>
				</tr>



				<tr> 
 					<td>Promotion : <form:select class="custom-select"
 							path="cours.promotion.idPromotion"> 
							<option value="0">-- Choisir --</option>
 							<c:forEach items="${coursform.listePromotionsExistantes }" 
 								var="promotion"> 
 								<option value="${promotion.idPromotion}">${promotion.libelle}</option>
 							</c:forEach>
 						</form:select>
					</td> 
 					<td>Mati�re : <form:select class="custom-select" 
 							path="cours.matiere.idMatiere"> 
							<option value="0">-- Choisir --</option> 
 							<c:forEach items="${coursform.listeMatieresExistantes }" 
 								var="matiere">
								<option value="${matiere.idMatiere}">${matiere.libelle}</option>
 							</c:forEach> 
 						</form:select> 
 					</td> 

			</tr> 


				<td colspan="3"><input class="btn btn-primary" type="submit"
					value="Ajouter" /></td>
			</table>
		</form:form>
	</div>


	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>