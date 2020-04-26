<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'authentification perso</title>
</head>
<body>

	<!-- ============== AFFICHAGE DES MESSAGES D'ERREUR ======================== -->
	<!-- En cas d'echec de l'authentification -->
	
	<c:if test="${not empty param.error }">
		<font color="red;">
			Erreur d'authentification. <br/>
			Raison : ${sessionScoped["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</font>
	</c:if>
	
	<!-- ============== AFFICHAGE DES MESSAGES DE DECONNEXION ======================== -->
	
	<c:if test="${not empty param.logout_message }">
		<font color="green;">
			Deconnexion avec succès <br/>
		</font>
	</c:if>
	
	
	<!-- ============== FORMULAIRE D'AUTHENTIFICATION PERSO ======================== -->

	<fieldset>
		<legend>Formulaire d'authentification</legend>
		
		<!-- construction de l'url effectuant l'anthentification -->
		<c:url value="login" var="loginUrl"/>
		<form action="${loginUrl }" method="post">
			<table>
				<tr>
					<td>Identifiant</td>
					<td><input type="text" name="u_identifiant"/> </td>
				</tr>
				<tr>
					<td>Mot de passe</td>
					<td><input type="text" name="u_motdepasse"/> </td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Se connecter"/></td>
					<td><input type="reset" value="Reset"/></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<br/><br/>
</body>
</html>