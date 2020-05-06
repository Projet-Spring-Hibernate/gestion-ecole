<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%--Ajout de la taglib de spring mvc 'form' --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<head>

<spring:url value="/assets/styles/bootstrap.css" var="bootstrapCss" />
<spring:url value="/assets/styles/style_perso.css" var="StylePerso" />


<link rel="stylesheet" href="${bootstrapCss}" />
<link rel="stylesheet" href="${StylePerso}" />

<spring:url value="/assets/scripts/bootstrap.js" var="bootstrapJS" />
<spring:url value="/assets/scripts/jquery-3.4.1.js" var="jquery" />
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrapJS}"></script>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'authentification perso</title>



<style>
html, body {
	height: 100%;
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: white;
}

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body class="text-center">

	<!-- ============== AFFICHAGE DES MESSAGES D'ERREUR ======================== -->
	<!-- En cas d'echec de l'authentification -->

	<c:if test="${not empty param.error }">
		<font color="red;"> Erreur d'authentification. <br /> Raison :
			${sessionScoped["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</font>
	</c:if>

	<!-- ============== AFFICHAGE DES MESSAGES DE DECONNEXION ======================== -->

	<c:if test="${not empty param.logout_message }">
		<font color="green;"> Deconnexion avec succès <br />
		</font>
	</c:if>


	<!-- ============== FORMULAIRE D'AUTHENTIFICATION PERSO ======================== -->


	<c:url value="login" var="loginUrl" />
	<form class="form-signin" action="${loginUrl }" method="post">
		<img class="mb-4"
			src="/01_gestion_ecoles/assets/images/online_education.png" alt=""
			width="200" height="200">
		<h1 class="h3 mb-3 font-weight-normal">Formulaire
			d'authentification</h1>
		<label for="inputEmail" class="sr-only">Adresse mail</label> <input
			type="email" id="inputEmail" name="u_identifiant"
			class="form-control" placeholder="Adresse mail" required autofocus>
		<label for="inputPassword" class="sr-only">Mot de passe</label> <input
			type="password" id="inputPassword" name="u_motdepasse"
			class="form-control" placeholder="Mot de passe" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Se
			connecter</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2019-2020</p>
	</form>



</body>
</html>